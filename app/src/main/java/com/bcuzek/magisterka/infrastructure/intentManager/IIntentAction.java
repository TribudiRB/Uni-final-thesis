package com.bcuzek.magisterka.infrastructure.intentManager;

import android.app.PendingIntent;

/**
 * Created by robert on 06.11.2016.
 */

public interface IIntentAction {
    void send(PendingIntent intent);
}
