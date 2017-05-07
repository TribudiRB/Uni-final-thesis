package com.bcuzek.magisterka.service.actionHolder;

import com.bcuzek.magisterka.service.actionHolder.action.ServiceAction;

public interface IActionHolder {
    ServiceAction getAction(String validatorType);
}
