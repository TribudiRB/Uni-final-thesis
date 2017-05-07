package com.bcuzek.magisterka.controllers.settings.preferenceChange;

import android.preference.CheckBoxPreference;
import android.preference.ListPreference;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.controllers.SettingsController;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.call.ICallAction;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.theme.IUpdateTheme;
import com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey;

import lombok.RequiredArgsConstructor;
import lombok.val;

/**
 * Created by robert on 06.03.2017.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class SharedPreferenceChanged implements ISharedPreferenceChanged {

    private final ICallAction callAction;
    private final IUpdateTheme updateTheme;

    @Override
    public void onSharedPreferenceChanged(SettingsController preference) {
        checkAudio(preference);
        checkTheme(preference);
    }

    private void checkAudio(SettingsController preference) {
        val preferenceNew = (CheckBoxPreference) preference.findPreference(PreferenceServiceKey.PREFERENCES_CALL_VOLUME_TYPE);
        callAction.writeAudioStream(preferenceNew);
        callAction.writeNotification(preferenceNew);
    }

    private void checkTheme(SettingsController preference) {
        val lp = (ListPreference) preference.findPreference(PreferenceServiceKey.PREFERENCES_THEME_TYPE);
        updateTheme.update(lp, (MainActivity) preference.getActivity());
    }

}
