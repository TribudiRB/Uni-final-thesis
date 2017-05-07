package com.bcuzek.magisterka.controllers.soundList;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class TabSelectedListener implements ITabSelectedListener {
    private ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
