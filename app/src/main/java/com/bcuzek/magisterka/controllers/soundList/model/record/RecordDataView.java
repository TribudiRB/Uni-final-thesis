package com.bcuzek.magisterka.controllers.soundList.model.record;

import com.bcuzek.magisterka.controllers.soundList.model.RecordViewModel;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Builder;
import rx.Observable;

@Getter
@Builder
public class RecordDataView implements IRecordView {
    private final IStringUtils context;
    private final ICalculatorUtils calculatorUtils;
    private final IRecordRepository recordRepository;

    public List<RecordViewModel> prepareData() {
        return Observable
                .from(recordRepository.getAll())
                .map(record -> new RecordViewModel().create(context, calculatorUtils, record, recordRepository.sumDuration(record)))
                .toList()
                .toBlocking()
                .single();
    }
}