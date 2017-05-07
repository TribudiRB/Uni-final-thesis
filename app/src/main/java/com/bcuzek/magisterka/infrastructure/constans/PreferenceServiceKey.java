package com.bcuzek.magisterka.infrastructure.constans;

/**
 * Created by robert on 17.07.2016.
 */

public interface PreferenceServiceKey {
    String PREFERENCE_NAMESPACE         = "com.buczek.magisterka.preferences";
    String THEME_KEY                    = "com.buczek.magisterka.preferences.theme.selected.key";
    String FIRST_RUN_KEY                = "com.buczek.magisterka.preferences.first.run.preference.key";
    String TIME_SAVED_KEY               = "com.buczek.magisterka.preferences.current.selected.play.time";
    String FIRST_RECORD_RUN_KEY         = "com.buczek.magisterka.preferences.first.record.fragment.run.key";
    String CAN_DISPLAY_DIALOG           = "com.buczek.magisterka.preferences.permission.can.display.dialog";
    String APP_RUN_TIMES_KEY            = "com.buczek.magisterka.preferences.app.run.times.counter.key";
    String CALL_PLAY_SOUND              = "com.buczek.magisterka.preferences.app.call.play.volume.key";
    String PHONE_MUTE_KEY               = "com.buczek.magisterka.preferences.mute.all.notification.key";
    String LAST_TRACK_KEY               = "com.buczek.magisterka.preferences.last.use.json.saved.data.key";
    String PREFERENCES_THEME_TYPE       = "com.buczek.magisterka.preferences.theme.key";
    String PREFERENCES_CALL_VOLUME_TYPE = "com.buczek.magisterka.preferences.record.key";
}
