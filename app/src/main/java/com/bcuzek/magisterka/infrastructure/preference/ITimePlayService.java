package com.bcuzek.magisterka.infrastructure.preference;

/**
 * Created by robert on 16.08.2016.
 */

public interface ITimePlayService {
    int readTimePlay();

    void writeTimePlay(int value);
}
