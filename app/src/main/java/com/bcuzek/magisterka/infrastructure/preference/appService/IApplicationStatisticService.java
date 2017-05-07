package com.bcuzek.magisterka.infrastructure.preference.appService;

/**
 * Created by robert on 16.08.2016.
 */

public interface IApplicationStatisticService {
    boolean isFirstApplicationRun();

    void setIsFirstApplicationRun(boolean bool);
}
