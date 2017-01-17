package com.flower.rose.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.flower.rose.R;


/**
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/12/下午2:49
 */

public class BGColorView extends FrameLayout {
    private int bgColor;

    public BGColorView(Context context) {
        super(context);
    }

    public BGColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BGColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BGColorView);
        this.bgColor = a.getColor(R.styleable.BGColorView_bgColor, 0xAA000000);
        a.recycle();
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bgColor);
    }
}
