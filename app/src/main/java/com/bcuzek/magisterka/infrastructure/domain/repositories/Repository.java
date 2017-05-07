package com.bcuzek.magisterka.infrastructure.domain.repositories;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.IDomainTrack;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class Repository<T extends IDomainTrack> implements IRepository<T> {
    protected IDatabaseContext databaseContext;
    Class<T> clazz;

    public Repository(Class<T> clazz, IDatabaseContext databaseContext) {
        this.clazz = clazz;
        this.databaseContext = databaseContext;
    }

    @Override
    public T add(T entity) {
        Realm realm = databaseContext.getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealm(entity);
        realm.commitTransaction();
        return entity;
    }

    @Override
    public RealmResults<T> getAll() {
        return databaseContext
                .getRealmInstance()
                .where(this.clazz)
                .findAll();
    }

    @Override
    public T getById(int id) {
        return databaseContext
                .getRealmInstance()
                .where(this.clazz)
                .equalTo("uuid", id)
                .findFirst();
    }

    @Override
    public void delete(int id) {
        Realm realm = databaseContext.getRealmInstance();
        realm.beginTransaction();
        realm.where(this.clazz).equalTo("uuid", id).findAll().deleteFirstFromRealm();
        realm.commitTransaction();
    }

    @Override
    public long sumDuration(T track) {
        return track
                .getStatisticsList()
                .sum("duration")
                .longValue();
    }

    public long count() {
        return databaseContext
                .getRealmInstance()
                .where(this.clazz)
                .count();
    }

}
