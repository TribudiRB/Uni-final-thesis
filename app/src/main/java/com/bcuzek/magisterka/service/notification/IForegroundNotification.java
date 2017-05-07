package com.bcuzek.magisterka.service.notification;


import android.app.Notification;

import com.bcuzek.magisterka.service.model.dto.NotificationDTO;

public interface IForegroundNotification {
    Notification create(NotificationDTO action);
}
