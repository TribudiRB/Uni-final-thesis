package com.bcuzek.magisterka.controllers.soundList;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bcuzek.magisterka.controllers.soundList.views.RecordsController;
import com.bcuzek.magisterka.controllers.soundList.views.TracksController;

import lombok.Getter;

@Getter
public class PagerAdapter extends FragmentStatePagerAdapter {
    public static final int SYSTEM_SOUND = 0;
    private int count;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.count = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new RecordsController();
            default:
                return new TracksController();
        }
    }
}