package com.bcuzek.magisterka.controllers.soundList.custom;

import android.app.Activity;

import com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu.ExtrasPopupMenuListener;

import lombok.Getter;
import lombok.experimental.Builder;

/**
 * Created by robert on 28.10.2016.
 */
@Getter
@Builder
public class CustomDTO {
    private final int id;
    private final ExtrasPopupMenuListener.IOnDelete action;
    private final Activity activity;

}
