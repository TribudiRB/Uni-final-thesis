package com.bcuzek.magisterka.controllers.settings.preferenceChange.theme;

import android.preference.ListPreference;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

/**
 * Created by robert on 06.03.2017.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class UpdateTheme implements IUpdateTheme {

    private final IPreference service;
    private Map<Integer, Theme> color = new HashMap<Integer, Theme>() {
        {
            put(Theme.RED.getId(), Theme.RED);
            put(Theme.BLUE.getId(), Theme.BLUE);
            put(Theme.SALOMON.getId(), Theme.SALOMON);
            put(Theme.PEACH_PUFF.getId(), Theme.PEACH_PUFF);
            put(Theme.OLIVE.getId(), Theme.OLIVE);
            put(Theme.CHOCOLATE.getId(), Theme.CHOCOLATE);
            put(Theme.GOLD.getId(), Theme.GOLD);
            put(Theme.GREEN.getId(), Theme.GREEN);
        }
    };

    @Override
    public void update(ListPreference preference, MainActivity activity) {
        service.saveStyle(color.get(Integer.parseInt(preference.getValue())).getId());
        activity.updateViewStyle();
    }

}
