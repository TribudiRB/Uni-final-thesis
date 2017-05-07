package com.bcuzek.magisterka.controllers.permission;

import android.app.Activity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.controllers.permission.callback.ICallback;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import lombok.experimental.Builder;

@Builder
public class DialogPermission implements IDialogPermission {
    private final IThemeUtils utils;
    private final IPreference service;
    private final ICallback callback;
    private final int title;
    private final int content;
    private final int positiveText;
    private final int negativeText;

    public void display(Activity activity) {
        if (service.readCanDisplayDialog()) {
            service.writeCanDisplayDialog(false);
            new MaterialDialog.Builder(activity)
                    .title(title)
                    .content(content)
                    .limitIconToDefaultSize()
                    .positiveText(positiveText)
                    .buttonsGravity(GravityEnum.CENTER)
                    .negativeText(negativeText)
                    .cancelable(false)
                    .onNegative((dialog1, which) -> callback.action(activity))
                    .onPositive((dialog1, which) -> {
                        ((MainActivity) activity).openPlayer();
                        service.writeCanDisplayDialog(true);
                    })
                    .titleColorRes(utils.getColor())
                    .negativeColorRes(utils.getColor())
                    .positiveColorRes(utils.getColor())
                    .build()
                    .show();
        }
    }
}