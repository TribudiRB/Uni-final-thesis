package com.bcuzek.magisterka.service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.service.receiver.IBroadcastHolder;

import javax.inject.Inject;

import static com.bcuzek.magisterka.infrastructure.constans.Action.START_PLAY_MUSIC;

public class PlayerReceiver extends BroadcastReceiver {

    @Inject
    IBroadcastHolder broadcast;

    public PlayerReceiver() {
        MagisterkaApplication.component().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        broadcast.getBroadcast(intent.getAction().equals(START_PLAY_MUSIC))
                .receive(context, intent);
    }
}
