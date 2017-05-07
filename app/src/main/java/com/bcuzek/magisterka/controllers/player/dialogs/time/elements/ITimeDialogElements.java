package com.bcuzek.magisterka.controllers.player.dialogs.time.elements;

import com.bcuzek.magisterka.controllers.player.dialogs.time.item.ITimeDialogItem;

import java.util.List;

/**
 * Created by robert on 06.10.2016.
 */

public interface ITimeDialogElements {

    List<ITimeDialogItem> getListOfTimeItems();

    List<String> getTime();
}
