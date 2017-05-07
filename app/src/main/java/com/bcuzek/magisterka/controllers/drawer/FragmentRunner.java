package com.bcuzek.magisterka.controllers.drawer;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.infrastructure.fragments.FragmentIdentifier;

public interface FragmentRunner {
    FragmentIdentifier run(DrawerController drawerView);
}
