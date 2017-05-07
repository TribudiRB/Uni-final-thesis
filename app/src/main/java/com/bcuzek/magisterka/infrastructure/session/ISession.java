package com.bcuzek.magisterka.infrastructure.session;

/**
 * Created by robert on 01.10.2016.
 */
public interface ISession {
    void startSession(int id);

    void stopSession();

    boolean isRunning();

    void update(long played);
}