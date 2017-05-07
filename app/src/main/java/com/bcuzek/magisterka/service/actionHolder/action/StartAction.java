package com.bcuzek.magisterka.service.actionHolder.action;

import android.content.Intent;

import com.bcuzek.magisterka.service.interfaces.IServiceAction;

import static com.bcuzek.magisterka.infrastructure.constans.Extra.EXTRA_SOUND_PARCEL;

public class StartAction implements ServiceAction {
    private int lastId;

    @Override
    public void action(IServiceAction action, Intent intent) {
        if (intent.hasExtra(EXTRA_SOUND_PARCEL))
            lastId = intent.getIntExtra(EXTRA_SOUND_PARCEL, 1);
        action.onStart(lastId);
    }
}
