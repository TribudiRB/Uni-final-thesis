package com.bcuzek.magisterka.controllers.drawer;

import com.bcuzek.magisterka.R;

import lombok.experimental.Builder;


@Builder
public class DrawerHolder implements IDrawerHolder {
    public static final int FORCE_SOUTLIST = 99999;
    private final FragmentRunner about;
    private final FragmentRunner record;
    private final FragmentRunner player;
    private final FragmentRunner settings;
    private final FragmentRunner list;
    private final FragmentRunner custom;
    private final FragmentRunner exit;

    @Override
    public FragmentRunner getBroadcast(int validatorType) {
        switch (validatorType) {
            case R.id.settings:
                return settings;
            case R.id.player:
                return player;
            case R.id.about:
                return about;
            case R.id.record:
                return record;
            case R.id.sounds:
                return list;
            case R.id.exit:
                return exit;
            case FORCE_SOUTLIST:
                return custom;
        }
        return player;
    }
}
