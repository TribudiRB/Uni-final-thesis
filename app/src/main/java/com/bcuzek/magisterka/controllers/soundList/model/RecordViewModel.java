package com.bcuzek.magisterka.controllers.soundList.model;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.Data;

/**
 * Created by robert on 21.04.2017.
 */

@Data
public class RecordViewModel extends ViewModel<Record> {
    private Record recordses;
    private String description;

    public RecordViewModel create(IStringUtils utils, ICalculatorUtils calculatorUtils, Record track, long time) {
        recordses = track;
        description = calculate(utils, calculatorUtils, time, track);
        return this;
    }

}
