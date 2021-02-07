package com.example.detailmodel.thread;

import android.os.Handler;
import android.os.Looper;

import com.example.share.base.data.BaseResponse;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseThread<R extends BaseResponse<D>,D> extends Thread{

    private WeakReference<Handler> mMainHandler;

    private WeakReference<CallBack<R>> mCallBack;

    private boolean isRecycle = false;

    public BaseThread(CallBack<R> callBack){
        Handler handler = new Handler(Looper.getMainLooper());
        mMainHandler = new WeakReference<>(handler);
        mCallBack = new WeakReference<>(callBack);
    }

    @Override
    public void run() {
        super.run();
        if(isRecycle()){
            return;
        }
        Class<R> cls = getResponseClass();
        R response = null;
        if(cls != null){
            try {
                response = cls.newInstance();
            } catch (Exception e) {
                response = null;
               // e.printStackTrace();
            }
        }
        execute(response);
        if(isRecycle()){
            return;
        }
        sendMessage(response);
    }

    public Class<R> getResponseClass(){
        Type type = getClass().getGenericSuperclass();
        if(type == null){
            return null;
        }
        if(type.toString().contains("<")
                && type.toString().contains(">")){
            Type[] tClass = ((ParameterizedType)getClass().
                    getGenericSuperclass()).getActualTypeArguments();
            if(tClass != null
                    && tClass.length >= 1){
                if(tClass[0].toString().contains("class")){
                    Class<R> cls = (Class<R>) tClass[0];
                    return cls;
                }else if(tClass[0] instanceof ParameterizedType){
                    ParameterizedType type1 = (ParameterizedType) tClass[0];
                    Type type2 = type1.getRawType();
                    if(type2.toString().contains("class")){
                        return (Class<R>) type2;
                    }
                }
            }
        }
        return null;
    }

    protected abstract void execute(R response);

    public synchronized void recycle(){
        isRecycle = true;
        mMainHandler = null;
        mCallBack = null;
    }

    public boolean isRecycle(){
        return isRecycle || mMainHandler.get() == null || mCallBack.get() == null;
    }

    public void sendMessage(final R response){
        if(!isRecycle && mMainHandler != null){
            Handler handler = mMainHandler.get();
            if(handler != null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mCallBack != null){
                            CallBack<R> callBack = mCallBack.get();
                            if(callBack != null){
                                callBack.call(response);
                            }
                        }
                    }
                });
            }
        }
    }

    public static interface CallBack<R>{

        /**
         * 返回值
         * @param data
         */
        void call(R data);
    }
}
