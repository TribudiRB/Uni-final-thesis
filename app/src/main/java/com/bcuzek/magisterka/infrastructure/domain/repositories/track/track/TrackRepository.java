package com.bcuzek.magisterka.infrastructure.domain.repositories.track.track;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.infrastructure.domain.repositories.Repository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction.track.TrackTransaction;
/**
 * Created by robert on 22.12.2016.
 */
public class TrackRepository extends Repository<Track> implements ITrackRepository {

    public TrackRepository(IDatabaseContext databaseContext) {
        super(Track.class, databaseContext);
    }

    @Override
    public void createPlayInfoHistory(final Statistics statistics, final int soundUUID) {
        databaseContext.getRealmInstance().executeTransaction(new TrackTransaction(getById(soundUUID), statistics));
    }

}
