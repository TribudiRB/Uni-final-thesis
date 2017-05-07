package com.bcuzek.magisterka.controllers.drawer.runners;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.PagerHostController;
import com.bcuzek.magisterka.controllers.drawer.FragmentRunner;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public class CustomListRunner implements FragmentRunner {

    private final IFragmentRunner appFragmentManager;

    @Override
    public FragmentIdentifier run(DrawerController drawerView) {
        appFragmentManager.startFragment(PagerHostController.getInstance(), drawerView.getFragmentManager());
        return FragmentIdentifier.Sound_list;
    }
}