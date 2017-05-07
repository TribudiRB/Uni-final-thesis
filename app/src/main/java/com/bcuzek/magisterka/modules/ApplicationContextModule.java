package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.domain.catalog.ITrackCatalog;
import com.bcuzek.magisterka.infrastructure.domain.catalog.TrackCatalog;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.initialTransaction.ITrackInitialTransaction;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.initialTransaction.TrackInitialTransaction;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentRunner;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.intentManager.IntentManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.11.2016.
 */
@Module
public class ApplicationContextModule {
    private final MagisterkaApplication application;

    public ApplicationContextModule(MagisterkaApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public MagisterkaApplication application() {
        return application;
    }

    @Provides
    @Singleton
    public Context applicationContext() {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    public ITrackCatalog getSoundCatalog() {
        return new TrackCatalog();
    }

    @Singleton
    @Provides
    public ITrackInitialTransaction transaction(ITrackCatalog soundCatalog) {
        return new TrackInitialTransaction(soundCatalog);
    }

    @Provides
    @Singleton
    public IIntentManager getIntentCreator(Context context) {
        return new IntentManager(context);
    }

    @Provides
    @Singleton
    public IFragmentRunner appFragmentManager() {
        return new FragmentRunner();
    }

}
