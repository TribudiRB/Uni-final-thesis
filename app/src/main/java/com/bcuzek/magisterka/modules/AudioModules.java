package com.bcuzek.magisterka.modules;

import android.content.Context;
import android.media.MediaPlayer;

import com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory.IRepositoryFactory;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics.IStatisticsRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.musicPlayer.IMusicPlayer;
import com.bcuzek.magisterka.infrastructure.musicPlayer.Player;
import com.bcuzek.magisterka.infrastructure.session.ISession;
import com.bcuzek.magisterka.infrastructure.session.Session;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 07.05.2017.
 */

@Module
public class AudioModules {

    @Provides
    @Singleton
    public IMusicPlayer holderModule(Context context,
                                     ITrackRepository soundRepository,
                                     IRecordRepository recordRepository) {
        return Player
                .builder()
                .soundRepository(soundRepository)
                .recordRepository(recordRepository)
                .context(context)
                .trackSize(soundRepository.count())
                .mediaPlayer(new MediaPlayer())
                .build();
    }

    @Singleton
    @Provides
    public ISession getSoundSession(IStatisticsRepository statisticsRepository,
                                    IRepositoryFactory repositoryFactory) {
        return new Session(statisticsRepository, repositoryFactory);
    }
}
