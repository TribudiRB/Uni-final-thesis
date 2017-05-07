package com.bcuzek.magisterka.infrastructure.domain.dataBase.entities;

import java.util.Date;

import io.realm.RealmList;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by robert on 5.12.2016.
 */

@Setter
@Getter
@RealmClass
public class Record implements IDomainTrack {

    @Index
    @PrimaryKey
    private int uuid;

    @Required
    private String name;

    private int icon;

    @Required
    private String title;

    private Date date;

    private RealmList<Statistics> statisticsList;

    public Record() {
    }

    public void addInfo(Statistics statistics) {
        this.statisticsList.add(statistics);
    }
}
