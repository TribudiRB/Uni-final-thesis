package com.bcuzek.magisterka.infrastructure.domain.dataBase.initialTransaction;

import com.bcuzek.magisterka.infrastructure.domain.catalog.ITrackCatalog;

import io.realm.Realm;
import lombok.RequiredArgsConstructor;
import rx.Observable;

/**
 * Created by robert on 16.03.2017.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class TrackInitialTransaction implements ITrackInitialTransaction {

    private final ITrackCatalog soundCatalog;

    @Override
    public void execute(Realm realm) {
        Observable.from(soundCatalog.getTrack()).subscribe(realm::copyToRealm);
    }

}
