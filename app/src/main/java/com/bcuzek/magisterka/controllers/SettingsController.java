package com.bcuzek.magisterka.controllers;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.settings.preferenceChange.ISharedPreferenceChanged;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;
import com.bcuzek.magisterka.infrastructure.fragments.IFragment;
import com.github.machinarius.preferencefragment.PreferenceFragment;

import javax.inject.Inject;

public class SettingsController extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, IFragment {

    @Inject
    ISharedPreferenceChanged iSharedPreferenceChanged;

    @Override
    public String getFragmentTag() {
        return ViewTag.SettingsView;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MagisterkaApplication.component().inject(this);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        iSharedPreferenceChanged.onSharedPreferenceChanged(this);
    }
}
