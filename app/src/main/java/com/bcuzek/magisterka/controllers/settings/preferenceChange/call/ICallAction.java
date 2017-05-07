package com.bcuzek.magisterka.controllers.settings.preferenceChange.call;

import android.preference.CheckBoxPreference;

/**
 * Created by robert on 06.03.2017.
 */

public interface ICallAction {
    void writeNotification(CheckBoxPreference preference);

    void writeAudioStream(CheckBoxPreference preference);
}
