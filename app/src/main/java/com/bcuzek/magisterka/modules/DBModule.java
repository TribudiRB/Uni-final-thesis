package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.DatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.initialTransaction.ITrackInitialTransaction;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory.IRepositoryFactory;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory.RepositoryFactory;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.RecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics.IStatisticsRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics.StatisticsRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.TrackRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApplicationContextModule.class})
public class DBModule {

    @Provides
    public ITrackRepository provideSoundRepository(IDatabaseContext databaseContext) {
        return new TrackRepository(databaseContext);
    }

    @Provides
    public IRecordRepository geIRecordRepository(IDatabaseContext databaseContext) {
        return new RecordRepository(databaseContext);
    }

    @Provides
    public IStatisticsRepository getIStatisticsRepository(IDatabaseContext databaseContext) {
        return StatisticsRepository.builder().databaseContext(databaseContext).build();
    }

    @Provides
    public IRepositoryFactory getRepositoryFactory(ITrackRepository trackRepository,
                                                   IRecordRepository recordRepository) {
        return RepositoryFactory
                .builder()
                .trackRepository(trackRepository)
                .recordRepository(recordRepository)
                .trackSize(trackRepository.count())
                .build();
    }

    @Provides
    @Singleton
    public IDatabaseContext provideDatabaseContext(Context context,
                                                   ITrackInitialTransaction transaction) {
        return new DatabaseContext(context, transaction);
    }
}
