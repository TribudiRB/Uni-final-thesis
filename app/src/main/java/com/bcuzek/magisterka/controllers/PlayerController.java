package com.bcuzek.magisterka.controllers;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.base.BaseConnectController;
import com.bcuzek.magisterka.controllers.player.IOnVolumeKey;
import com.bcuzek.magisterka.controllers.player.KeyVolumeEvent;
import com.bcuzek.magisterka.controllers.player.TimeCalculator;
import com.bcuzek.magisterka.controllers.player.dialogs.firstRun.IFirstRun;
import com.bcuzek.magisterka.controllers.player.model.IPlayerModel;
import com.bcuzek.magisterka.controllers.player.seekBar.ISeekBarChangeListener;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;
import com.bcuzek.magisterka.service.interfaces.IStatusWatcher;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.service.timer.ISubscription;
import com.bcuzek.magisterka.utils.color.IColorUtils;
import com.scalified.fab.ActionButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import lombok.Getter;
import lombok.val;

import static com.bcuzek.magisterka.infrastructure.constans.Extra.Extra_SOUND_LIST_ID;

public class PlayerController extends BaseConnectController implements IOnVolumeKey {

    @Getter
    String fragmentTag = ViewTag.PlayerView;

    @Getter
    int fragmentIdentifier = R.layout.player_controller;

    @BindViews({R.id.current,
            R.id.first_play,
            R.id.second_play,
            R.id.third_play,
            R.id.fourth_play})
    List<ImageView> imageViewLastUsed;

    @BindView(R.id.sound_title)
    TextView infoTextTime;

    @BindView(R.id.play)
    ActionButton playBtn;

    @BindView(R.id.stop)
    ActionButton stopBtn;

    @Inject
    IColorUtils colorUtils;

    @BindView(R.id.timer)
    CircuitTimer clock;

    @BindView(R.id.volumeBar)
    SeekBar volumeBar;

    @Inject
    ISubscription ISubscription;

    @BindString(R.string.app_name)
    @Getter
    String title;

    @Inject
    IPlayerModel model;

    @Inject
    TimeCalculator calculator;

    @Inject
    IIntentManager manager;

    @Inject
    ISeekBarChangeListener barChangeListener;

    @Inject
    ITimeHolder timeHolder;

    @Inject
    IFirstRun firstRun;

    private com.bcuzek.magisterka.controllers.player.KeyVolumeEvent KeyVolumeEvent;

    public static final PlayerController getInstance(Bundle data) {
        val fragment = new PlayerController();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);
        volumeBar.setOnSeekBarChangeListener(barChangeListener);
        firstRun.displayWelcomeDialog(getActivity());
        val bundle = getArguments();
        if (bundle != null && bundle.get(Extra_SOUND_LIST_ID) != null) {
            model.updateView(imageViewLastUsed, infoTextTime, bundle.getInt(Extra_SOUND_LIST_ID));
            timeHolder.restartTime();
            onActionStart();
        }
        model.loadLastUsed();
        model.updateView(imageViewLastUsed, infoTextTime, Integer.MIN_VALUE);
        playBtn.setButtonColor(colorUtils.getCurrentColor(getActivity()));
        stopBtn.setButtonColor(colorUtils.getCurrentColor(getActivity()));
        calculator.initClock(clock);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpKeyListener();
        clock.selectTimeDialog(() -> model.displayDialog(getActivity(), clock));
    }

    private void setUpKeyListener() {
        val au = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        KeyVolumeEvent = new KeyVolumeEvent(volumeBar, au);
        val volume_level = au.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeBar.setProgress(volume_level);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @OnClick(R.id.play)
    public void onActionStart() {
        model.sendPlay();
    }

    @OnClick(R.id.stop)
    public void onActionPause() {
        manager.send(manager.pause());
    }

    @OnClick({R.id.first_play, R.id.second_play,
            R.id.third_play, R.id.fourth_play})
    public void onClick(View v) {
        model.updateView(imageViewLastUsed, infoTextTime, (int) v.getTag());
        timeHolder.restartTime();
        manager.send(manager.stopPlay());
        onActionStart();
    }

    protected void displayCurrentState() {
        if (service != null) {
            switch (service.getCurrentState()) {
                case Playing:
                    playBtn.hide();
                    break;
                default:
                    playBtn.show();
            }
        }
    }

    protected IStatusWatcher updateStatusWatcher() {
        return new IStatusWatcher() {
            @Override
            public void onTimeChanged(long milliseconds) {
                calculator.updateClock(clock);
            }

            @Override
            public void onStateChange() {
                displayCurrentState();
            }
        };
    }

    @Override
    public boolean onVolume(int keyCode, KeyEvent event) {
        return KeyVolumeEvent.onVolume(keyCode, event);
    }
}
