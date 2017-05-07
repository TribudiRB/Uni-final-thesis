package com.bcuzek.magisterka.controllers.base;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.bcuzek.magisterka.controllers.settings.PlaySoundMusicCall;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import javax.inject.Inject;

public class PlayerPhoneStateListener extends PhoneStateListener {

    private static final String callPermission = "android.permission.CALL_PRIVILEGED";

    @Inject
    IIntentManager creator;
    @Inject
    IPreference service;
    private boolean isPhoneCalling = false;
    private Context context;

    public PlayerPhoneStateListener(Context context) {
        this.context = context;
        MagisterkaApplication.component().inject(this);
    }

    public static void muteNotification(Context context, boolean mute) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, mute);
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                if (PlaySoundMusicCall.NO != service.canPlayRingtone())
                    rejectCall(AudioManager.RINGER_MODE_NORMAL);
                if (isPhoneCalling) {
                    creator.send(creator.startCall());
                    isPhoneCalling = false;
                }
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                if (PlaySoundMusicCall.NO != service.canPlayRingtone())
                    rejectCall(AudioManager.RINGER_MODE_SILENT);
                creator.send(creator.stopCall());
                isPhoneCalling = true;
                break;
        }
    }

    private void rejectCall(int mode) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(mode);
        context.sendOrderedBroadcast(creator.muteCall(), callPermission);
    }
}
