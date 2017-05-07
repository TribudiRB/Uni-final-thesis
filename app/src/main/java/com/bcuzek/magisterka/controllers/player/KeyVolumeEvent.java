package com.bcuzek.magisterka.controllers.player;

import android.media.AudioManager;
import android.view.KeyEvent;
import android.widget.SeekBar;

import lombok.AllArgsConstructor;
import lombok.val;

import static android.view.KeyEvent.KEYCODE_VOLUME_DOWN;
import static android.view.KeyEvent.KEYCODE_VOLUME_UP;

/**
 * Created by robert on 02.08.2016.
 */
@AllArgsConstructor
public class KeyVolumeEvent implements IOnVolumeKey {
    private static final int VALUE_UP = 1;
    private static final int VALUE_DOWN = -1;
    private static final int VALUE_UNMODIFIED = 0;
    private SeekBar volumeBar;
    private AudioManager audioManager;

    private int getValue(int validatorType) {
        switch (validatorType) {
            case KEYCODE_VOLUME_UP:
                return VALUE_UP;
            case KEYCODE_VOLUME_DOWN:
                return VALUE_DOWN;
            default:
                return VALUE_UNMODIFIED;
        }
    }

    @Override
    public boolean onVolume(int keyCode, KeyEvent event) {
        val keyPressed = getValue(keyCode);
        val currentValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeBar.setProgress(currentValue + keyPressed);
        return false;
    }
}