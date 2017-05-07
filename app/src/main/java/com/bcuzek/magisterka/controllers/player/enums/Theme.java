package com.bcuzek.magisterka.controllers.player.enums;


import com.bcuzek.magisterka.R;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by robert on 23.08.2016.
 */
@Getter
@AllArgsConstructor
public enum Theme {
    RED(1, R.color.colorRedDark, R.style.AppTheme_styleRed, R.drawable.common_fab_shape_red_color),
    BLUE(2, R.color.colorBlueDark, R.style.AppTheme_styleBlue, R.drawable.common_fab_add_shape),
    GREEN(3, R.color.colorGreenDark, R.style.AppTheme_styleGreen, R.drawable.common_fab_shape_green),
    SALOMON(4, R.color.colorSalomonDark, R.style.AppTheme_styleSalomon, R.drawable.common_fab_shape_salomon),
    PEACH_PUFF(5, R.color.colorSalomonDark, R.style.AppTheme_stylePeachPuff, R.drawable.common_fab_shape_peach_puff),
    OLIVE(6, R.color.colorOlive, R.style.AppTheme_styleOlive, R.drawable.common_fab_shape_olive),
    CHOCOLATE(7, R.color.colorChocolate, R.style.AppTheme_styleChocolate, R.drawable.common_fab_shape_chocolate),
    GOLD(8, R.color.colorGold, R.style.AppTheme_styleGold, R.drawable.common_fab_shape_gold);
    private final int id;
    private final int color;
    private final int style;
    private final int drawable;
}
