package com.bcuzek.magisterka.controllers.about.dialog.dialog;

import android.app.Activity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import lombok.experimental.Builder;


@Builder
public class AboutDialog implements IAboutDialog {
    private final int title;
    private final int content;
    private final int centerString;
    private final IThemeUtils utils;

    public void showDialog(Activity activity) {
        new MaterialDialog.Builder(activity)
                .title(title)
                .titleColorRes(utils.getColor())
                .limitIconToDefaultSize()
                .content(content)
                .neutralText(centerString)
                .buttonsGravity(GravityEnum.CENTER)
                .neutralColorRes(utils.getColor())
                .build()
                .show();
    }
}
