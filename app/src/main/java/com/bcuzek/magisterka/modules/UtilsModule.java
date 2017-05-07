package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.utils.calculator.CalculatorUtils;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.color.ColorUtils;
import com.bcuzek.magisterka.utils.color.IColorUtils;
import com.bcuzek.magisterka.utils.file.FileUtils;
import com.bcuzek.magisterka.utils.file.IFileUtils;
import com.bcuzek.magisterka.utils.keyboard.IKeyboardUtils;
import com.bcuzek.magisterka.utils.keyboard.KeyboardUtils;
import com.bcuzek.magisterka.utils.record.IRecordUtils;
import com.bcuzek.magisterka.utils.record.RecordUtils;
import com.bcuzek.magisterka.utils.recorder.IRecorderUtils;
import com.bcuzek.magisterka.utils.recorder.RecorderUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;
import com.bcuzek.magisterka.utils.string.StringUtils;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;
import com.bcuzek.magisterka.utils.theme.ThemeUtils;
import com.bcuzek.magisterka.utils.trackTitle.ITrackTitleUtils;
import com.bcuzek.magisterka.utils.trackTitle.TrackTitleUtils;
import com.bcuzek.magisterka.utils.validator.IValidator;
import com.bcuzek.magisterka.utils.validator.Validator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 17.03.2017.
 */

@Module
public class UtilsModule {

    @Singleton
    @Provides
    public ITrackTitleUtils crossSound(ITrackRepository trackRepository,
                                       IStringUtils utils,
                                       IRecordRepository recordRepository,
                                       IFileUtils fileUtils) {
        return TrackTitleUtils
                .builder()
                .trackRepository(trackRepository)
                .utils(utils)
                .trackSize(trackRepository.count())
                .recordRepository(recordRepository)
                .fileUtils(fileUtils)
                .build();
    }

    @Singleton
    @Provides
    public IColorUtils getColorUtils(IThemeUtils utils) {
        return new ColorUtils(utils);
    }

    @Singleton
    @Provides
    public IThemeUtils getIThemeUtils(IPreference service) {
        return new ThemeUtils(service);
    }

    @Singleton
    @Provides
    public IStringUtils getStringUtils(Context context) {
        return new StringUtils(context);
    }

    @Singleton
    @Provides
    public ICalculatorUtils getCalculatorUtils() {
        return new CalculatorUtils();
    }

    @Singleton
    @Provides
    public IFileUtils getIFileManager() {
        return FileUtils
                .builder()
                .catalog("TromboneExpert")
                .format(".3gp")
                .dateFormatPrefix("MM dd yyyy  HH mm ss")
                .build();
    }

    @Singleton
    @Provides
    public IValidator getValidator() {
        return Validator
                .builder()
                .pattern("([\\w\\s]+)")
                .build();
    }


    @Singleton
    @Provides
    public IRecorderUtils getRecorder() {
        return new RecorderUtils();
    }

    @Singleton
    @Provides
    public IRecordUtils getCreator(IRecordRepository repository,
                                   IFileUtils manager) {
        return new RecordUtils(repository, manager);
    }

    @Singleton
    @Provides
    public IKeyboardUtils getKeyboardAction() {
        return new KeyboardUtils();
    }

}
