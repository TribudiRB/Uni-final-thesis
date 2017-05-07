package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.AboutController;
import com.bcuzek.magisterka.controllers.PagerHostController;
import com.bcuzek.magisterka.controllers.PlayerController;
import com.bcuzek.magisterka.controllers.RecordController;
import com.bcuzek.magisterka.controllers.SettingsController;
import com.bcuzek.magisterka.controllers.player.dialogs.time.SelectOneItemDialog;
import com.bcuzek.magisterka.controllers.player.dialogs.time.elements.ITimeDialogElements;
import com.bcuzek.magisterka.controllers.player.model.utils.ILastTrackManager;
import com.bcuzek.magisterka.controllers.player.model.utils.LastTrackManager;
import com.bcuzek.magisterka.controllers.record.animation.ISubscriptionTimer;
import com.bcuzek.magisterka.controllers.record.animation.SubscriptionTimer;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.theme.IUpdateTheme;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.theme.UpdateTheme;
import com.bcuzek.magisterka.controllers.soundList.model.track.DataView;
import com.bcuzek.magisterka.controllers.soundList.model.track.ITrackView;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.service.timer.ISubscription;
import com.bcuzek.magisterka.service.timer.Timer;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModules {

    @Singleton
    @Provides
    public IUpdateTheme getIThemeService(IPreference service) {
        return new UpdateTheme(service);
    }

    @Singleton
    @Provides
    public SelectOneItemDialog selectOneItemDialog(IPreference service,
                                                   IThemeUtils utils,
                                                   ITimeDialogElements dialogElements) {
        return SelectOneItemDialog
                .builder()
                .items(dialogElements.getListOfTimeItems())
                .service(service)
                .utils(utils)
                .title(R.string.player_view_time_title)
                .negativeText(R.string.common_cancel)
                .elements(dialogElements)
                .build();
    }

    @Singleton
    @Provides
    public ITrackView getDataView(ICalculatorUtils calculatorUtils,
                                  IStringUtils utils,
                                  ITrackRepository trackRepository) {
        return DataView
                .builder()
                .calculatorUtils(calculatorUtils)
                .utils(utils)
                .trackRepository(trackRepository)
                .build();
    }

    @Provides
    public SettingsController startNewSettings() {
        return new SettingsController();
    }

    @Provides
    public AboutController aboutApplicationView() {
        return new AboutController();
    }

    @Provides
    public PagerHostController soundListHostView() {
        return new PagerHostController();
    }

    @Provides
    public RecordController getRecordView() {
        return new RecordController();
    }

    @Provides
    public PlayerController playerView() {
        return new PlayerController();
    }

    @Singleton
    @Provides
    public ISubscriptionTimer animationAction() {
        return new SubscriptionTimer();
    }

    @Singleton
    @Provides
    public ISubscription createSubscription() {
        return new Timer();
    }

    @Provides
    public ILastTrackManager getILastTrackManager(IPreference service,
                                                  IRecordRepository recordRepository,
                                                  ITrackRepository trackRepository) {
        return LastTrackManager
                .builder()
                .service(service)
                .recordRepository(recordRepository)
                .trackSize(trackRepository.count())
                .trackRepository(trackRepository)
                .build();
    }

}

