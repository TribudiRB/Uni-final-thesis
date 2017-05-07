package com.bcuzek.magisterka.utils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import lombok.val;

public class KeyboardUtils implements IKeyboardUtils {

    public void hide(Activity activity) {
        val view = activity.getCurrentFocus();
        if (view != null) {
            val methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
