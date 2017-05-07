package com.bcuzek.magisterka.infrastructure.intentManager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.KeyEvent;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static com.bcuzek.magisterka.infrastructure.constans.Action.EXIT_APPLICATION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.PAUSE_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.RESTART_TIME_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.START_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.START_CALL_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.START_PLAY_MUSIC;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_CALL_ACTION;
import static com.bcuzek.magisterka.infrastructure.constans.Action.STOP_SERVICE;
import static com.bcuzek.magisterka.infrastructure.constans.Extra.EXTRA_SOUND_PARCEL;

/**
 * Created by robert on 06.11.2016.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class IntentManager implements IIntentManager {
    private final Context context;

    @SneakyThrows(PendingIntent.CanceledException.class)
    public void send(PendingIntent intent) {
        intent.send();
    }

    public PendingIntent openPlayer() {
        return createPending(createIntent(application, Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public PendingIntent play() {
        return createPending(createIntent(broadcastReceiver, START_ACTION));
    }

    public PendingIntent pause() {
        return createPending(createIntent(broadcastReceiver, PAUSE_ACTION));
    }

    public PendingIntent restart() {
        return createPending(createIntent(broadcastReceiver, RESTART_TIME_ACTION));
    }

    public PendingIntent stopCall() {
        return createPending(createIntent(broadcastReceiver, STOP_CALL_ACTION));
    }

    public PendingIntent startCall() {
        return createPending(createIntent(broadcastReceiver, START_CALL_ACTION));
    }

    public PendingIntent play(int id) {
        return createPending(createIntent(broadcastReceiver, START_PLAY_MUSIC)
                .putExtra(EXTRA_SOUND_PARCEL, id));
    }

    public PendingIntent stopPlay() {
        return createPending(createIntent(broadcastReceiver, STOP_ACTION));
    }

    public PendingIntent stopService() {
        return createPending(createIntent(broadcastReceiver, STOP_SERVICE));
    }

    public PendingIntent exitApplication() {
        return createPending(createIntent(broadcastReceiver, EXIT_APPLICATION));
    }

    public Intent muteCall() {
        return new Intent(Intent.ACTION_MEDIA_BUTTON)
                .putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
    }

    @Override
    public Intent openApplicationDetailSettings(Activity activity) {
        return new Intent()
                .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .setData(Uri.parse("package:" + activity.getPackageName()))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
    }

    private Intent createIntent(Class clazz, String action) {
        return new Intent(context, clazz).setAction(action);
    }

    private PendingIntent createPending(Intent intent) {
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
