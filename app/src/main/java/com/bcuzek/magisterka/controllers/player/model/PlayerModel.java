package com.bcuzek.magisterka.controllers.player.model;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.player.TimeCalculator;
import com.bcuzek.magisterka.controllers.player.dialogs.time.SelectOneItemDialog;
import com.bcuzek.magisterka.controllers.player.model.utils.ILastTrackManager;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.utils.trackTitle.ITrackTitleUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import lombok.experimental.Builder;

@Builder
public class PlayerModel implements IPlayerModel {

    private ITrackRepository soundRepository;
    private IPreference service;
    private IIntentManager creator;
    private TimeCalculator calculator;
    private ITrackTitleUtils trackTitleUtils;
    private ITimeHolder timeHolder;
    private SelectOneItemDialog dialog;
    private ILastTrackManager manager;
    private List<Track> track;
    private Context context;
    private long trackSize;

    public void sendPlay() {
        if (manager.size() > 0) {
            creator.send(creator.play(manager.get(0)));
            manager.addLastTrack(manager.get(0));
        } else {
            manager.addLastTrack(0);
            creator.send(creator.play(0));
        }
    }

    public void displayDialog(Activity activity, CircuitTimer clock) {
        dialog.build(activity, item -> {
            service.writeTimePlay(item.getValue());
            creator.send(creator.restart());
            timeHolder.restartTime();
            calculator.restartClock(clock, item.getName());
        });
    }

    public void updateView(List<ImageView> images, TextView text, int element) {
        if (manager.size() > 0)
            text.setText(trackTitleUtils.getTitle(manager.get(0)));
        for (int i = 0; i < manager.size(); i++) {
            images.get(i).setVisibility(View.VISIBLE);
            images.get(i).setTag(manager.get(i));
            Picasso.with(context)
                    .load(manager.get(i) < trackSize ?
                            track.get(manager.get(i)).getIcon() :
                            R.drawable.ic_audiotrack_white_24dp)
                    .into(images.get(i));
        }
        if (element != Integer.MIN_VALUE)
            manager.addLastTrack(element);
    }

    public void loadLastUsed() {
        manager.createLastTrack();
    }
}
