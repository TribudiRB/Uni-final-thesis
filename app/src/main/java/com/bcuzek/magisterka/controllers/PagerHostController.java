package com.bcuzek.magisterka.controllers;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.base.BaseController;
import com.bcuzek.magisterka.controllers.soundList.ITabSelectedListener;
import com.bcuzek.magisterka.controllers.soundList.PagerAdapter;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import lombok.Getter;
import lombok.val;

public class PagerHostController extends BaseController {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Getter
    String fragmentTag = ViewTag.SoundListHostView;

    @Getter
    int fragmentIdentifier = R.layout.pagger_controller;

    @BindString(R.string.pager_records)
    String records;

    @BindString(R.string.pager_system)
    String system;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Inject
    ITabSelectedListener tabListener;

    public static final PagerHostController getInstance() {
        return new PagerHostController();
    }

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);
        tabLayout.addTab(tabLayout.newTab().setText(system));
        tabLayout.addTab(tabLayout.newTab().setText(records));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setAdapter(new PagerAdapter(getMainActivity().getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(PagerAdapter.SYSTEM_SOUND);
        tabListener.setViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(tabListener);
        return view;
    }
}
