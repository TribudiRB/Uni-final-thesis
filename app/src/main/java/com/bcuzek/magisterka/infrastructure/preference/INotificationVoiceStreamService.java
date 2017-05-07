package com.bcuzek.magisterka.infrastructure.preference;

/**
 * Created by robert on 16.08.2016.
 */

public interface INotificationVoiceStreamService {
    boolean readNotificationStream();

    void writeNotificationStream(boolean val);
}
