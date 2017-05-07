package com.bcuzek.magisterka.controllers.base;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.permission.IDialogPermission;
import com.bcuzek.magisterka.controllers.permission.manager.IPermissionManager;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.PermissionQualifiers;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.keyboard.IKeyboardUtils;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

import static com.bcuzek.magisterka.controllers.permission.manager.PermissionManager.RECORD_AUDIO;
import static com.bcuzek.magisterka.controllers.permission.manager.PermissionManager.REQUEST_PERMISSIONS;
import static com.bcuzek.magisterka.controllers.permission.manager.PermissionManager.WRITE_EXTERNAL_STORAGE;

/**
 * Created by robert on 06.03.2017.
 */

public abstract class BaseRecordController extends BaseController {

    @Inject
    @Named(PermissionQualifiers.DIALOG)
    IDialogPermission dialogPermission;

    @Inject
    @Named(PermissionQualifiers.SETTINGS)
    IDialogPermission androidSettings;

    @Inject
    IPreference service;

    @Inject
    IKeyboardUtils keyboardAction;

    @Inject
    IPermissionManager permissionManager;

    @Getter
    String fragmentTag = ViewTag.RecordView;

    @Getter
    int fragmentIdentifier = R.layout.record_controller;

    @Override
    public void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(getMainActivity(), WRITE_EXTERNAL_STORAGE) + ContextCompat.checkSelfPermission(getMainActivity(), RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (service.isFirstTimeDisplayDialog()) {
                ActivityCompat.requestPermissions(getMainActivity(), permissionManager.getRequirePermission(), REQUEST_PERMISSIONS);
                service.setIsFirstTimeDisplayDialog(false);
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(getMainActivity(), WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getMainActivity(), RECORD_AUDIO)) {
                dialogPermission.display(getMainActivity());
            } else
                androidSettings.display(getMainActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        keyboardAction.hide(getActivity());
    }

    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

}
