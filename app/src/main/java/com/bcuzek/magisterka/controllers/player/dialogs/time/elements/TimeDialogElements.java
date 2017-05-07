package com.bcuzek.magisterka.controllers.player.dialogs.time.elements;

import android.content.Context;

import com.bcuzek.magisterka.controllers.player.dialogs.time.item.ITimeDialogItem;
import com.bcuzek.magisterka.controllers.player.enums.Time;

import java.util.List;

import lombok.RequiredArgsConstructor;
import rx.Observable;

/**
 * Created by robert on 06.10.2016.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class TimeDialogElements implements ITimeDialogElements {

    private final Context context;

    public List<ITimeDialogItem> getListOfTimeItems() {
        return Observable
                .from(Time.values())
                .map(time -> time.createItem(context))
                .toList()
                .toBlocking()
                .single();
    }

    @Override
    public List<String> getTime() {
        return Observable
                .from(Time.values())
                .map(time -> time.translate(context))
                .toList()
                .toBlocking()
                .single();
    }
}
