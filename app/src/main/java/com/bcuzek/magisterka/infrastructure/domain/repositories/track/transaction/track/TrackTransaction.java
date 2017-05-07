package com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.track;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.Transaction;

public class TrackTransaction extends Transaction<Track> {

    public TrackTransaction(Track track, Statistics statistics) {
        super(track, statistics);
    }

}