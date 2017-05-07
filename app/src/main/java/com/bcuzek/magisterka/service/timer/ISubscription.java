package com.bcuzek.magisterka.service.timer;

import com.bcuzek.magisterka.service.model.TimeDelay;

/**
 * Created by robert on 22.03.2017.
 */

public interface ISubscription {

    void start(Timer.Action action);

    void stop();

    boolean isUnsubscribed();

    int getDelay();

    void setDelay(TimeDelay delay);
}
