package com.bcuzek.magisterka.controllers.about.view.item;

import java.util.List;

import lombok.experimental.Builder;
import rx.Observable;

/**
 * Created by robert on 01.09.2016.
 */
@Builder
public class AboutListItems implements IAboutListItems {

    private final AboutItem parent;
    private final AboutItem developer;
    private final AboutItem version;
    private final AboutItem about;
    private final AboutItem contact;
    private final AboutItem permission;

    public List<AboutItem> createListItems() {
        parent.addChild(developer, version, permission, about, contact);
        return Observable
                .just(parent)
                .toList()
                .toBlocking()
                .first();
    }
}
