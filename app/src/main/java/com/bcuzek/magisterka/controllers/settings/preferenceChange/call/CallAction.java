package com.bcuzek.magisterka.controllers.settings.preferenceChange.call;

import android.preference.CheckBoxPreference;

import com.bcuzek.magisterka.controllers.settings.PlaySoundMusicCall;
import com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import lombok.RequiredArgsConstructor;

/**
 * Created by robert on 06.03.2017.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class CallAction implements ICallAction {

    private final IPreference service;

    @Override
    public void writeNotification(CheckBoxPreference preference) {
        boolean call = preference.getSharedPreferences().getBoolean(PreferenceServiceKey.PREFERENCES_CALL_VOLUME_TYPE, true);
        service.writeRigtoneStream(call == PlaySoundMusicCall.NO.isValue() ? PlaySoundMusicCall.NO : PlaySoundMusicCall.YES);
    }

    @Override
    public void writeAudioStream(CheckBoxPreference preference) {
        boolean mute = preference.getSharedPreferences().getBoolean(PreferenceServiceKey.PHONE_MUTE_KEY, true);
        service.writeNotificationStream(mute);
    }
}
