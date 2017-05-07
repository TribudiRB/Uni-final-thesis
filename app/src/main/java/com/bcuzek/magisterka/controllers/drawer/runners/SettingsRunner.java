package com.bcuzek.magisterka.controllers.drawer.runners;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.SettingsController;
import com.bcuzek.magisterka.controllers.drawer.FragmentRunner;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;

import lombok.RequiredArgsConstructor;

/**
 * Created by robert on 14.04.2017.
 */

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class SettingsRunner implements FragmentRunner {
    private final IFragmentRunner appFragmentManager;
    private final SettingsController view;

    @Override
    public FragmentIdentifier run(DrawerController drawerView) {
        appFragmentManager.startFragment(view, drawerView.getFragmentManager());
        return FragmentIdentifier.Settings;
    }
}