package com.bcuzek.magisterka.utils.string;

import android.content.Context;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class StringUtils implements IStringUtils {
    private final Context context;

    @Override
    public String getStringById(int id) {
        return context.getString(id);
    }

}
