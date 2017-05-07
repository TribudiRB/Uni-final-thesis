package com.bcuzek.magisterka.infrastructure.preference.appService;

/**
 * Created by robert on 16.08.2016.
 */

public interface IApplicationPermissionRecordsService {
    boolean isFirstTimeDisplayDialog();

    void setIsFirstTimeDisplayDialog(boolean bool);
}
