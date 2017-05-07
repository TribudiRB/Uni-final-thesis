package com.bcuzek.magisterka.infrastructure.domain.repositories.track.statistics;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;

import io.realm.Realm;
import lombok.experimental.Builder;

/**
 * Created by robert on 29.12.2016.
 */
@Builder
public class StatisticsRepository implements IStatisticsRepository {

    private IDatabaseContext databaseContext;

    @Override
    public long getDurationByStatistics(Statistics info) {
        return databaseContext
                .getRealmInstance()
                .where(Statistics.class)
                .equalTo("uuid", info.getUuid())
                .findFirst()
                .getDuration();
    }

    @Override
    public void update(final Statistics statistics) {
        Realm realm = databaseContext.getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(statistics);
        realm.commitTransaction();
    }

}
