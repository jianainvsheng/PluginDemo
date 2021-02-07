package com.example.annotation.viewenum;

import com.example.annotation.method.IViewAdapter;
import com.example.annotation.method.IViewCreate;
import com.example.annotation.utils.TypeUtils;

public enum MethodEnum {

    VIEW_ADAPTER(TypeUtils.getDescriptor(IViewAdapter.class),"描述设置adapter的功能"),
    VIEW_CREATE(TypeUtils.getDescriptor(IViewCreate.class),"描述创建view的功能")
    ;
    private String mMethodClass;

    private String mDesc;

    private MethodEnum(String methodClass, String desc){
        this.mDesc = desc;
        this.mMethodClass = methodClass;
    }

    public String getmMethodClass() {
        return mMethodClass;
    }

    public String getmDesc() {
        return mDesc;
    }
}
