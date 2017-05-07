package com.bcuzek.magisterka.service.interfaces;

public interface IStatusWatcher {
    void onStateChange();

    void onTimeChanged(long milliseconds);

}
