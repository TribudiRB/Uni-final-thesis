package com.bcuzek.magisterka.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class HeightSquareImageView extends ImageView {
    public HeightSquareImageView(Context context) {
        super(context);
    }

    public HeightSquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeightSquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}