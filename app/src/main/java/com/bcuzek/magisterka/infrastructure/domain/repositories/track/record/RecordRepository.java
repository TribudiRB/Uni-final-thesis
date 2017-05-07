package com.bcuzek.magisterka.infrastructure.domain.repositories.track.record;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.infrastructure.domain.repositories.Repository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.record.RecordTransaction;

/**
 * Created by robert on 22.12.2016.
 */

public class RecordRepository extends Repository<Record> implements IRecordRepository {

    public RecordRepository(IDatabaseContext databaseContext) {
        super(Record.class, databaseContext);
    }

    @Override
    public void createPlayInfoHistory(Statistics statistics, int soundUUID) {
        databaseContext
                .getRealmInstance()
                .executeTransaction(new RecordTransaction(getById(soundUUID), statistics));
    }

    @Override
    public int getLastUuId() {
        Number uuid = databaseContext.getRealmInstance().where(Record.class).max("uuid");
        return uuid != null ? uuid.intValue() + 1 : (int) databaseContext.getRealmInstance()
                .where(Track.class).count();
    }
}
