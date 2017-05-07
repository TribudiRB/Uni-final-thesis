package com.bcuzek.magisterka.utils.record;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;

public interface IRecordUtils {
    Record createRecord(String name);
}
