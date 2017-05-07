package com.bcuzek.magisterka.controllers.record.dialog;

import android.app.Activity;

public interface IOptionDialog {
    void showDialog(Activity activity, OptionDialog.DialogAction positive, OptionDialog.DialogAction negative);
}
