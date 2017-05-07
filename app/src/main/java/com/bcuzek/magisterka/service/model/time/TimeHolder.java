package com.bcuzek.magisterka.service.model.time;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeHolder implements ITimeHolder {
    private long currentTime = 0;
    private long startTime = 0;
    private long playTime = 0;

    @Override
    public long getCalculatedTime() {
        return getTime() - startTime + playTime;
    }

    @Override
    public void updatePlayTime() {
        this.playTime += getTime() - startTime;
    }

    @Override
    public void restartPlayedTime() {
        this.playTime = 0;
    }

    @Override
    public void setNewStartTime() {
        this.startTime = getTime();
    }

    @Override
    public void restartTime() {
        this.currentTime = 0;
    }

    private long getTime() {
        return new Date().getTime();
    }
}
