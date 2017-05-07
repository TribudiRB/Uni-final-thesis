package com.bcuzek.magisterka.infrastructure.musicPlayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;

import java.io.IOException;

import lombok.experimental.Builder;
import lombok.val;
import rx.Observable;

@Builder
public class Player implements IMusicPlayer {
    private ITrackRepository soundRepository;
    private IRecordRepository recordRepository;
    private Context context;
    private long trackSize;
    private MediaPlayer mediaPlayer;

    @Override
    public void play(int sound) throws IOException {
        if (sound < trackSize) {
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, soundRepository.getById(sound).getName());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else {
            val path = Observable
                    .from(recordRepository.getAll())
                    .filter(e -> e.getUuid() == sound)
                    .toBlocking()
                    .first()
                    .getName();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }
}
