package com.example.share.loading;

import android.content.Context;
import android.graphics.Color;
import com.example.tv.widget.dialog.builder.BaseBuilder;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class DialogNormalBuilder extends BaseBuilder<DialogNormalBuilder> {

    private String title;            // 标题文字

    private Z_TYPE mLoadingBuilderType = Z_TYPE.ROTATE_CIRCLE;

    private       int                    mLoadingBuilderColor = Color.parseColor("#99FFFFFF");

    public DialogNormalBuilder(Context context) {
        super(context);
    }

    public String getTitle() {
        return title;
    }

    public DialogNormalBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogNormalBuilder setLoadingBuilder(@NonNull Z_TYPE type)
    {
        this.mLoadingBuilderType = type;
        return this;
    }

    public DialogNormalBuilder setLoadingColor(@ColorInt int color)
    {
        this.mLoadingBuilderColor = color;
        return this;
    }

    public int getLoadingBuilderColor() {
        return mLoadingBuilderColor;
    }

    public Z_TYPE getLoadingBuilderType() {
        return mLoadingBuilderType;
    }
}
