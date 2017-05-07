package com.bcuzek.magisterka.controllers.player.enums;

import android.content.Context;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.player.dialogs.time.item.ITimeDialogItem;
import com.bcuzek.magisterka.controllers.player.dialogs.time.item.TimeDialogItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by robert on 06.03.2017.
 */
@Getter
@AllArgsConstructor
public enum Time {
    ONE_MINUTE(0xEA60, R.string.time_one_minute),
    FIVE_MINUTE(0x493E0, R.string.five_sounds),
    TEN_MINUTE(0x927C0, R.string.ten_minute),
    FIFTEEN_MINUTE(0xDBBA0, R.string.fifteen_minutes),
    TWENTY_MINUTE(0x124F80, R.string.twenty_minutes),
    THIRTY_MINUTE(0x1B7740, R.string.thirty_minutes),
    FORTY_FIFE_MINUTE(0x2932E0, R.string.forty_five_minutes),
    ONE_HOUR(0x36EE80, R.string.one_hour_minutes),
    MAX_TIME(0x3929CA0, R.string.infinity);

    private final int time;
    private final int resourceId;

    public String translate(Context context) {
        return context.getString(resourceId);
    }

    public ITimeDialogItem createItem(Context context) {
        return new TimeDialogItem(time, context.getString(resourceId));
    }
}