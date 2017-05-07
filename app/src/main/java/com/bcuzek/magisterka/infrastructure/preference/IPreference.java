package com.bcuzek.magisterka.infrastructure.preference;

import com.bcuzek.magisterka.controllers.settings.PlaySoundMusicCall;

import java.util.List;

/**
 * Created by robert on 16.08.2016.
 */

public interface IPreference extends IThemeService, ITimePlayService, IApplicationFirstDisplayElementsService, INotificationVoiceStreamService, IPermissionService {
    int readAppRunTime();

    PlaySoundMusicCall canPlayRingtone();

    void updateCommissioning();

    void writeRigtoneStream(PlaySoundMusicCall value);

    void writeUsedList(List<Integer> list);

    List<Integer> getLastUsed();

}
