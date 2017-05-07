package com.bcuzek.magisterka.controllers;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.drawer.IDrawerHolder;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.val;


public class DrawerController extends Fragment {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.navigation_drawer_image)
    LinearLayout layout;

    @Inject
    IDrawerHolder drawerHolder;

    private MenuItem lastItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = inflater.inflate(R.layout.drawer_conteiner, container, false);
        ButterKnife.bind(this, view);
        MagisterkaApplication.component().inject(this);
        return view;
    }

    public void init(DrawerLayout drawerLayout) {
        navigationView.setNavigationItemSelectedListener(item -> {
            changeCheckableState(item);
            changeSelectedItem(item);
            drawerLayout.closeDrawers();
            return true;
        });
        val drawerToggle = new ActionBarDrawerToggle(getActivity(),
                drawerLayout,
                (Toolbar) getActivity().findViewById(R.id.toolbar),
                R.string.drawer_open,
                R.string.drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.post(() -> drawerToggle.syncState());
    }

    public void changeStateToPlayer() {
        val item = navigationView.getMenu().findItem(R.id.player);
        changeCheckableState(item);
        if (lastItem == null)
            lastItem = item;
        if (lastItem != item) {
            getFragmentManager().popBackStackImmediate();
            lastItem = item;
        }
    }

    public void runPlayerController() {
        val item = navigationView.getMenu().findItem(R.id.player);
        changeCheckableState(item);
        changeSelectedItem(item);
    }

    public void applyTheme(int color) {
        layout.setBackgroundColor(color);
    }

    private void changeCheckableState(MenuItem menuItem) {
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
    }

    private void changeSelectedItem(MenuItem menuItem) {
        if (lastItem == null)
            lastItem = menuItem;
        if (lastItem != menuItem) {
            getFragmentManager().popBackStackImmediate();
            lastItem = menuItem;
        }
        drawerHolder.getBroadcast(menuItem.getItemId()).run(this);
    }

}
