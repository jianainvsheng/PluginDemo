package com.example.share.base;

import android.app.Dialog;
import android.os.Build;

import com.example.share.R;
import com.example.share.loading.DialogNormalBuilder;
import com.example.share.loading.LoadingHelper;
import com.example.share.mvp.BaseNormalPresenter;
import com.example.share.mvp.BaseNormalView;
import com.example.tv.mvp.MvpActivity;
import com.example.tv.widget.dialog.commom.GNormalDialog;

import androidx.annotation.Keep;

@Keep
public abstract class BaseNormalActivity<V extends BaseNormalView, P extends BaseNormalPresenter<V>>
        extends MvpActivity<V, P> implements
        //EmptyViewBox.OnEmptyClickListener,
        BaseNormalView{

    //private EmptyViewBox mDefaultView;

    private Dialog dialog;

    @Override
    public void showLoadingDialog() {
        if (!this.isFinishing() && (Build.VERSION.SDK_INT < 17 || !this.isDestroyed())) {
            if (null == this.dialog) {
                dialog = GNormalDialog.onCreateBuiler(new DialogNormalBuilder(this))
                        .setThemeStyleResId(R.style.dialog_style)
                        .setHelperClass(LoadingHelper.class)
                        .build();
                dialog.show();
            } else if (!this.dialog.isShowing()) {
                this.dialog.show();
            }

        }
    }

    @Override
    public void hideLoadingDialog() {
        if (!this.isFinishing() && (Build.VERSION.SDK_INT < 17 || !this.isDestroyed())) {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        }
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }

}
