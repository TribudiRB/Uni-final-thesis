package com.bcuzek.magisterka.utils.color;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class ColorUtils implements IColorUtils {
    private final IThemeUtils utils;

    public int getCurrentColor(Activity activity) {
        return ContextCompat.getColor(activity, utils.getColor());
    }
}
