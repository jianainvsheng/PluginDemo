package com.example.share.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.share.mvp.BaseNormalPresenter;
import com.example.share.mvp.BaseNormalView;
import com.example.tv.panel.eventmanger.EventManager;
import com.example.tv.panel.eventmanger.internal.kernel.ISubscriber;
import com.example.tv.panel.panelmanger.panel.BaseSubscriberPanel;
import com.example.tv.panel.panelmanger.panel.IPanelManager;
import com.example.tv.panel.panelmanger.panel.PanelUtils;
import com.example.tv.panel.panelmanger.panel.context.SubscriberContext;
import com.example.tv.panel.panelmanger.panel.impl.SubscriberContextPanel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */
@Keep
public abstract class BaseSubscriberFragment<V extends BaseNormalView, P extends BaseNormalPresenter<V>> extends BaseNormalFragment<V,P> implements IPanelManager, ISubscriber {

    private SubscriberContext mSubscriberContext;

    public BaseSubscriberFragment() {
        mSubscriberContext = new SubscriberContextPanel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventManager.getDefault().registerObserver(getContext(), this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PanelUtils.onFindHolder(getContext(),this,this);
    }

    @Override
    public void addSubscriberHolder(BaseSubscriberPanel<?> holder) {
        mSubscriberContext.onAttach(holder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriberContext != null) {
            mSubscriberContext.onDetachedFromContext(getContext());
        }
        EventManager.getDefault().removeObserver(getContext(), this);
    }

    @NonNull
    @Override
    public P createPresenter() {

        Class<?> cls = getPresentClass();
        if(cls != null){
            try {
                return (P) cls.newInstance();
            } catch (java.lang.InstantiationException e) {
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
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }
}
