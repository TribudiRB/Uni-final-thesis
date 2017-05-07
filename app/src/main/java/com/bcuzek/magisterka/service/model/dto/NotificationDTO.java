package com.bcuzek.magisterka.service.model.dto;

import android.app.PendingIntent;

import com.bcuzek.magisterka.utils.notificationTimeCalculator.INotificationTimeCalculator;

import lombok.Getter;
import lombok.experimental.Builder;
import lombok.extern.java.Log;

@Builder
@Log
@Getter
public class NotificationDTO {
    private String contentTitle;
    private String contentText;
    private INotificationTimeCalculator calculator;
    private int drawable;
    private int actionString;
    private PendingIntent intent;

    public void update(long time) {
        this.contentText = calculator.calculateTime(time);
    }
}
