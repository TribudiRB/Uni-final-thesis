package com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu;

import android.widget.PopupMenu;

import com.bcuzek.magisterka.controllers.soundList.custom.CustomDTO;

/**
 * Created by robert on 06.03.2017.
 */

public interface IExtrasPopupMenuListener extends PopupMenu.OnMenuItemClickListener {
    void setCustomTransferModel(CustomDTO model);
}
