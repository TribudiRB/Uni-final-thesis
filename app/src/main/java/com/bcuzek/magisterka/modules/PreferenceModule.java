package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.controllers.settings.preferenceChange.ISharedPreferenceChanged;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.SharedPreferenceChanged;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.call.CallAction;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.call.ICallAction;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.theme.IUpdateTheme;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.infrastructure.preference.Preference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApplicationContextModule.class})
public class PreferenceModule {
    @Provides
    @Singleton
    public IPreference getPreferenceService(MagisterkaApplication application) {
        return new Preference(application);
    }

    @Provides
    @Singleton
    public ICallAction getICallAction(IPreference service) {
        return new CallAction(service);
    }

    @Singleton
    @Provides
    public ISharedPreferenceChanged getISharedPreferenceChanged(ICallAction callAction,
                                                                IUpdateTheme updateTheme) {
        return new SharedPreferenceChanged(callAction, updateTheme);
    }
}
