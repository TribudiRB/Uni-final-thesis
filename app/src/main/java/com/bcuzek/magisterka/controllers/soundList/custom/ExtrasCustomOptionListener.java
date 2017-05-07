package com.bcuzek.magisterka.controllers.soundList.custom;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu.IExtrasPopupMenuListener;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;

import javax.inject.Inject;

import lombok.val;

public class ExtrasCustomOptionListener implements View.OnClickListener {
    @Inject
    IExtrasPopupMenuListener listener;
    private Context context;
    private CustomDTO model;

    public ExtrasCustomOptionListener(Context context) {
        MagisterkaApplication.component().inject(this);
        this.context = context;
    }

    public void setModel(CustomDTO model) {
        this.model = model;
    }

    public void onClick(View v) {
        val popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.remove_menu, popupMenu.getMenu());
        listener.setCustomTransferModel(model);
        popupMenu.setOnMenuItemClickListener(listener);
        popupMenu.show();
    }
}
