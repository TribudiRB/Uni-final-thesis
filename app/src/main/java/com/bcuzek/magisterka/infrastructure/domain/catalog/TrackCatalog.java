package com.bcuzek.magisterka.infrastructure.domain.catalog;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.initialData.InitialData;

import java.util.List;

import rx.Observable;

/**
 * Created by robert on 06.03.2017.
 */

public class TrackCatalog implements ITrackCatalog {

    public List<Track> getTrack() {
        return Observable
                .from(InitialData.values())
                .map(InitialData::create)
                .toList()
                .toBlocking()
                .single();
    }
}
