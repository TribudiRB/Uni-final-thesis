package com.bcuzek.magisterka.service.actionHolder;

import com.bcuzek.magisterka.service.actionHolder.action.ServiceAction;

import lombok.experimental.Builder;

import static com.bcuzek.magisterka.infrastructure.constans.Action.EXIT_APPLICATION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.PAUSE_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.RESTART_TIME_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.START_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.START_CALL_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_CALL_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_SERVICE;

@Builder
public class ActionHolder implements IActionHolder {

    private final ServiceAction pause;
    private final ServiceAction start;
    private final ServiceAction stop;
    private final ServiceAction stopService;
    private final ServiceAction phoneStart;
    private final ServiceAction phoneStop;
    private final ServiceAction restartTime;
    private final ServiceAction exitApplication;

    public ServiceAction getAction(String validatorType) {
        switch (validatorType) {
            case PAUSE_ACTION:
                return pause;
            case START_ACTION:
                return start;
            case STOP_ACTION:
                return stop;
            case STOP_SERVICE:
                return stopService;
            case START_CALL_ACTION:
                return phoneStart;
            case STOP_CALL_ACTION:
                return phoneStop;
            case RESTART_TIME_ACTION:
                return restartTime;
            case EXIT_APPLICATION:
                return exitApplication;
            default:
                return exitApplication;
        }
    }

}
