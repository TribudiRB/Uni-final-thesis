package com.bcuzek.magisterka.controllers.player.dialogs.firstRun;

import android.app.Activity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import lombok.experimental.Builder;

/**
 * Created by robert on 22.08.2016.
 */
@Builder
public class FirstRun implements IFirstRun {
    private IPreference service;
    private IThemeUtils utils;
    private int title;
    private int neutralText;
    private int content;

    @Override
    public void displayWelcomeDialog(Activity activity) {
        if (service.isFirstApplicationRun()) {
            service.setIsFirstApplicationRun(false);
            new MaterialDialog.Builder(activity)
                    .title(title)
                    .limitIconToDefaultSize()
                    .content(content)
                    .neutralText(neutralText)
                    .buttonsGravity(GravityEnum.CENTER)
                    .neutralColorRes(utils.getColor())
                    .titleColorRes(utils.getColor())
                    .build()
                    .show();
        }
    }
}
