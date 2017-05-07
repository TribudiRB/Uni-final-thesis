package com.bcuzek.magisterka.infrastructure.domain.repositories.track.record;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.infrastructure.domain.repositories.IRepository;

/**
 * Created by robert on 22.12.2016.
 */
public interface IRecordRepository extends IRepository<Record> {
    int getLastUuId();
}
