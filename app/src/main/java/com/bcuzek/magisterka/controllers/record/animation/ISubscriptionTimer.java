package com.bcuzek.magisterka.controllers.record.animation;

/**
 * Created by robert on 22.03.2017.
 */

public interface ISubscriptionTimer {
    void start(IAction action);

    void stop();
}
