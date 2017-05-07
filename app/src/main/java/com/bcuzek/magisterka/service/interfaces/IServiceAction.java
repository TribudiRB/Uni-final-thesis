package com.bcuzek.magisterka.service.interfaces;

public interface IServiceAction {
    void onPause();

    void onStop();

    void onStart(int sound);

    void shutdownService();

    void onPhoneCallStart();

    void onPhoneCallStop();

    void restartTime();

    void exitApplication();
}
