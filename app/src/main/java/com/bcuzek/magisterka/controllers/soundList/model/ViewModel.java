package com.bcuzek.magisterka.controllers.soundList.model;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.IDomainTrack;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

/**
 * Created by robert on 22.04.2017.
 */

public abstract class ViewModel<T extends IDomainTrack> {

    protected String calculate(IStringUtils utils, ICalculatorUtils calculatorUtils, long time, T domainTrack) {
        return utils
                .getStringById(R.string.play_time)
                .concat(" ")
                .concat(calculatorUtils.calculate(time))
                .concat(" ")
                .concat(utils.getStringById(R.string.play_times))
                + domainTrack.getStatisticsList().size();
    }
}
