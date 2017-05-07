package com.bcuzek.magisterka.controllers.drawer.runners;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.drawer.FragmentRunner;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;

import lombok.AllArgsConstructor;

/**
 * Created by robert on 24.04.2017.
 */
@AllArgsConstructor
public class ExitRunner implements FragmentRunner {

    private final IIntentManager creator;

    @Override
    public FragmentIdentifier run(DrawerController drawerView) {
        creator.send(creator.exitApplication());
        return null;
    }
}
