package com.bcuzek.magisterka.controllers.player.dialogs.time;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.controllers.player.dialogs.time.elements.ITimeDialogElements;
import com.bcuzek.magisterka.controllers.player.dialogs.time.item.ITimeDialogItem;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import java.util.List;

import lombok.experimental.Builder;
import rx.Observable;

@Builder
public class SelectOneItemDialog {
    private List<ITimeDialogItem> items;
    private IPreference service;
    private IThemeUtils utils;
    private ITimeDialogElements elements;
    private int title;
    private int negativeText;

    public void build(Activity activity, ISelectOnItemListener listener) {
        new MaterialDialog
                .Builder(activity)
                .title(title)
                .items(elements.getTime())
                .itemsCallbackSingleChoice(getPosition(service.readTimePlay()), (dialog, view, which, text) -> {
                    listener.onSelect(SelectOneItemDialog.this.items.get(which));
                    return true;
                })
                .negativeText(negativeText)
                .titleColorRes(utils.getColor())
                .negativeColorRes(utils.getColor())
                .positiveColorRes(utils.getColor())
                .build()
                .show();
    }

    private int getPosition(int selectedItem) {
        return Observable
                .from(items)
                .filter(i -> i.getValue() == selectedItem)
                .map(iOneItem -> items.indexOf(iOneItem))
                .toBlocking()
                .first();
    }

    public interface ISelectOnItemListener {
        void onSelect(ITimeDialogItem item);
    }
}
