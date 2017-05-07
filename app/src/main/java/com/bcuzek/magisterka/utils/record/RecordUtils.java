package com.bcuzek.magisterka.utils.record;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.utils.file.IFileUtils;

import java.util.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class RecordUtils implements IRecordUtils {
    private final static int defIcon = R.drawable.ic_audiotrack_white_24dp;
    private final IRecordRepository repository;
    private final IFileUtils manager;

    @Override
    public Record createRecord(String name) {
        Record custom = new Record();
        custom.setUuid(repository.getLastUuId());
        custom.setTitle(name);
        custom.setIcon(defIcon);
        custom.setName(manager.createFile(name));
        custom.setDate(new Date());
        return custom;
    }
}
