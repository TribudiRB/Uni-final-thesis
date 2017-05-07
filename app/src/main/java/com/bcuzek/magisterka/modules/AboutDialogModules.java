package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.about.dialog.IDialogHolder;
import com.bcuzek.magisterka.controllers.about.dialog.action.ContactAction;
import com.bcuzek.magisterka.controllers.about.dialog.action.IDialogAction;
import com.bcuzek.magisterka.controllers.about.dialog.dialog.AboutDialog;
import com.bcuzek.magisterka.controllers.about.dialog.dialog.IAboutDialog;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.AboutApplicationQualifiers;
import com.bcuzek.magisterka.utils.string.IStringUtils;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.11.2016.
 */
@Module
public class AboutDialogModules {

    @Singleton
    @Provides
    public IDialogHolder getDialogHolder(
            @Named(AboutApplicationQualifiers.DEVELOPER) IDialogAction developer,
            @Named(AboutApplicationQualifiers.VERSION) IDialogAction version,
            @Named(AboutApplicationQualifiers.PERMISSION) IDialogAction permission,
            @Named(AboutApplicationQualifiers.CONTACT) IDialogAction contact,
            @Named(AboutApplicationQualifiers.THESIS) IDialogAction thesis) {
        return validatorType -> {
            switch (validatorType) {
                case DEVELOPER:
                    return developer;
                case VERSION:
                    return version;
                case PERMISSIONS:
                    return permission;
                case CONTACT:
                    return contact;
                case THESIS:
                    return thesis;
                default:
                    return developer;
            }
        };
    }

    @Provides
    @Named(AboutApplicationQualifiers.DEVELOPER)
    @Singleton
    public IDialogAction getDeveloper() {
        return activity -> {
        };
    }

    @Provides
    @Named(AboutApplicationQualifiers.VERSION)
    @Singleton
    public IDialogAction getVersionAction(@Named(AboutApplicationQualifiers.VERSION) IAboutDialog aboutApplicationDialog) {
        return activity -> aboutApplicationDialog.showDialog(activity);
    }

    @Provides
    @Named(AboutApplicationQualifiers.PERMISSION)
    @Singleton
    public IDialogAction getPermissionAction(@Named(AboutApplicationQualifiers.PERMISSION) IAboutDialog aboutApplicationDialog) {
        return activity -> aboutApplicationDialog.showDialog(activity);
    }

    @Provides
    @Named(AboutApplicationQualifiers.THESIS)
    @Singleton
    public IDialogAction getThesisAction(@Named(AboutApplicationQualifiers.THESIS) IAboutDialog aboutApplicationDialog) {
        return activity -> aboutApplicationDialog.showDialog(activity);
    }

    @Provides
    @Named(AboutApplicationQualifiers.CONTACT)
    @Singleton
    public IDialogAction getContactInfoAction(IStringUtils utils) {
        return ContactAction
                .builder()
                .stringUtils(utils)
                .build();
    }

    @Provides
    @Named(AboutApplicationQualifiers.PERMISSION)
    @Singleton
    public IAboutDialog permissionAboutApplicationDialog(IThemeUtils utils) {
        return AboutDialog
                .builder()
                .title(R.string.permissions_question)
                .content(R.string.permission_text)
                .centerString(R.string.dialog_button_value_close)
                .utils(utils)
                .build();
    }

    @Provides
    @Named(AboutApplicationQualifiers.VERSION)
    @Singleton
    public IAboutDialog versionAboutApplicationDialog(IThemeUtils utils) {
        return AboutDialog
                .builder()
                .title(R.string.version_information)
                .content(R.string.current_version)
                .centerString(R.string.dialog_button_value_close)
                .utils(utils)
                .build();
    }

    @Provides
    @Named(AboutApplicationQualifiers.THESIS)
    @Singleton
    public IAboutDialog getAboutApplicationDialog(IThemeUtils utils) {
        return AboutDialog
                .builder()
                .title(R.string.final_work)
                .content(R.string.dialog_thesis_context)
                .centerString(R.string.dialog_button_value_close)
                .utils(utils)
                .build();
    }
}
