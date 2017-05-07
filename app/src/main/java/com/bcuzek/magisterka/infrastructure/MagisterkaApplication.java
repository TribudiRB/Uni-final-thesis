package com.bcuzek.magisterka.infrastructure;

import android.app.Application;

import com.bcuzek.magisterka.components.ApplicationComponent;
import com.bcuzek.magisterka.components.ComponentGraph;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.IDatabaseContext;

import javax.inject.Inject;

public class MagisterkaApplication extends Application {
    private static ComponentGraph graph;
    private static MagisterkaApplication mInstance;

    @Inject
    IDatabaseContext databaseContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        buildComponent();
        component().inject(this);
        databaseContext.setup();
    }

    public static ComponentGraph component() {
        if (graph == null)
            throw new MagisterkaException();
        return graph;
    }

    private static void buildComponent() {
        graph = ApplicationComponent.Initializer.init(mInstance);
    }
}
