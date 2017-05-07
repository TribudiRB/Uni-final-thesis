package com.bcuzek.magisterka.controllers.settings.preferenceChange.theme;

import android.preference.ListPreference;

import com.bcuzek.magisterka.MainActivity;

/**
 * Created by robert on 06.03.2017.
 */

public interface IUpdateTheme {
    void update(ListPreference preference, MainActivity activity);
}
