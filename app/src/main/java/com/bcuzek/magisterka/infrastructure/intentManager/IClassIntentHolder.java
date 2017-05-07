package com.bcuzek.magisterka.infrastructure.intentManager;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.service.receiver.PlayerReceiver;

/**
 * Created by robert on 06.11.2016.
 */

public interface IClassIntentHolder {
    Class broadcastReceiver = PlayerReceiver.class;
    Class application = MainActivity.class;
}
