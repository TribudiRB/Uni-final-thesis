package com.bcuzek.magisterka.controllers.record.dialog;

import android.app.Activity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.R;

import lombok.experimental.Builder;

@Builder
public class OptionDialog implements IOptionDialog {
    private int content;
    private int positiveText;
    private int negativeText;

    public void showDialog(Activity activity, DialogAction positive, DialogAction negative) {
        new MaterialDialog
                .Builder(activity)
                .content(content)
                .contentGravity(GravityEnum.CENTER)
                .cancelable(false)
                .positiveText(positiveText)
                .onPositive((dialog, which) -> positive.action())
                .onNegative((dialog, which) -> negative.action())
                .negativeText(negativeText)
                .buttonsGravity(GravityEnum.CENTER)
                .positiveColorRes(R.color.colorRed)
                .negativeColorRes(R.color.colorGreenDark)
                .contentColorRes(R.color.colorChocolate)
                .build()
                .show();
    }

    public interface DialogAction {
        void action();
    }
}
