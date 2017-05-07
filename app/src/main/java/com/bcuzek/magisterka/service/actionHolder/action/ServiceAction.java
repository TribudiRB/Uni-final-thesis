package com.bcuzek.magisterka.service.actionHolder.action;


import android.content.Intent;

import com.bcuzek.magisterka.service.interfaces.IServiceAction;

public interface ServiceAction {
    void action(IServiceAction action, Intent intent);
}
