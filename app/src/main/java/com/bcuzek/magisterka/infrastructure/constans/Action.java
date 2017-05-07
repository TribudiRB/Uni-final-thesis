package com.bcuzek.magisterka.infrastructure.constans;

/**
 * Created by robert on 10.11.2016.
 */

public interface Action {
    String PAUSE_ACTION = "com.buczek.magisterka.foregroundservice.pause";
    String START_ACTION = "com.buczek.magisterka.foregroundservice.start";
    String STOP_ACTION = "com.buczek.magisterka.foregroundservice.stop";
    String RESTART_TIME_ACTION = "com.buczek.magisterka.foregroundservice.restart.time";
    String STOP_SERVICE = "com.buczek.magisterka.foregroundservice.service.stop";
    String STOP_CALL_ACTION = "com.buczek.magisterka.foregroundservice.telephon.stop.call";
    String START_CALL_ACTION = "com.buczek.magisterka.foregroundservice.telephon.start.call";
    String START_PLAY_MUSIC = "com.buczek.magisterka.foregroundservice.music.play";
    String EXIT_APPLICATION = "com.buczek.magisterka.close.application";
}
