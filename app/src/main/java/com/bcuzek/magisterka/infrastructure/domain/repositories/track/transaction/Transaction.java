package com.bcuzek.magisterka.infrastructure.domain.repositories.track.transaction;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.IDomainTrack;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;

import io.realm.Realm;

/**
 * Created by robert on 21.04.2017.
 */

public abstract class Transaction<T extends IDomainTrack> implements ITransaction {
    private T clazz;
    private Statistics statistics;

    public Transaction(T clazz, Statistics statistics) {
        this.clazz = clazz;
        this.statistics = statistics;
    }

    public void execute(Realm realm) {
        clazz.addInfo(realm.copyToRealm(statistics));
    }
}
