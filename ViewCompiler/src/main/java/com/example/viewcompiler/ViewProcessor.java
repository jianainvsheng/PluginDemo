package com.example.viewcompiler;

import android.content.Context;
import android.view.ViewGroup;

import com.example.tv.widget.recyclerview.BaseRecyclerAdapter;
import com.example.viewcompiler.info.ViewInfo;

public class ViewProcessor {

    public static void onCreateViewInfo(ViewGroup parentView){
        ViewInfo viewInfo = new ViewInfo(parentView);
    }

    public static void attachViewToParent(Context context,ViewGroup parentView){
        if(parentView != null){
            Object info = parentView.getTag(R.id.view_tag_processor_id);
            if(info instanceof ViewInfo){
                ViewInfo viewInfo = (ViewInfo) info;
                viewInfo.attachViewToParent(context,parentView);
            }
        }
    }

    public static void setAdapter(ViewGroup parentView, BaseRecyclerAdapter<?,?> adapter){
        if(parentView != null){
            Object info = parentView.getTag(R.id.view_tag_processor_id);
            if(info instanceof ViewInfo){
                ViewInfo viewInfo = (ViewInfo) info;
                viewInfo.setAdapter(adapter);
            }
        }
    }

    public static void addOnItemClick(ViewGroup parentView,Object target,String method){
        if(parentView != null){
            Object info = parentView.getTag(R.id.view_tag_processor_id);
            if(info instanceof ViewInfo){
                ViewInfo viewInfo = (ViewInfo) info;
                viewInfo.addItemClick(target,method);
            }
        }
    }
}
