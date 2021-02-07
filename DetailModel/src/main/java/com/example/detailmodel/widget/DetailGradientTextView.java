package com.example.detailmodel.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.detailmodel.R;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;


public
class DetailGradientTextView extends TextView {

    private @ColorInt
    int mStartColor;

    private @ColorInt int mEndColor;

    private boolean isRequestLayout = false;

    public DetailGradientTextView(Context context) {
        this(context,null);
    }

    public DetailGradientTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DetailGradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DetailGradientTextView);
            mStartColor = typedArray.getColor(R.styleable.DetailGradientTextView_startColor,0);
            mEndColor = typedArray.getColor(R.styleable.DetailGradientTextView_endColor,0);
            typedArray.recycle();
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        this.mStartColor = color;
        this.mEndColor = color;
        isRequestLayout = true;
        requestLayout();
    }

    public void setTextColor(@ColorInt int startColor, @ColorInt int endColor){
        this.mStartColor = startColor;
        this.mEndColor = endColor;
        isRequestLayout = true;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if ((changed || isRequestLayout) && mStartColor != 0 && mEndColor != 0) {
            isRequestLayout = false;
            @SuppressLint("DrawAllocation") LinearGradient gradient = new LinearGradient(0, 0, getWidth(), 0,
                    mStartColor,
                    mEndColor,
                    Shader.TileMode.CLAMP);
            getPaint().setShader(gradient);
        }
    }
}
