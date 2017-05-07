package com.bcuzek.magisterka.components;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.controllers.AboutController;
import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.PagerHostController;
import com.bcuzek.magisterka.controllers.PlayerController;
import com.bcuzek.magisterka.controllers.RecordController;
import com.bcuzek.magisterka.controllers.SettingsController;
import com.bcuzek.magisterka.controllers.base.BaseConnectController;
import com.bcuzek.magisterka.controllers.base.BaseRecordController;
import com.bcuzek.magisterka.controllers.base.PlayerPhoneStateListener;
import com.bcuzek.magisterka.controllers.permission.PermissionsActivity;
import com.bcuzek.magisterka.controllers.player.model.PlayerModel;
import com.bcuzek.magisterka.controllers.soundList.adapter.RecordAdapter;
import com.bcuzek.magisterka.controllers.soundList.adapter.TrackAdapter;
import com.bcuzek.magisterka.controllers.soundList.custom.ExtrasCustomOptionListener;
import com.bcuzek.magisterka.controllers.soundList.views.RecordsController;
import com.bcuzek.magisterka.controllers.soundList.views.TracksController;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.service.Service;
import com.bcuzek.magisterka.service.base.BaseActionService;
import com.bcuzek.magisterka.service.receiver.PlayerReceiver;

/**
 * Created by robert on 22.01.2017.
 */

public interface ComponentGraph {

    void inject(MagisterkaApplication application);

    void inject(PermissionsActivity permissionsActivity);

    void inject(MainActivity activity);

    void inject(DrawerController fragmentManager);

    void inject(SettingsController settingsController);

    void inject(PlayerController playerController);

    void inject(Service playerService);

    void inject(PlayerPhoneStateListener playerPhoneStateListener);

    void inject(PlayerModel PlayerModel);

    void inject(AboutController aboutController);

    void inject(TracksController tracksView);

    void inject(TrackAdapter adapter);

    void inject(RecordController recordController);

    void inject(RecordsController recordsView);

    void inject(RecordAdapter adapter);

    void inject(PagerHostController pagerHostController);

    void inject(PlayerReceiver playerReceiver);

    void inject(BaseRecordController baseRecordView);

    void inject(ExtrasCustomOptionListener extrasCustomOptionListener);

    void inject(BaseActionService service);

    void inject(BaseConnectController connectController);

}
