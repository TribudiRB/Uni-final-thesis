package com.bcuzek.magisterka.infrastructure.preference;

/**
 * Created by robert on 16.08.2016.
 */

public interface IThemeService {
    int readStyle();

    void saveStyle(int value);
}
