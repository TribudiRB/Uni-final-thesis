package com.bcuzek.magisterka.infrastructure.intentManager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by robert on 06.11.2016.
 */

public interface IIntentManager extends IClassIntentHolder, IIntentAction {
    PendingIntent openPlayer();

    PendingIntent play();

    PendingIntent pause();

    PendingIntent restart();

    PendingIntent stopCall();

    PendingIntent startCall();

    PendingIntent play(int id);

    PendingIntent stopPlay();

    PendingIntent stopService();

    PendingIntent exitApplication();

    Intent muteCall();

    Intent openApplicationDetailSettings(Activity activity);
}
