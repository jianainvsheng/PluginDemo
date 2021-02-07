package com.example.detailmodel.processor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.annotation.method.IViewClick;
import com.example.share.viewprocessor.BaseViewProcessor;

public class RecyclerViewProcessor extends BaseViewProcessor {

    public RecyclerViewProcessor(Context context,ViewGroup parentView) {
        super(context,parentView);
    }

    @IViewClick
    public void onItemClick(int position){
        Toast.makeText(getContext(),"点击了 ： " + position,Toast.LENGTH_SHORT).show();
    }
}
