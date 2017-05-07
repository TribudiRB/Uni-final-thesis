package com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.record;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.Transaction;

public class RecordTransaction extends Transaction<Record> {

    public RecordTransaction(Record track, Statistics statistics) {
        super(track, statistics);
    }

}