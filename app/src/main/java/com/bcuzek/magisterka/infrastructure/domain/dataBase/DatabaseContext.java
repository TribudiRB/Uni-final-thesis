package com.bcuzek.magisterka.infrastructure.domain.dataBase;

import android.content.Context;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.initialTransaction.ITrackInitialTransaction;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class DatabaseContext implements IDatabaseContext {
    private final Context context;
    private final ITrackInitialTransaction transaction;
    private RealmConfiguration realmConfiguration;
    private int schemaVersion = 1;

    public void setup() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration
                    .Builder(context)
                    .initialData(transaction)
                    .schemaVersion(schemaVersion)
                    .deleteRealmIfMigrationNeeded()
                    .build();

            Realm.setDefaultConfiguration(realmConfiguration);
        } else throw new DataBaseException();
    }

    @Override
    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }
}
