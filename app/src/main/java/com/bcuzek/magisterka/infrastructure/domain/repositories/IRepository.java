package com.bcuzek.magisterka.infrastructure.domain.repositories;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Statistics;

import io.realm.RealmModel;
import io.realm.RealmResults;

public interface IRepository<T extends RealmModel> {
    T add(T entity);

    RealmResults<T> getAll();

    T getById(int id);

    void delete(int id);

    long sumDuration(final T track);

    void createPlayInfoHistory(Statistics statistics, int soundUUID);

    long count();
}
