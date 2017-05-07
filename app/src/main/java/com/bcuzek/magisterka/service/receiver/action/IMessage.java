package com.bcuzek.magisterka.service.receiver.action;

import android.content.Context;
import android.content.Intent;

import com.bcuzek.magisterka.service.Service;

public interface IMessage {
    Class target = Service.class;

    void receive(Context context, Intent intent);
}
