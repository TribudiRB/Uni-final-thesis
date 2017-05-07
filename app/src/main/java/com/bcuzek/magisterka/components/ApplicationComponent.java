package com.bcuzek.magisterka.components;

import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.modules.AboutApplicationListItemsModule;
import com.bcuzek.magisterka.modules.AboutDialogModules;
import com.bcuzek.magisterka.modules.ApplicationContextModule;
import com.bcuzek.magisterka.modules.AudioModules;
import com.bcuzek.magisterka.modules.BroadcastModules;
import com.bcuzek.magisterka.modules.DBModule;
import com.bcuzek.magisterka.modules.DrawerModules;
import com.bcuzek.magisterka.modules.ModelsModules;
import com.bcuzek.magisterka.modules.Modules;
import com.bcuzek.magisterka.modules.NotificationModules;
import com.bcuzek.magisterka.modules.PermissionModules;
import com.bcuzek.magisterka.modules.PreferenceModule;
import com.bcuzek.magisterka.modules.ServiceActionModules;
import com.bcuzek.magisterka.modules.UtilsModule;
import com.bcuzek.magisterka.modules.ViewModules;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert on 22.11.2016.
 */

@Singleton
@Component(modules = {
        ApplicationContextModule.class,
        PreferenceModule.class,
        DBModule.class,
        Modules.class,
        ServiceActionModules.class,
        ViewModules.class,
        AboutDialogModules.class,
        UtilsModule.class,
        NotificationModules.class,
        PermissionModules.class,
        AboutApplicationListItemsModule.class,
        AudioModules.class,
        ModelsModules.class,
        BroadcastModules.class,
        DrawerModules.class
})
public interface ApplicationComponent extends ComponentGraph {

    final class Initializer {

        private static ApplicationComponent applicationComponent;

        private Initializer() {
        }

        public static ApplicationComponent init(MagisterkaApplication app) {
            if (applicationComponent == null)
                applicationComponent = DaggerApplicationComponent
                        .builder()
                        .applicationContextModule(new ApplicationContextModule(app))
                        .preferenceModule(new PreferenceModule())
                        .dBModule(new DBModule())
                        .audioModules(new AudioModules())
                        .viewModules(new ViewModules())
                        .modules(new Modules())
                        .serviceActionModules(new ServiceActionModules())
                        .viewModules(new ViewModules())
                        .aboutDialogModules(new AboutDialogModules())
                        .utilsModule(new UtilsModule())
                        .notificationModules(new NotificationModules())
                        .permissionModules(new PermissionModules())
                        .aboutApplicationListItemsModule(new AboutApplicationListItemsModule())
                        .broadcastModules(new BroadcastModules())
                        .drawerModules(new DrawerModules())
                        .build();
            return applicationComponent;
        }
    }
}
