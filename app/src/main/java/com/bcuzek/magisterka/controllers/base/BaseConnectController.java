package com.bcuzek.magisterka.controllers.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.service.Service;
import com.bcuzek.magisterka.service.base.BaseBinderService;
import com.bcuzek.magisterka.service.interfaces.IStatusWatcher;
import com.bcuzek.magisterka.service.interfaces.ServiceState;
import com.bcuzek.magisterka.service.model.TimeDelay;
import com.bcuzek.magisterka.service.timer.ISubscription;

import javax.inject.Inject;

/**
 * Created by robert on 04.03.2017.
 */

public abstract class BaseConnectController extends BaseController {

    protected static Service service;
    protected boolean mIsBound;

    @Inject
    ISubscription timer;

    @Inject
    IIntentManager manager;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            BaseConnectController.service = (Service) ((BaseBinderService.LocalBinder) service).getService();
            BaseConnectController.service.registerStatusWatcher(updateStatusWatcher());
            displayCurrentState();
        }

        public void onServiceDisconnected(ComponentName className) {
            service = null;
        }
    };

    protected abstract void displayCurrentState();

    protected abstract IStatusWatcher updateStatusWatcher();

    private void doBindService() {
        if (!Service.isServiceRunning(getActivity())) {
            Intent intent = new Intent(getActivity(), Service.class);
            getActivity().startService(intent);
        }

        getActivity().bindService(new Intent(getActivity(), Service.class),
                mConnection,
                Context.BIND_AUTO_CREATE);
        mIsBound = true;
        timer.setDelay(TimeDelay.Fast);
    }

    private void doUnbindService() {
        if (mIsBound && service != null) {
            service.unregisterPlayerStatusWatcher();
            getActivity().unbindService(mConnection);
            mIsBound = false;
            timer.setDelay(TimeDelay.Slow);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        doBindService();
    }

    @Override
    public void onPause() {
        super.onPause();
        doUnbindService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (service != null && service.getCurrentState() != ServiceState.Playing)
            manager.send(manager.stopService());
    }


}
