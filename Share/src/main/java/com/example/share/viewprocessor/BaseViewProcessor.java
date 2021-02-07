package com.example.share.viewprocessor;

import android.content.Context;
import android.view.ViewGroup;
import com.example.annotation.method.IViewAdapter;
import com.example.annotation.method.IViewCreate;
import com.example.annotation.type.IViewType;
import com.example.tv.widget.recyclerview.BaseRecyclerAdapter;

@IViewType
public class BaseViewProcessor{

    private ViewGroup mParentView;

    private Context mContext;

    public BaseViewProcessor(Context context,ViewGroup parentView){
        this.mContext = context;
        this.mParentView = parentView;
        if(mContext == null || mParentView == null){
            throw new NullPointerException("context and parent must not null");
        }
    }

    public ViewGroup getParentView() {
        return mParentView;
    }

    public void setParentView(ViewGroup mParentView) {
        this.mParentView = mParentView;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @IViewCreate
    public void onCreateRecycler(){
    }

    @IViewAdapter
    public void setAdapter(BaseRecyclerAdapter<?,?> adapter){
    }
}
