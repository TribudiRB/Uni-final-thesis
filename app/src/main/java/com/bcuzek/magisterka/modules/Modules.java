package com.bcuzek.magisterka.modules;

import android.content.Context;
import android.media.AudioManager;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.player.TimeCalculator;
import com.bcuzek.magisterka.controllers.player.dialogs.firstRun.FirstRun;
import com.bcuzek.magisterka.controllers.player.dialogs.firstRun.IFirstRun;
import com.bcuzek.magisterka.controllers.player.dialogs.time.elements.ITimeDialogElements;
import com.bcuzek.magisterka.controllers.player.dialogs.time.elements.TimeDialogElements;
import com.bcuzek.magisterka.controllers.player.seekBar.ISeekBarChangeListener;
import com.bcuzek.magisterka.controllers.player.seekBar.SeekBarChangeListener;
import com.bcuzek.magisterka.controllers.record.dialog.IOptionDialog;
import com.bcuzek.magisterka.controllers.record.dialog.OptionDialog;
import com.bcuzek.magisterka.controllers.soundList.ITabSelectedListener;
import com.bcuzek.magisterka.controllers.soundList.TabSelectedListener;
import com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu.ExtrasPopupMenuListener;
import com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu.IExtrasPopupMenuListener;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.service.model.time.TimeHolder;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.file.IFileUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;
import com.bcuzek.magisterka.utils.theme.IThemeUtils;

import javax.inject.Singleton;

import dagger.Provides;

@dagger.Module
public class Modules {

    @Singleton
    @Provides
    public ISeekBarChangeListener getSeekBarChangeListener(Context context) {
        return SeekBarChangeListener
                .builder()
                .audioManager((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
                .build();
    }

    @Singleton
    @Provides
    public ITabSelectedListener getTabSelectedListener() {
        return new TabSelectedListener();
    }

    @Singleton
    @Provides
    public IOptionDialog getOptionDialog() {
        return OptionDialog
                .builder()
                .content(R.string.save_question)
                .positiveText(R.string.save_no)
                .negativeText(R.string.save_yes)
                .build();
    }

    @Provides
    @Singleton
    public TimeCalculator getTimeCalculator(IPreference service, ITimeHolder timeHolder,
                                            ICalculatorUtils calculatorUtils, IStringUtils utils) {
        return TimeCalculator
                .builder()
                .service(service)
                .timeHolder(timeHolder)
                .calculatorUtils(calculatorUtils)
                .utils(utils)
                .build();
    }

    @Singleton
    @Provides
    public ITimeHolder getTimeHolder() {
        return new TimeHolder();
    }

    @Singleton
    @Provides
    public ITimeDialogElements getITimeDialogElements(Context context) {
        return new TimeDialogElements(context);
    }

    @Singleton
    @Provides
    public IFirstRun getIFirstLunchApp(IThemeUtils utils,
                                       IPreference service) {
        return FirstRun
                .builder()
                .title(R.string.app_welcome)
                .service(service)
                .utils(utils)
                .neutralText(R.string.first_run_button)
                .content(R.string.first_run_content)
                .build();
    }

    @Singleton
    @Provides
    public IExtrasPopupMenuListener getIExtrasPopupMenuListener(IFileUtils manager,
                                                                ITrackRepository custom,
                                                                IIntentManager intentCreator,
                                                                IRecordRepository recordRepository) {
        return ExtrasPopupMenuListener
                .builder()
                .intentCreator(intentCreator)
                .manager(manager)
                .custom(custom)
                .recordRepository(recordRepository)
                .content(R.string.delete_content)
                .negativeText(R.string.delete)
                .positiveText(R.string.common_cancel)
                .build();
    }
}
