package com.bcuzek.magisterka.infrastructure.domain.dataBase.entities;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by robert on 15.07.2016.
 */

@RealmClass
@Setter
@Getter
public class Statistics implements RealmModel {

    @Index
    @PrimaryKey
    private String uuid;

    @Required
    private Date start;

    private long duration;

    public Statistics() {
    }

    public Statistics(String uuid, Date start, long duration) {
        this.uuid = uuid;
        this.start = start;
        this.duration = duration;
    }
}
