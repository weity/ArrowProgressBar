package com.jiozuo.arrowprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Weity on 2015/4/22.
 * ArrowProgressBar like arrow
 */
public class ArrowPb extends View {

    protected long mUiThreadId;

    public ArrowPb(Context context) {
        this(context, null);
    }

    public ArrowPb(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.arrowPbStyle);
    }

    public ArrowPb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mUiThreadId = Thread.currentThread().getId();
        initProgressBar(attrs, defStyleAttr);
    }

    private Path backgroundPath;    //for background
    private Paint backgroundPaint;
    private Path progressPath;      //for progress
    private Paint progressPaint;

    private int mMinWidth;
    private int mMaxWidth;
    private int mMinHeight;
    private int mMaxHeight;


    private void initProgressBar(AttributeSet attrs, int defStyleAttr) {
        mProgress = 0;
        mMinWidth = 50;
        mMaxWidth = 100;
        mMinHeight = 20;
        mMaxHeight = 50;
        backgroundPath = new Path();
        progressPath = new Path();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ArrowPb, defStyleAttr, 0);
        mMax = a.getInt(R.styleable.ArrowPb_max, 100);
        int bgColor = a.getColor(R.styleable.ArrowPb_backgroundColor, android.R.color.holo_blue_bright);
        int proColor = a.getColor(R.styleable.ArrowPb_progressColor, android.R.color.holo_red_light);
        int bgStyle = a.getInt(R.styleable.ArrowPb_backgroundStyle, 0);
        int progress = a.getInt(R.styleable.ArrowPb_progress, 10);
        float dashInterval = a.getDimension(R.styleable.ArrowPb_bgDashInterval, 20f);
        this.setProgress(progress);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(bgColor);
        if (bgStyle == 0) {
            backgroundPaint.setStrokeWidth(1.0f);
            backgroundPaint.setStyle(Paint.Style.STROKE);
            backgroundPaint.setPathEffect(new DashPathEffect(new float[]{dashInterval, dashInterval}, 0));
        } else {
            backgroundPaint.setStyle(Paint.Style.FILL);
        }
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(proColor);
        a.recycle();
    }

    int radio = 10;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgroundArrow(canvas);
        drawProgressArrow(canvas);
    }

    float offsetFraction = 0.05f;
    float progressFraction = 0.1f;

    private void drawProgressArrow(Canvas canvas) {
        if (progressFraction > 0) {
            int centerY = canvas.getHeight() / 2;
            int offsetX = (int) (canvas.getWidth() * offsetFraction);
            progressPath.reset();
            progressPath.moveTo(offsetX, centerY);
            progressPath.lineTo(0, 0);
            progressPath.lineTo(canvas.getWidth() * progressFraction - offsetX, 0);
            progressPath.lineTo(canvas.getWidth() * progressFraction, centerY);
            progressPath.lineTo(canvas.getWidth() * progressFraction - offsetX, canvas.getHeight());
            progressPath.lineTo(0, canvas.getHeight());
            progressPath.close();
            canvas.drawPath(progressPath, progressPaint);
        }
    }


    private void drawBackgroundArrow(Canvas canvas) {
        int centerY = canvas.getHeight() / 2;
        int offsetX = (int) (canvas.getWidth() * offsetFraction);
        backgroundPath.reset();
        backgroundPath.moveTo(offsetX, centerY);
        backgroundPath.lineTo(0, 0);
        backgroundPath.lineTo(canvas.getWidth() - offsetX, 0);
        backgroundPath.lineTo(canvas.getWidth(), centerY);
        backgroundPath.lineTo(canvas.getWidth() - offsetX, canvas.getHeight());
        backgroundPath.lineTo(0, canvas.getHeight());
        backgroundPath.close();
        canvas.drawPath(backgroundPath, backgroundPaint);
    }

    private int mMax;
    private int mProgress;

    public synchronized void setMax(int max) {
        if (max < 0) {
            max = 0;
        }
        if (max != mMax) {
            mMax = max;
            postInvalidate();

            if (mProgress > max) {
                mProgress = max;
            }
        }
    }

    public int getMax() {
        return mMax;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        if (this.mProgress > this.mMax) {
            this.mProgress = mMax;
        } else if (this.mProgress < 0) {
            this.mProgress = 0;
        }
        this.progressFraction = 1.0f * this.mProgress / this.mMax;
        this.invalidate();
    }

    public int getProgress() {
        return this.mProgress;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


}
