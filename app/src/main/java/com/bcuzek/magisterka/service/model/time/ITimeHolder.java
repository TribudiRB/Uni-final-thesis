package com.bcuzek.magisterka.service.model.time;

public interface ITimeHolder {

    long getCalculatedTime();

    void updatePlayTime();

    void restartPlayedTime();

    void setNewStartTime();

    long getCurrentTime();

    void setCurrentTime(long currentTime);

    void restartTime();
}
