package com.bcuzek.magisterka.service.receiver;

import com.bcuzek.magisterka.service.receiver.action.IMessage;

public interface IBroadcastHolder {
    IMessage getBroadcast(boolean validatorType);
}
