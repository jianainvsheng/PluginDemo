package com.example.viewcompiler.info;

import android.text.TextUtils;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewOnClick {

    private Object mTargetClick;

    private String mClickMethodName;

    public ViewOnClick(Object targetClick,String methodName){
        this.mTargetClick = targetClick;
        this.mClickMethodName = methodName;
    }

    public void onItemClick(int position){
        if(mTargetClick != null && !TextUtils.isEmpty(mClickMethodName)){
            try {
                Method method = mTargetClick.getClass().getMethod(mClickMethodName,int.class);
                method.invoke(mTargetClick,position);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isViewClickEquals(ViewOnClick viewOnClick){

        if(viewOnClick == null){
            return false;
        }
        if(mTargetClick == viewOnClick.mTargetClick &&
                !TextUtils.isEmpty(mClickMethodName) &&
                mClickMethodName.equals(viewOnClick.mClickMethodName)){
            return true;
        }
        return false;
    }
}
