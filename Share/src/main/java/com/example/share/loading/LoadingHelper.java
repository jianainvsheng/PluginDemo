package com.example.share.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.share.R;
import com.example.share.loading.view.ZLoadingView;
import com.example.tv.widget.dialog.helper.BaseDialogHelper;


/**
 * Created by yangjian-ds3 on 2018/3/21.
 */

public class LoadingHelper extends BaseDialogHelper<DialogNormalBuilder> implements Dialog.OnDismissListener, DialogInterface.OnShowListener{


    private ZLoadingView mLoading;
    private TextView textView;
    private double  mDurationTimePercent    = 1.0f;

    public LoadingHelper(Context context) {
        super(context);
    }

    @Override
    public View onCreateContextView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_loading_dialog, null);
        mLoading = view.findViewById(R.id.activity_loading);
        textView = view.findViewById(R.id.activity_bookloading_text);
        return view;
    }

    @Override
    public void setBuilder(DialogNormalBuilder builder, final Dialog dialog) {
        super.setBuilder(builder, dialog);
        if(builder.getTitle() != null && builder.getTitle().length() != 0){
            textView.setText(builder.getTitle());
        }
        Window window = getDialog().getWindow();
        if (null != window) {
            window.setGravity(Gravity.CENTER);
        }
//        Window dialogWindow = getDialog().getWindow();
//        WindowManager.LayoutParams params = dialogWindow.getAttributes();
//        params.width = (int) (ScreenUtils.getDisplayWidth(getContext()) * 0.85);
//        params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
//        dialogWindow.setAttributes(params);
        mLoading.setLoadingBuilder(builder.getLoadingBuilderType());
        // 设置间隔百分比
        if (mLoading.getZLoadingBuilder() != null)
        {
            mLoading.getZLoadingBuilder().setDurationTimePercent(this.mDurationTimePercent);
        }
        mLoading.setColorFilter(builder.getLoadingBuilderColor(), PorterDuff.Mode.SRC_ATOP);
        dialog.setOnDismissListener(this);
        dialog.setOnShowListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

//        if(mLoading.isStart()){
//            mLoading.stop();
//        }
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {

//        if(!mLoading.isStart()){
//            mLoading.start();
//        }
        mLoading.setVisibility(View.VISIBLE);
    }
}
