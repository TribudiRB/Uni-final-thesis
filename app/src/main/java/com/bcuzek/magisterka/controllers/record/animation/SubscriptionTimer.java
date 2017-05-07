package com.bcuzek.magisterka.controllers.record.animation;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 22.03.2017.
 */

public class SubscriptionTimer implements ISubscriptionTimer {

    private final static int interval = 100;
    private Subscription timer;

    @Override
    public void start(IAction action) {
        timer = Observable
                .interval(interval, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> action.action());
    }

    @Override
    public void stop() {
        if (timer == null) return;
        timer.unsubscribe();
    }
}
