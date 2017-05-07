package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.controllers.AboutController;
import com.bcuzek.magisterka.controllers.PlayerController;
import com.bcuzek.magisterka.controllers.RecordController;
import com.bcuzek.magisterka.controllers.SettingsController;
import com.bcuzek.magisterka.controllers.drawer.DrawerHolder;
import com.bcuzek.magisterka.controllers.drawer.FragmentRunner;
import com.bcuzek.magisterka.controllers.drawer.IDrawerHolder;
import com.bcuzek.magisterka.controllers.drawer.runners.CustomListRunner;
import com.bcuzek.magisterka.controllers.drawer.runners.ExitRunner;
import com.bcuzek.magisterka.controllers.drawer.runners.Runner;
import com.bcuzek.magisterka.controllers.drawer.runners.SettingsRunner;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.DrawerQualifiers;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;

@dagger.Module
public class DrawerModules {

    @Provides
    @Singleton
    public IDrawerHolder getDrawerHolder(@Named(DrawerQualifiers.ABOUT) FragmentRunner about,
                                         @Named(DrawerQualifiers.RECORD) FragmentRunner record,
                                         @Named(DrawerQualifiers.PLAYER) FragmentRunner player,
                                         @Named(DrawerQualifiers.SETTINGS) FragmentRunner settings,
                                         @Named(DrawerQualifiers.TRACK) FragmentRunner track,
                                         @Named(DrawerQualifiers.LIST) FragmentRunner list,
                                         @Named(DrawerQualifiers.EXIT) FragmentRunner exit) {
        return DrawerHolder
                .builder()
                .about(about)
                .record(record)
                .player(player)
                .settings(settings)
                .list(track)
                .custom(list)
                .exit(exit)
                .build();
    }

    @Provides
    @Named(DrawerQualifiers.ABOUT)
    @Singleton
    public FragmentRunner getAboutRunner(IFragmentRunner appFragmentManager,
                                         AboutController view) {
        return Runner
                .builder()
                .appFragmentManager(appFragmentManager)
                .id(FragmentIdentifier.About_application)
                .view(view)
                .build();
    }

    @Provides
    @Named(DrawerQualifiers.RECORD)
    @Singleton
    public FragmentRunner getRecordRunner(IFragmentRunner appFragmentManager,
                                          RecordController view) {
        return Runner
                .builder()
                .appFragmentManager(appFragmentManager)
                .id(FragmentIdentifier.Record)
                .view(view)
                .build();
    }

    @Provides
    @Named(DrawerQualifiers.PLAYER)
    @Singleton
    public FragmentRunner getPlayerRunner(IFragmentRunner appFragmentManager,
                                          PlayerController view) {
        return Runner
                .builder()
                .appFragmentManager(appFragmentManager)
                .id(FragmentIdentifier.Player)
                .view(view)
                .build();
    }

    @Provides
    @Named(DrawerQualifiers.SETTINGS)
    @Singleton
    public FragmentRunner getSettingsRunner(IFragmentRunner appFragmentManager,
                                            SettingsController view) {
        return new SettingsRunner(appFragmentManager, view);
    }

    @Provides
    @Named(DrawerQualifiers.TRACK)
    @Singleton
    public FragmentRunner getSoundListRunner(IFragmentRunner appFragmentManager) {
        return new CustomListRunner(appFragmentManager);
    }

    @Provides
    @Named(DrawerQualifiers.LIST)
    @Singleton
    public FragmentRunner getCustomListRunner(IFragmentRunner appFragmentManager) {
        return new CustomListRunner(appFragmentManager);
    }

    @Provides
    @Named(DrawerQualifiers.EXIT)
    @Singleton
    public FragmentRunner getExitRunner(IIntentManager creator) {
        return new ExitRunner(creator);
    }
}
