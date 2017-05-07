package com.bcuzek.magisterka.controllers.player.model;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;

import java.util.List;

/**
 * Created by robert on 23.04.2017.
 */

public interface IPlayerModel {
    void sendPlay();

    void displayDialog(Activity activity, CircuitTimer clock);

    void updateView(List<ImageView> images, TextView text, int element);

    void loadLastUsed();
}
