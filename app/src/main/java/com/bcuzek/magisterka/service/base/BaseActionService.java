package com.bcuzek.magisterka.service.base;

import android.app.Service;
import android.content.Intent;

import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.service.actionHolder.IActionHolder;
import com.bcuzek.magisterka.service.interfaces.IServiceAction;

import javax.inject.Inject;

/**
 * Created by robert on 09.04.2017.
 */

public abstract class BaseActionService extends Service implements IServiceAction {

    @Inject
    IActionHolder action;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    public abstract void initComponent();

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (intent != null && intent.getAction() != null)
            action.getAction(intent.getAction()).action(this, intent);
        return START_STICKY;
    }

    @Override
    public void shutdownService() {
        stopForeground(true);
        stopSelf();
    }
}
