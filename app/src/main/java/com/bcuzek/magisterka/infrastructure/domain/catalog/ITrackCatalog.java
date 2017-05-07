package com.bcuzek.magisterka.infrastructure.domain.catalog;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;

import java.util.List;

/**
 * Created by robert on 06.03.2017.
 */

public interface ITrackCatalog {

    List<Track> getTrack();
}
