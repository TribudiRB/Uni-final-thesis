package com.bcuzek.magisterka.service.receiver.action;

import android.content.Context;
import android.content.Intent;

import com.bcuzek.magisterka.service.Service;

import lombok.val;

import static com.bcuzek.magisterka.infrastructure.constans.Action.START_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Extra.EXTRA_SOUND_PARCEL;

public class PlayMessage implements IMessage {

    @Override
    public void receive(Context context, Intent intent) {
        val background = new Intent(context, target).setAction(START_ACTION);
        val extra = intent.getIntExtra(EXTRA_SOUND_PARCEL, 0);
        background.putExtra(EXTRA_SOUND_PARCEL, extra);
        context.startService(background);
    }
}
