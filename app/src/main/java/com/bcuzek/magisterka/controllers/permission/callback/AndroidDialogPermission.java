package com.bcuzek.magisterka.controllers.permission.callback;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;

import com.bcuzek.magisterka.controllers.permission.manager.IPermissionManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import lombok.experimental.Builder;

import static com.bcuzek.magisterka.controllers.permission.manager.PermissionManager.REQUEST_PERMISSIONS;


/**
 * Created by robert on 17.01.2017.
 */

@Builder
public class AndroidDialogPermission implements ICallback {

    private final IPreference service;
    private final IPermissionManager permissionManager;

    @Override
    public void action(Activity activity) {
        ActivityCompat.requestPermissions(activity, permissionManager.getRequirePermission(), REQUEST_PERMISSIONS);
        service.writeCanDisplayDialog(true);
    }
}
