package com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;

/**
 * Created by robert on 29.12.2016.
 */

public interface IStatisticsRepository {
    long getDurationByStatistics(Statistics info);

    void update(final Statistics statistics);
}
