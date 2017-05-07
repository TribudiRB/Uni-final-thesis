package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.NotificationModelQualifiers;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.service.model.dto.NotificationDTO;
import com.bcuzek.magisterka.service.notification.ForegroundNotification;
import com.bcuzek.magisterka.service.notification.IForegroundNotification;
import com.bcuzek.magisterka.utils.notificationTimeCalculator.INotificationTimeCalculator;
import com.bcuzek.magisterka.utils.notificationTimeCalculator.NotificationTimeCalculator;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 15.03.2017.
 */
@Module
public class NotificationModules {

    @Singleton
    @Provides
    public IForegroundNotification getForegroundNotification(Context context,
                                                             IIntentManager intentCreator,
                                                             IStringUtils utils) {
        return ForegroundNotification
                .builder()
                .context(context)
                .contentIntent(intentCreator.openPlayer())
                .icon(R.drawable.ic_audiotrack_white_48dp)
                .powerOffIcon(R.drawable.ic_power_settings_new_white_24dp)
                .exitIntent(intentCreator.exitApplication())
                .stringUtils(utils)
                .build();
    }

    @Singleton
    @Provides
    @Named(NotificationModelQualifiers.PAUSE)
    public NotificationDTO getNotificationModelPause(IStringUtils context,
                                                     IIntentManager intentCreator,
                                                     INotificationTimeCalculator calculator) {
        return NotificationDTO
                .builder()
                .contentTitle(context.getStringById(R.string.app_name))
                .actionString(R.string.pause)
                .calculator(calculator)
                .drawable(R.drawable.ic_pause_white_24dp)
                .intent(intentCreator.pause())
                .build();
    }

    @Singleton
    @Provides
    @Named(NotificationModelQualifiers.PLAY)
    public NotificationDTO getNotificationModelPlay(IStringUtils context,
                                                    IIntentManager intentCreator,
                                                    INotificationTimeCalculator calculator) {
        return NotificationDTO
                .builder()
                .contentTitle(context.getStringById(R.string.app_name))
                .actionString(R.string.play)
                .calculator(calculator)
                .drawable(R.drawable.ic_play_arrow_white_24dp)
                .intent(intentCreator.play())
                .build();
    }

    @Singleton
    @Provides
    public INotificationTimeCalculator getITimeCalculator(ICalculatorUtils calculatorUtils,
                                                          IStringUtils context,
                                                          IPreference service) {
        return NotificationTimeCalculator
                .builder()
                .calculatorUtils(calculatorUtils)
                .utils(context)
                .service(service)
                .build();
    }


}
