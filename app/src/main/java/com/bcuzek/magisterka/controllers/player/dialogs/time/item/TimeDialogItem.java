package com.bcuzek.magisterka.controllers.player.dialogs.time.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by robert on 02.08.2016.
 */
@Getter
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class TimeDialogItem implements ITimeDialogItem {
    private final int value;
    private final String name;
}
