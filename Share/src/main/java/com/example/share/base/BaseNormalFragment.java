package com.example.share.base;

import android.app.Dialog;

import com.example.share.R;
import com.example.share.loading.DialogNormalBuilder;
import com.example.share.loading.LoadingHelper;
import com.example.share.mvp.BaseNormalPresenter;
import com.example.share.mvp.BaseNormalView;
import com.example.tv.mvp.MvpFragment;
import com.example.tv.widget.dialog.commom.GNormalDialog;
public abstract class BaseNormalFragment<V extends BaseNormalView, P extends BaseNormalPresenter<V>>
        extends MvpFragment<V, P> implements
       // EmptyViewBox.OnEmptyClickListener,
        BaseNormalView {

    //private EmptyViewBox mDefaultView;

    private Dialog dialog;

    @Override
    public void showLoadingDialog() {

        if (isDetached() || getActivity()==null) {
            return;
        }

        if (dialog == null) {
            dialog = GNormalDialog.onCreateBuiler(new DialogNormalBuilder(getContext()))
                    .setThemeStyleResId(R.style.dialog_style)
                    .setHelperClass(LoadingHelper.class)
                    .build();
            dialog.show();
        } else {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public Dialog getDialog() {
        return dialog;
    }
}
