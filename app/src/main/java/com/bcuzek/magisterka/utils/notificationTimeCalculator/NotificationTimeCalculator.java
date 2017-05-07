package com.bcuzek.magisterka.utils.notificationTimeCalculator;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.experimental.Builder;

import static com.bcuzek.magisterka.infrastructure.constans.Time.MAX_TIME;

/**
 * Created by robert on 23.03.2017.
 */

@Builder
public class NotificationTimeCalculator implements INotificationTimeCalculator {
    private final ICalculatorUtils calculatorUtils;
    private final IStringUtils utils;
    private final IPreference service;

    @Override
    public String calculateTime(long time) {
        return MAX_TIME != service.readTimePlay() ?
                calculatorUtils
                        .calculate(service.readTimePlay() - time)
                        .concat(" ")
                        .concat(utils.getStringById(R.string.concate_of)
                                .concat(" ")
                                .concat(calculatorUtils.calculate(service.readTimePlay())))
                : calculatorUtils.calculate(time);
    }

}
