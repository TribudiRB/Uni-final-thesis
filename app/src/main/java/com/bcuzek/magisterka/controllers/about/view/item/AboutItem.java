package com.bcuzek.magisterka.controllers.about.view.item;

import com.bcuzek.magisterka.controllers.about.view.AboutIdentifier;

import java.util.ArrayList;

import lombok.Getter;
import lombok.experimental.Builder;
import rx.Observable;

/**
 * Created by robert on 31.08.2016.
 */
@Getter
@Builder
public final class AboutItem {
    private int header;
    private int subHeader;
    private int icon;
    private AboutIdentifier identifier;
    private ArrayList<AboutItem> items;

    public void addChild(AboutItem... item) {
        if (items == null)
            items = new ArrayList<>();
        Observable.from(item).forEach(items::add);
    }

}
