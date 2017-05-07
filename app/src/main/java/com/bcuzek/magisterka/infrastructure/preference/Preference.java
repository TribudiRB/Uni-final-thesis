package com.bcuzek.magisterka.infrastructure.preference;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.controllers.player.enums.Time;
import com.bcuzek.magisterka.controllers.settings.PlaySoundMusicCall;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceDefaultValues.NOTIFICATION_DEFAULT;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceDefaultValues.PREFERENCES_DEFAULT_EMPTY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceDefaultValues.PREFERENCES_FIRST_RUN;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceDefaultValues.RECORDS_DISPLAY_DEFAULT;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.APP_RUN_TIMES_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.CALL_PLAY_SOUND;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.CAN_DISPLAY_DIALOG;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.FIRST_RECORD_RUN_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.FIRST_RUN_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.LAST_TRACK_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.PHONE_MUTE_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.PREFERENCE_NAMESPACE;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.THEME_KEY;
import static com.bcuzek.magisterka.infrastructure.constans.PreferenceServiceKey.TIME_SAVED_KEY;

/**
 * Created by robert on 16.08.2016.
 */

public class Preference implements IPreference {
    private final SharedPreferences preferences;

    public Preference(Application application) {
        this.preferences = application.getSharedPreferences(PREFERENCE_NAMESPACE, Context.MODE_PRIVATE);
    }

    @Override
    public int readAppRunTime() {
        return preferences.getInt(APP_RUN_TIMES_KEY, 0);
    }

    @Override
    public PlaySoundMusicCall canPlayRingtone() {
        return PlaySoundMusicCall.toEnum(preferences.getString(CALL_PLAY_SOUND, PlaySoundMusicCall.YES.toString()));
    }

    @Override
    public int readStyle() {
        return preferences.getInt(THEME_KEY, Theme.BLUE.getId());
    }

    @Override
    public int readTimePlay() {
        return preferences.getInt(TIME_SAVED_KEY, Time.FIFTEEN_MINUTE.getTime());
    }

    @Override
    public boolean isFirstApplicationRun() {
        return preferences.getBoolean(FIRST_RUN_KEY, PREFERENCES_FIRST_RUN);
    }

    @Override
    public void saveStyle(int value) {
        preferences.edit().putInt(THEME_KEY, value).apply();
    }

    @Override
    public void writeTimePlay(int value) {
        preferences.edit().putInt(TIME_SAVED_KEY, value).apply();
    }

    @Override
    public void setIsFirstApplicationRun(boolean bool) {
        preferences.edit().putBoolean(FIRST_RUN_KEY, bool).apply();
    }

    @Override
    public void writeRigtoneStream(PlaySoundMusicCall value) {
        preferences.edit().putString(CALL_PLAY_SOUND, value.toString()).apply();
    }

    @Override
    public void writeNotificationStream(boolean val) {
        preferences.edit().putBoolean(PHONE_MUTE_KEY, val).apply();
    }

    @Override
    public boolean readNotificationStream() {
        return preferences.getBoolean(PHONE_MUTE_KEY, NOTIFICATION_DEFAULT);
    }

    @Override
    public void writeUsedList(List<Integer> list) {
        preferences.edit().putString(LAST_TRACK_KEY, new Gson().toJson(list)).apply();
    }

    @Override
    public List<Integer> getLastUsed() {
        return new Gson().fromJson(preferences.getString(LAST_TRACK_KEY, PREFERENCES_DEFAULT_EMPTY),
                new TypeToken<ArrayList<Integer>>() {
                }.getType());
    }

    @Override
    public void updateCommissioning() {
        int value = readAppRunTime();
        preferences.edit().putInt(APP_RUN_TIMES_KEY, ++value).apply();
    }

    @Override
    public boolean isFirstTimeDisplayDialog() {
        return preferences.getBoolean(FIRST_RECORD_RUN_KEY, RECORDS_DISPLAY_DEFAULT);
    }

    @Override
    public void setIsFirstTimeDisplayDialog(boolean bool) {
        preferences.edit().putBoolean(FIRST_RECORD_RUN_KEY, bool).apply();
    }

    @Override
    public boolean readCanDisplayDialog() {
        return preferences.getBoolean(CAN_DISPLAY_DIALOG, true);
    }

    @Override
    public void writeCanDisplayDialog(boolean val) {
        preferences.edit().putBoolean(CAN_DISPLAY_DIALOG, val).apply();
    }
}
