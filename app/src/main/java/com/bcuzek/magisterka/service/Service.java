package com.bcuzek.magisterka.service;

import android.content.Intent;
import android.widget.Toast;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.Time;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.NotificationModelQualifiers;
import com.bcuzek.magisterka.infrastructure.musicPlayer.IMusicPlayer;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.infrastructure.session.ISession;
import com.bcuzek.magisterka.service.base.BaseBinderService;
import com.bcuzek.magisterka.service.interfaces.ServiceState;
import com.bcuzek.magisterka.service.model.dto.NotificationDTO;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.service.notification.IForegroundNotification;
import com.bcuzek.magisterka.service.phoneState.IPhoneState;
import com.bcuzek.magisterka.service.timer.ISubscription;

import javax.inject.Inject;
import javax.inject.Named;

public class Service extends BaseBinderService {
    public static final int NOTIFICATION_ID = 399;

    @Inject
    ISession session;

    @Inject
    IMusicPlayer player;

    @Inject
    IForegroundNotification notification;

    @Inject
    IPreference service;

    @Inject
    ITimeHolder timeHolder;

    @Inject
    IPhoneState phoneState;

    @Inject
    @Named(NotificationModelQualifiers.PAUSE)
    NotificationDTO pauseModel;

    @Inject
    @Named(NotificationModelQualifiers.PLAY)
    NotificationDTO playModel;

    @Inject
    ISubscription timer;

    private boolean isAutoPaused = false;
    private ServiceState currentState = ServiceState.Stopped;

    public ServiceState getCurrentState() {
        return currentState;
    }

    @Override
    public void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        displayNotification(pauseModel);
        statusWatcher.onStateChange();
        return true;
    }

    @Override
    public void onTimeChanged(long milliseconds) {
        displayNotification(pauseModel);
    }

    @Override
    public void onStateChange() {
        switch (currentState) {
            case Stopped:
                session.stopSession();
                stopForeground(true);
                break;
            case Paused:
                displayNotification(playModel);
                break;
        }
    }

    @Override
    public void onStart(int sound) {
        if (currentState == ServiceState.Playing)
            onStop();
        session.startSession(sound);
        currentState = ServiceState.Playing;
        phoneState.startListener();
        timeHolder.setNewStartTime();
        timer.start(() -> {
            long currentTime = timeHolder.getCalculatedTime();
            checkIsNotEnd(currentTime);
            session.update(timer.getDelay() * 100);
            statusWatcher.onStateChange();
            statusWatcher.onTimeChanged(timeHolder.getCurrentTime());
            if (currentTime < service.readTimePlay())
                timeHolder.setCurrentTime(currentTime);
        });
        playMusic(sound);
    }

    private void playMusic(int sound) {
        try {
            player.play(sound);
        } catch (Exception e) {
            Toast.makeText(this, R.string.cannot_find_file, Toast.LENGTH_SHORT).show();
            onStop();
        }
    }

    private void checkIsNotEnd(long milliseconds) {
        int timePlay = service.readTimePlay();
        if (timePlay != Time.MAX_TIME && timePlay - milliseconds < 0)
            onStop();
    }

    @Override
    public void onPause() {
        currentState = ServiceState.Paused;
        player.stop();
        phoneState.stopListener();
        timer.stop();
        timeHolder.updatePlayTime();
        statusWatcher.onStateChange();
    }

    private void displayNotification(NotificationDTO notificationDTO) {
        long currentTime = timeHolder.getCurrentTime();
        pauseModel.update(currentTime);
        playModel.update(currentTime);
        startForeground(NOTIFICATION_ID, notification.create(notificationDTO));
    }

    @Override
    public void onPhoneCallStart() {
        if (isAutoPaused) {
            onStart(service.getLastUsed().get(0));
            isAutoPaused = false;
        }
    }

    @Override
    public void onPhoneCallStop() {
        onPause();
        isAutoPaused = true;
    }

    @Override
    public void restartTime() {
        timeHolder.restartTime();
        if (currentState == ServiceState.Playing)
            timeHolder.setNewStartTime();
        else
            onStop();
    }

    @Override
    public void exitApplication() {
        onStop();
        shutdownService();
        System.exit(0);
    }

    @Override
    public void onStop() {
        timeHolder.restartTime();
        currentState = ServiceState.Stopped;
        if (timer.isUnsubscribed())
            onPause();
        if (session.isRunning()) {
            session.stopSession();
            player.stop();
            timeHolder.restartPlayedTime();
            phoneState.stopListener();
            timeHolder.restartTime();
            statusWatcher.onStateChange();
            stopForeground(true);
        }
    }

}
