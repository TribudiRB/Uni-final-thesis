package com.bcuzek.magisterka.controllers.drawer.runners;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.base.BaseController;
import com.bcuzek.magisterka.controllers.drawer.FragmentRunner;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;

import lombok.experimental.Builder;

/**
 * Created by robert on 14.04.2017.
 */

@Builder
public class Runner implements FragmentRunner {
    private final IFragmentRunner appFragmentManager;
    private final BaseController view;
    private final FragmentIdentifier id;

    public FragmentIdentifier run(DrawerController drawerView) {
        appFragmentManager.startFragment(view, drawerView.getFragmentManager());
        return id;
    }
}