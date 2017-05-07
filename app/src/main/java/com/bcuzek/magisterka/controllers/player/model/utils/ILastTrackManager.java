package com.bcuzek.magisterka.controllers.player.model.utils;

/**
 * Created by robert on 23.04.2017.
 */

public interface ILastTrackManager {

    int size();

    int get(int index);

    void createLastTrack();

    void addLastTrack(int element);
}
