package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.permission.DialogPermission;
import com.bcuzek.magisterka.controllers.permission.IDialogPermission;
import com.bcuzek.magisterka.controllers.permission.callback.AndroidDialogPermission;
import com.bcuzek.magisterka.controllers.permission.callback.AndroidUserSettings;
import com.bcuzek.magisterka.controllers.permission.callback.ICallback;
import com.bcuzek.magisterka.controllers.permission.manager.IPermissionManager;
import com.bcuzek.magisterka.controllers.permission.manager.PermissionManager;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.PermissionQualifiers;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 17.03.2017.
 */
@Module
public class PermissionModules {

    @Singleton
    @Provides
    @Named(PermissionQualifiers.SETTINGS)
    public ICallback userSettings(IPreference service,
                                  IIntentManager creator) {
        return AndroidUserSettings
                .builder()
                .preferenceService(service)
                .intentManager(creator)
                .build();
    }

    @Singleton
    @Provides
    @Named(PermissionQualifiers.DIALOG)
    public ICallback iCallback(IPreference service, IPermissionManager manager) {
        return AndroidDialogPermission
                .builder()
                .service(service)
                .permissionManager(manager)
                .build();
    }

    @Provides
    @Named(PermissionQualifiers.DIALOG)
    @Singleton
    public IDialogPermission getDialogPermission(IThemeUtils utils,
                                                 IPreference service,
                                                 @Named(PermissionQualifiers.DIALOG) ICallback callback) {
        return DialogPermission
                .builder()
                .title(R.string.permissions_question)
                .service(service)
                .utils(utils)
                .callback(callback)
                .content(R.string.record_permission_can_again)
                .positiveText(R.string.dialog_button_value_close)
                .negativeText(R.string.add_permission)
                .build();
    }

    @Provides
    @Named(PermissionQualifiers.SETTINGS)
    @Singleton
    public IDialogPermission getDialogPermission2(IThemeUtils utils,
                                                  IPreference service,
                                                  @Named(PermissionQualifiers.SETTINGS) ICallback callback) {
        return DialogPermission
                .builder()
                .title(R.string.permissions_question)
                .service(service)
                .utils(utils)
                .callback(callback)
                .content(R.string.record_permission_newer_again)
                .positiveText(R.string.dialog_button_value_close)
                .negativeText(R.string.go_to_settings)
                .build();
    }

    @Provides
    @Singleton
    public IPermissionManager getPermissionManager() {
        return new PermissionManager();
    }
}
