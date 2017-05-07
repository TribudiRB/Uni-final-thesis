package com.bcuzek.magisterka.infrastructure.domain.dataBase.entities;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by robert on 21.11.2016.
 */

public interface IDomainTrack extends RealmModel {
    void addInfo(Statistics statistics);

    RealmList<Statistics> getStatisticsList();
}
