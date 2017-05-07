package com.bcuzek.magisterka.infrastructure.domain.dataBase;

import io.realm.Realm;

public interface IDatabaseContext {
    void setup();

    Realm getRealmInstance();
}
