package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.controllers.player.TimeCalculator;
import com.bcuzek.magisterka.controllers.player.dialogs.time.SelectOneItemDialog;
import com.bcuzek.magisterka.controllers.player.model.IPlayerModel;
import com.bcuzek.magisterka.controllers.player.model.PlayerModel;
import com.bcuzek.magisterka.controllers.player.model.utils.ILastTrackManager;
import com.bcuzek.magisterka.controllers.record.RecordModel;
import com.bcuzek.magisterka.controllers.record.animation.ISubscriptionTimer;
import com.bcuzek.magisterka.controllers.soundList.model.record.IRecordView;
import com.bcuzek.magisterka.controllers.soundList.model.record.RecordDataView;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.file.IFileUtils;
import com.bcuzek.magisterka.utils.record.IRecordUtils;
import com.bcuzek.magisterka.utils.recorder.IRecorderUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;
import com.bcuzek.magisterka.utils.trackTitle.ITrackTitleUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 07.05.2017.
 */
@Module
public class ModelsModules {

    @Singleton
    @Provides
    public IRecordView getCustomDataView(IStringUtils context,
                                         ICalculatorUtils utils,
                                         IRecordRepository repository) {
        return RecordDataView
                .builder()
                .context(context)
                .calculatorUtils(utils)
                .recordRepository(repository)
                .build();
    }

    @Singleton
    @Provides
    public RecordModel getRecordModel(TimeCalculator timeCalculator,
                                      IRecorderUtils recorder,
                                      IRecordUtils soundCreator,
                                      IFileUtils manager,
                                      ISubscriptionTimer action,
                                      IRecordRepository recordRepository,
                                      ITimeHolder holder) {
        return new RecordModel(timeCalculator, recordRepository, recorder, soundCreator, manager, action, holder);
    }

    @Singleton
    @Provides
    public IPlayerModel getPlayerViewModel(ITrackRepository soundRepository,
                                           IPreference service,
                                           IIntentManager creator,
                                           TimeCalculator calculator,
                                           ITrackTitleUtils titleMa,
                                           ITimeHolder timeHolder,
                                           SelectOneItemDialog dialog,
                                           ILastTrackManager manager,
                                           Context context) {
        return PlayerModel
                .builder()
                .soundRepository(soundRepository)
                .service(service)
                .creator(creator)
                .calculator(calculator)
                .trackSize(soundRepository.count())
                .trackTitleUtils(titleMa)
                .timeHolder(timeHolder)
                .dialog(dialog)
                .manager(manager)
                .context(context)
                .track(soundRepository.getAll())
                .build();
    }
}
