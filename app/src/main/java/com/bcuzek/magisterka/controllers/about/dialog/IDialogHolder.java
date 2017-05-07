package com.bcuzek.magisterka.controllers.about.dialog;


import com.bcuzek.magisterka.controllers.about.dialog.action.IDialogAction;
import com.bcuzek.magisterka.controllers.about.view.AboutIdentifier;

public interface IDialogHolder {
    IDialogAction getAction(AboutIdentifier validatorType);
}
