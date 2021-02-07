package com.example.buildsrc.plugin.processor.target;

public class TargetInfo {

    private String mTargetType;

    private String mTargetMethod;

    private String mTargetMethodDesc;

    public String getTargetType() {
        return mTargetType;
    }

    public void setTargetType(String targetType) {
        this.mTargetType = targetType;
    }

    public String getTargetMethod() {
        return mTargetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.mTargetMethod = targetMethod;
    }

    public String getTargetMethodDesc() {
        return mTargetMethodDesc;
    }

    public void setTargetMethodDesc(String targetMethodDesc) {
        this.mTargetMethodDesc = targetMethodDesc;
    }
}
