package com.bcuzek.magisterka.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bcuzek.magisterka.R;


public class SeekCircuit extends View {
    private final int mAngleOffset = -90;
    private int mMax = 100;
    private int mProgress = 0;
    private int mProgressWidth = 4;
    private int mArcWidth = 2;
    private int mStartAngle = 0;
    private int mSweepAngle = 360;
    private int mRotation = 0;
    private boolean mRoundedEdges = false;
    private boolean mClockwise = true;
    private boolean mEnabled = true;
    private int mArcRadius = 0;
    private long mProgressSweep = 0;
    private RectF mArcRect = new RectF();
    private Paint mArcPaint;
    private Paint thumbColor;
    private Paint mProgressPaint;
    private int mTranslateX;
    private int mTranslateY;
    private int mThumbXPos;
    private int mThumbYPos;
    private OnClickCircuit onClick;

    public SeekCircuit(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SeekCircuit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.seekCircuitStyle);
    }

    public SeekCircuit(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public void setOnClick(OnClickCircuit onClick) {
        this.onClick = onClick;
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final Resources res = getResources();
        float density = context.getResources().getDisplayMetrics().density;
        int arcColor = res.getColor(R.color.colorBlue);
        int progressColor = res.getColor(R.color.colorRed);
        mProgressWidth = (int) (mProgressWidth * density);

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.SeekArc, defStyle, 0);
            mMax = a.getInteger(R.styleable.SeekArc_max, mMax);
            mProgress = a.getInteger(R.styleable.SeekArc_progress, mProgress);
            mProgressWidth = (int) a.getDimension(
                    R.styleable.SeekArc_progressWidth, mProgressWidth);
            mArcWidth = (int) a.getDimension(R.styleable.SeekArc_arcWidth,
                    mArcWidth);
            mStartAngle = a.getInt(R.styleable.SeekArc_startAngle, mStartAngle);
            mSweepAngle = a.getInt(R.styleable.SeekArc_sweepAngle, mSweepAngle);
            mRotation = a.getInt(R.styleable.SeekArc_rotation, mRotation);
            mRoundedEdges = a.getBoolean(R.styleable.SeekArc_roundEdges,
                    mRoundedEdges);
            mClockwise = a.getBoolean(R.styleable.SeekArc_clockwise,
                    mClockwise);
            mEnabled = a.getBoolean(R.styleable.SeekArc_enabled, mEnabled);

            arcColor = a.getColor(R.styleable.SeekArc_arcColor, arcColor);
            progressColor = a.getColor(R.styleable.SeekArc_progressColor,
                    progressColor);

            a.recycle();
        }
        mProgress = (mProgress > mMax) ? mMax : mProgress;
        mProgress = (mProgress < 0) ? 0 : mProgress;
        mSweepAngle = (mSweepAngle > 360) ? 360 : mSweepAngle;
        mSweepAngle = (mSweepAngle < 0) ? 0 : mSweepAngle;
        mProgressSweep = mProgress / mMax * mSweepAngle;
        mStartAngle = (mStartAngle > 360) ? 0 : mStartAngle;
        mStartAngle = (mStartAngle < 0) ? 0 : mStartAngle;
        mArcPaint = new Paint();
        mArcPaint.setColor(arcColor);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
        thumbColor = new Paint();
        thumbColor.setColor(progressColor);
        thumbColor.setAntiAlias(true);
        thumbColor.setStyle(Paint.Style.STROKE);
        thumbColor.setStrokeWidth(14);
        mProgressPaint = new Paint();
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressWidth);
        if (mRoundedEdges) {
            mArcPaint.setStrokeCap(Paint.Cap.ROUND);
            mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!mClockwise)
            canvas.scale(-2, 2, mArcRect.centerX(), mArcRect.centerY());
        final int arcStart = mStartAngle + mAngleOffset + mRotation;
        final int arcSweep = mSweepAngle;
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(4);

        canvas.drawArc(mArcRect, arcStart, arcSweep, false, mArcPaint);
        canvas.drawArc(mArcRect, arcStart, mProgressSweep, false,
                mProgressPaint);
        if (mEnabled) {
            canvas.translate(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos);
            canvas.drawCircle(0, 0, 7, thumbColor);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final double xClickPos = event.getX();
        final double yPosClick = event.getY();
        final double xCenter = getWidth() / 2;
        final double yCenter = getHeight() / 2;
        final double range = getHeight() / 1.5;

        double x = (xClickPos - xCenter) * (xClickPos - xCenter);
        double y = (yPosClick - yCenter) * (yPosClick - yCenter);
        if (Math.sqrt(x) + Math.sqrt(y) <= Math.sqrt(range * range))
            onClick.onClick();
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        float top = 0;
        float left = 0;
        int arcDiameter = 0;
        mTranslateX = (int) (width * 0.5f);
        mTranslateY = (int) (height * 0.5f);
        arcDiameter = min - getPaddingLeft();
        mArcRadius = arcDiameter / 2;
        top = height / 2 - (arcDiameter / 2);
        left = width / 2 - (arcDiameter / 2);
        mArcRect.set(left, top, left + arcDiameter, top + arcDiameter);
        int arcStart = (int) mProgressSweep + mStartAngle + mRotation + 90;
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(arcStart)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(arcStart)));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void updateThumbPosition() {
        int thumbAngle = (int) (mStartAngle + mProgressSweep + mRotation + 90);
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(thumbAngle)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(thumbAngle)));
    }

    public void printNewPosition(long value) {
        mProgressSweep = value;
    }

    public void setArcRotation(int mRotation) {
        this.mRotation = mRotation;
        updateThumbPosition();
    }

    public interface OnClickCircuit {
        void onClick();
    }

}
