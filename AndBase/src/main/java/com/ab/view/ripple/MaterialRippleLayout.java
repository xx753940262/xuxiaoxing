package com.ab.view.ripple;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.ab.R;

/**
 * 描述：点击水波动画-02
 * 作者：徐小星 on 2016/9/1 0001 10:18
 * 邮箱：xx@yougudongli.com
 */
public class MaterialRippleLayout extends RelativeLayout implements MaterialCircleEffectDetector.Callback {
    private MaterialCircleEffectDetector mDetector;

    public MaterialRippleLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MaterialTextView, defStyle, 0);
        int color = a.getColor(R.styleable.MaterialTextView_maskColor, Color.parseColor("#ffffff"));
        a.recycle();
        mDetector = new MaterialCircleEffectDetector(getContext(), this, this, color);
    }

    public void setMaterialBackgroundDetector(int color) {
        if (mDetector != null) {
            mDetector.setColor(color);
        } else {
            mDetector = new MaterialCircleEffectDetector(getContext(), this, this, color);
        }
    }

    /**
     * 隐藏按下的颜色
     */
    public void setHideBackGroud() {
        mDetector.setShowFocusColor(false);
        mDetector.setAlpha(55);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDetector.onSizeChanged(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superResult = super.onTouchEvent(event);
        return mDetector.onTouchEvent(event, superResult);
    }

    public void cancelAnimator() {
        mDetector.cancelAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        mDetector.draw(canvas);
    }

    /**
     * When the view performClick, we should ensure the background animation appears.
     * So we will handle the action in mDetector;
     *
     * @return
     */
    @Override
    public boolean performClick() {
        return mDetector.handlePerformClick();
    }

    /**
     * When the view performClick, we should ensure the background animation appears.
     * So we will handle the action in mDetector;
     *
     * @return
     */
    @Override
    public boolean performLongClick() {
        return mDetector.handlePerformLongClick();
    }


    @Override
    public void performClickAfterAnimation() {
        super.performClick();
    }

    @Override
    public void performLongClickAfterAnimation() {
        super.performLongClick();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        ;
        return mDetector.onTouchEvent(ev, result);
    }
}

