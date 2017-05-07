package com.bcuzek.magisterka.controllers.permission.manager;

import android.Manifest;

/**
 * Created by robert on 04.05.2017.
 */

public class PermissionManager implements IPermissionManager {
    public static final int REQUEST_PERMISSIONS = 20;
    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @Override
    public String[] getRequirePermission() {
        return new String[]{
                WRITE_EXTERNAL_STORAGE,
                RECORD_AUDIO
        };
    }
}
