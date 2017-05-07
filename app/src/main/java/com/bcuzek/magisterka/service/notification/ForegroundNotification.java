package com.bcuzek.magisterka.service.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.bcuzek.magisterka.service.model.dto.NotificationDTO;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.experimental.Builder;

@Builder
public class ForegroundNotification implements IForegroundNotification {
    private Context context;
    private PendingIntent contentIntent;
    private PendingIntent exitIntent;
    private IStringUtils stringUtils;
    private int icon;
    private int powerOffIcon;

    public Notification create(NotificationDTO action) {
        return ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                ? setStyle(action) : getBuilder(action)).build();
    }

    @SuppressLint("NewApi")
    private Notification.Builder setStyle(NotificationDTO modelAction) {
        return getBuilder(modelAction).setStyle(new Notification.MediaStyle());
    }

    private Notification.Builder getBuilder(NotificationDTO modelAction) {
        return new Notification.Builder(context)
                .setContentTitle(modelAction.getContentTitle())
                .setContentText(modelAction.getContentText())
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent)
                .addAction(modelAction.getDrawable(), stringUtils.getStringById(modelAction.getActionString()), modelAction.getIntent())
                .addAction(powerOffIcon, stringUtils.getStringById(modelAction.getActionString()), exitIntent);
    }

}