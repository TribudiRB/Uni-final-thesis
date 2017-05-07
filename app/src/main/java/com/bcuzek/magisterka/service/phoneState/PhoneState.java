package com.bcuzek.magisterka.service.phoneState;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.bcuzek.magisterka.controllers.base.PlayerPhoneStateListener;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import lombok.RequiredArgsConstructor;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by robert on 23.03.2017.
 */


@RequiredArgsConstructor(suppressConstructorProperties = true)
public class PhoneState implements IPhoneState {

    private final Context context;
    private final IPreference service;
    private TelephonyManager telephony;
    private PlayerPhoneStateListener playerPhoneStateListener;

    @Override
    public void startListener() {
        playerPhoneStateListener = new PlayerPhoneStateListener(context);
        telephony = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        telephony.listen(playerPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        if (service.readNotificationStream())
            PlayerPhoneStateListener.muteNotification(context, true);
    }

    @Override
    public void stopListener() {
        playerPhoneStateListener = null;
        if (telephony != null) {
            telephony.listen(null, PhoneStateListener.LISTEN_NONE);
            telephony = null;
        }
        PlayerPhoneStateListener.muteNotification(context, false);
    }
}
