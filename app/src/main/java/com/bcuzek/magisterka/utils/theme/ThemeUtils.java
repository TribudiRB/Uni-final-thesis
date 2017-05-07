package com.bcuzek.magisterka.utils.theme;

import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import lombok.RequiredArgsConstructor;
import rx.Observable;

@RequiredArgsConstructor(suppressConstructorProperties = true)

public class ThemeUtils implements IThemeUtils {

    private final IPreference service;

    public int getColor() {
        return Observable
                .from(Theme.values())
                .filter(e -> e.getId() == service.readStyle())
                .toBlocking()
                .first()
                .getColor();
    }
}
