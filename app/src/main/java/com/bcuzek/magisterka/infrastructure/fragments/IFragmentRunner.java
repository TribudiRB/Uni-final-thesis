package com.bcuzek.magisterka.infrastructure.fragments;

import android.support.v4.app.FragmentManager;

/**
 * Created by robert on 13.08.2016.
 */

public interface IFragmentRunner {
    void startFragment(IFragment fragment, FragmentManager fragmentManager);
}
