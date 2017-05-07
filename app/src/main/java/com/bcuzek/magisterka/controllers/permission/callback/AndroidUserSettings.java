package com.bcuzek.magisterka.controllers.permission.callback;

import android.app.Activity;

import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import lombok.experimental.Builder;

/**
 * Created by robert on 17.01.2017.
 */

@Builder
public class AndroidUserSettings implements ICallback {

    private final IPreference preferenceService;
    private final IIntentManager intentManager;

    @Override
    public void action(Activity activity) {
        activity.startActivity(intentManager.openApplicationDetailSettings(activity));
        preferenceService.writeCanDisplayDialog(true);
    }
}
