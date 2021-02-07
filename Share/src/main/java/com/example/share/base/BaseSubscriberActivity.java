package com.example.share.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.example.share.mvp.BaseNormalPresenter;
import com.example.share.mvp.BaseNormalView;
import com.example.share.utils.TvViewAnimatorUtils;
import com.example.tv.panel.eventmanger.EventManager;
import com.example.tv.panel.eventmanger.internal.kernel.ISubscriber;
import com.example.tv.panel.panelmanger.panel.BaseSubscriberPanel;
import com.example.tv.panel.panelmanger.panel.IPanelManager;
import com.example.tv.panel.panelmanger.panel.PanelUtils;
import com.example.tv.panel.panelmanger.panel.context.SubscriberContext;
import com.example.tv.panel.panelmanger.panel.impl.SubscriberContextPanel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */

public abstract class BaseSubscriberActivity<V extends BaseNormalView, P extends BaseNormalPresenter<V>> extends BaseNormalActivity<V,P> implements IPanelManager, ISubscriber,ViewTreeObserver.OnGlobalFocusChangeListener {

    private SubscriberContext mSubscriberContext;

    private ViewTreeObserver mViewTreeObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mSubscriberContext = new SubscriberContextPanel();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        EventManager.getDefault().registerObserver(this, this);
        setContentView(onCreateLayoutResId());
        PanelUtils.onFindHolder(this,this,this);
        mViewTreeObserver = this.findViewById(android.R.id.content).getViewTreeObserver();
        mViewTreeObserver.addOnGlobalFocusChangeListener(this);
    }

    public abstract int onCreateLayoutResId();

    @Override
    public void addSubscriberHolder(BaseSubscriberPanel<?> holder){
        mSubscriberContext.onAttach(holder);
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (oldFocus != null) {
            TvViewAnimatorUtils.setViewAnimator(oldFocus, false);
        }
        if (newFocus != null) {
            newFocus.bringToFront();
            TvViewAnimatorUtils.setViewAnimator(newFocus, true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscriberContext != null){
            mSubscriberContext.onDetachedFromContext(this);
        }
        EventManager.getDefault().removeObserver(this, this);
    }

    @Override
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }

    @NonNull
    @Override
    public P createPresenter() {

        Class<?> cls = getPresentClass();
        if(cls != null){
            try {
                return (P) cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Class<?> getPresentClass(){

        Type type = getClass().getGenericSuperclass();
        if(type == null){
            return null;
        }

        if(type.toString().contains("<")
                && type.toString().contains(">")){
            Type[] tClass = ((ParameterizedType)getClass().
                    getGenericSuperclass()).getActualTypeArguments();
            if(tClass != null
                    && tClass.length >= 2
                    && tClass[1].toString().contains("class")){
                return (Class<?>) tClass[1];
            }
        }
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
