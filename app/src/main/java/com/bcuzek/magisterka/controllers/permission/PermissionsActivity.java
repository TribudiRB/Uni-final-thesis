package com.bcuzek.magisterka.controllers.permission;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import javax.inject.Inject;

public abstract class PermissionsActivity extends AppCompatActivity {

    @Inject
    IPreference service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    public abstract void initComponent();

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        service.writeCanDisplayDialog(true);
        recreate();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

}
