package com.bcuzek.magisterka.infrastructure.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bcuzek.magisterka.R;

/**
 * Created by robert on 13.08.2016.
 */

public class FragmentRunner implements IFragmentRunner {

    public void startFragment(IFragment fragment, FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(fragment.getFragmentTag()) != null)
            return;

        fragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.home_content_fragment, (Fragment) fragment, fragment.getFragmentTag())
                .addToBackStack(fragment.getFragmentTag())
                .commitAllowingStateLoss();
    }

}
