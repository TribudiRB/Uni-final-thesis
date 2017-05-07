package com.bcuzek.magisterka.controllers.player;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.experimental.Builder;

import static com.bcuzek.magisterka.infrastructure.constans.Time.MAX_TIME;
import static com.bcuzek.magisterka.infrastructure.constans.Time.ONE_MINUTE;

@Builder
public class TimeCalculator {

    private static final int ANGLE = 360;
    private IPreference service;
    private ITimeHolder timeHolder;
    private ICalculatorUtils calculatorUtils;
    private IStringUtils utils;

    public void updateClock(CircuitTimer clock) {
        if (isEndless(service.readTimePlay()))
            updateBar(clock, timeHolder.getCurrentTime() / (ONE_MINUTE / ANGLE) % ANGLE, setHour(isEnd((service.readTimePlay() - timeHolder.getCurrentTime()))));
        else
            updateClockMinute(clock);
    }

    public void restartClock(CircuitTimer clock, String val) {
        updateBar(clock, 0, val);
    }

    public void updateClockMinute(CircuitTimer clock) {
        updateBar(clock, (this.timeHolder.getCurrentTime() / (ONE_MINUTE / ANGLE)) % ANGLE, setHour(timeHolder.getCurrentTime() % ONE_MINUTE));
    }

    public void initClock(CircuitTimer clock) {
        updateBar(clock, 0, isEndless(service.readTimePlay()) ? setHour(service.readTimePlay()) : utils.getStringById(R.string.infinity));
    }

    private boolean isEndless(long time) {
        return time != MAX_TIME;
    }

    private long isEnd(long timeToPrint) {
        return timeToPrint < 0 ? 0 : timeToPrint;
    }

    private void updateBar(CircuitTimer clock, long play, String timeToPrint) {
        clock.updateBarPositionAndHour(play, timeToPrint);
    }

    private String setHour(long hour) {
        return calculatorUtils.calculate(hour);
    }
}
