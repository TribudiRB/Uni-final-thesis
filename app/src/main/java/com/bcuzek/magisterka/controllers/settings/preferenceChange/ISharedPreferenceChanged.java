package com.bcuzek.magisterka.controllers.settings.preferenceChange;

import com.bcuzek.magisterka.controllers.SettingsController;

/**
 * Created by robert on 06.03.2017.
 */

public interface ISharedPreferenceChanged {
    void onSharedPreferenceChanged(SettingsController preference);
}
