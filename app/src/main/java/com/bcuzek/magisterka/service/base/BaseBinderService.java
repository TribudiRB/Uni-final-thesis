package com.bcuzek.magisterka.service.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.bcuzek.magisterka.service.Service;
import com.bcuzek.magisterka.service.interfaces.IStatusWatcher;

import rx.Observable;

/**
 * Created by robert on 09.04.2017.
 */

public abstract class BaseBinderService extends BaseActionService implements IStatusWatcher {
    protected final IBinder mBinder = new LocalBinder();
    protected IStatusWatcher statusWatcher = this;

    @Override
    public IBinder onBind(final Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public BaseBinderService getService() {
            return BaseBinderService.this;
        }
    }

    public static boolean isServiceRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return Observable
                .from(activityManager.getRunningServices(Integer.MAX_VALUE))
                .filter(info -> info.service.getClassName().equals(Service.class.getSimpleName()))
                .map(e -> true)
                .defaultIfEmpty(false)
                .toBlocking()
                .first();
    }

    public void registerStatusWatcher(final IStatusWatcher IStatusWatcher) {
        statusWatcher = IStatusWatcher;
    }

    public void unregisterPlayerStatusWatcher() {
        statusWatcher = this;
    }

}
