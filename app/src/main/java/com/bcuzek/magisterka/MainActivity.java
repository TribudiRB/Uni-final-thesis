package com.bcuzek.magisterka;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;

import com.bcuzek.magisterka.controllers.DrawerController;
import com.bcuzek.magisterka.controllers.PlayerController;
import com.bcuzek.magisterka.controllers.permission.PermissionsActivity;
import com.bcuzek.magisterka.controllers.player.IOnVolumeKey;
import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.fragments.IFragmentRunner;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.val;

import static com.bcuzek.magisterka.infrastructure.constans.Extra.Extra_SOUND_LIST_ID;


public class MainActivity extends PermissionsActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    DrawerController navigationDrawer;

    @Inject
    IFragmentRunner appFragmentManager;

    @Inject
    IPreference preferenceService;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        val tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        val fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment instanceof IOnVolumeKey && ((IOnVolumeKey) fragment).onVolume(keyCode, event))
            return false;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_controller);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        navigationDrawer = (DrawerController) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        navigationDrawer.init(drawerLayout);
        updateViewStyle();
        preferenceService.updateCommissioning();
        if (getFragmentManager().getBackStackEntryCount() == 0)
            openPlayer();
    }

    @Override
    public void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(Gravity.LEFT);
        else
            drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void updateViewStyle() {
        int element = preferenceService.readStyle() - 1;
        val style = Theme.values()[element].getStyle();
        val color = Theme.values()[element].getColor();
        getTheme().applyStyle(style, true);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));
        navigationDrawer.applyTheme(ContextCompat.getColor(this, color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(getResources().getColor(color));
    }

    public void openPlayer(int sound) {
        navigationDrawer.changeStateToPlayer();
        val bundle = new Bundle();
        bundle.putInt(Extra_SOUND_LIST_ID, sound);
        appFragmentManager.startFragment(PlayerController.getInstance(bundle), getSupportFragmentManager());
    }

    @Override
    public void recreate() {
        finish();
        startActivity(getIntent());
    }

    public void openPlayer() {
        navigationDrawer.runPlayerController();
    }

}
