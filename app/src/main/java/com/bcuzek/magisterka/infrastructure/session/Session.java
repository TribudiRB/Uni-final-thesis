package com.bcuzek.magisterka.infrastructure.session;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory.IRepositoryFactory;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics.IStatisticsRepository;

import java.util.Date;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.val;

/**
 * Created by robert on 01.10.2016.
 */
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class Session implements ISession {
    private final IStatisticsRepository statisticsRepository;
    private final IRepositoryFactory repositoryFactory;
    private Date date;
    private String playInfoUUID;
    private boolean running = false;

    @Override
    public void startSession(int id) {
        date = new Date();
        playInfoUUID = UUID
                .randomUUID()
                .toString();
        repositoryFactory
                .getRepository(id)
                .createPlayInfoHistory(createPlayInfo(0), id);
        running = true;
    }

    @Override
    public void stopSession() {
        running = false;
    }

    @Override
    public void update(long played) {
        val duration = statisticsRepository.getDurationByStatistics(createPlayInfo(played));
        statisticsRepository.update(createPlayInfo(played + duration));
        running = true;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private Statistics createPlayInfo(long duration) {
        return new Statistics(playInfoUUID, date, duration);
    }

}
