package com.bcuzek.magisterka.controllers.player.seekBar;

import android.media.AudioManager;
import android.widget.SeekBar;

import lombok.experimental.Builder;

@Builder
public class SeekBarChangeListener implements ISeekBarChangeListener {

    private AudioManager audioManager;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}