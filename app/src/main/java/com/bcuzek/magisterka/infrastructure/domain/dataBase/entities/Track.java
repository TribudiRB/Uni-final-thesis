package com.bcuzek.magisterka.infrastructure.domain.dataBase.entities;

import io.realm.RealmList;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by robert on 15.07.2016.
 */

@RealmClass
@Getter
@Setter
public class Track implements IDomainTrack {

    protected RealmList<Statistics> statisticsList;
    @Index
    @PrimaryKey
    private int uuid;
    private int name;
    private int icon;
    private int title;

    public Track() {
    }

    public Track(int uuid, int name, int icon, int title) {
        this.uuid = uuid;
        this.name = name;
        this.icon = icon;
        this.title = title;
    }

    public void addInfo(Statistics statistics) {
        this.statisticsList.add(statistics);
    }

}
