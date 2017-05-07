package com.bcuzek.magisterka.controllers.soundList;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public interface ITabSelectedListener extends TabLayout.OnTabSelectedListener {
    void setViewPager(ViewPager viewPager);
}
