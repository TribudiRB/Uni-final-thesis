package com.bcuzek.magisterka.service.timer;

import com.bcuzek.magisterka.service.model.TimeDelay;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 22.03.2017.
 */

public class Timer implements ISubscription {

    private Subscription subscription;
    private int interval = 100;

    @Getter
    private int delay = 1;

    @Override
    public void start(Action action) {
        subscription = Observable
                .interval(interval, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(aLong -> aLong % delay == 0)
                .subscribe(aLong -> action.action());
    }

    @Override
    public void stop() {
        if (subscription == null)
            return;
        subscription.unsubscribe();
    }

    @Override
    public boolean isUnsubscribed() {
        return subscription != null && !subscription.isUnsubscribed();
    }

    @Override
    public void setDelay(TimeDelay delay) {
        this.delay = delay.getValue();
    }

    public interface Action {
        void action();
    }
}
