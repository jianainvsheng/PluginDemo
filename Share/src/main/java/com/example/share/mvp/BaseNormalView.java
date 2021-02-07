package com.example.share.mvp;

import android.app.Dialog;

import com.example.tv.mvp.MvpView;

/**
 * Created by yangjian on 2017/8/1.
 */

public interface BaseNormalView extends MvpView {

	/**
	 * 展示dialog
	 */
	void showLoadingDialog();

	/**
	 * 消失dialog
	 */
	void hideLoadingDialog();

	/**
	 * 获取dialog
	 * @return
	 */
	Dialog getDialog();

}
