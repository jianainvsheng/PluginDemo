package com.example.viewcompiler.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tv.widget.recyclerview.BaseRecyclerAdapter;
import com.example.tv.widget.tvrecyclerview.TvHeaderRecyclerView;
import com.example.tv.widget.tvrecyclerview.widget.TvRecyclerView;
import com.example.viewcompiler.R;

import java.util.ArrayList;
import java.util.List;

public class ViewInfo {

    private TvHeaderRecyclerView mChildView;

    private List<ViewOnClick> mViewClick;

    private ViewGroup mParentView;

    public ViewInfo(ViewGroup viewGroup){
        if(viewGroup != null){
            mParentView = viewGroup;
            viewGroup.removeAllViews();
            viewGroup.setTag(R.id.view_tag_processor_id,this);
        }else{
            throw new NullPointerException("viewGroup is not null");
        }
    }

    public void attachViewToParent(Context context,ViewGroup parent){
        View view = LayoutInflater.from(context)
                .inflate(R.layout.view_processor_content_layout,null);
        mChildView = view.findViewById(R.id.view_processor_content_recyclerview);
        mParentView.addView(view);
        mChildView.setOnItemListener(new TvRecyclerView.OnItemListener() {
            @Override
            public void onItemPreSelected(TvRecyclerView tvRecyclerView, View view, int i) {

            }

            @Override
            public void onItemSelected(TvRecyclerView tvRecyclerView, View view, int i) {

            }

            @Override
            public void onItemClick(TvRecyclerView tvRecyclerView, View view, int i) {
                if(mViewClick != null){
                    for(ViewOnClick click : mViewClick){
                        click.onItemClick(i);
                    }
                }
            }
        });
    }

    public void addItemClick(Object targetClick,String methodName){
        ViewOnClick click = new ViewOnClick(targetClick,methodName);
        if(mViewClick == null){
            mViewClick = new ArrayList<>();
        }
        mViewClick.add(click);
    }

    public void setAdapter(BaseRecyclerAdapter<?,?> adapter){
        if(mChildView != null){
            mChildView.setAdapter(adapter);
        }
    }
}
