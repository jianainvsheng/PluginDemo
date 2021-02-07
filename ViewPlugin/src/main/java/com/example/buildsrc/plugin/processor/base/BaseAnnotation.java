package com.example.buildsrc.plugin.processor.base;

import com.example.annotation.desc.IAnnotationDesc;
import com.example.annotation.inject.IInjectMethod;
import com.example.annotation.inject.ITargetCallback;
import com.example.annotation.inject.ITargetInject;
import com.example.buildsrc.plugin.processor.target.TargetCallback;
import com.example.buildsrc.plugin.processor.target.TargetInfo;
import com.example.buildsrc.plugin.processor.target.TargetType;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseAnnotation<A extends Annotation,P extends BaseProcessor> {

    private Class<A> mAnnotation;

    private String mAnnotationDesc;

    private TargetInfo mTarget;

    private TargetCallback mTargetCallback;

    private TargetType mTargetType;

    private String mAnnotationClassName;

    private String mAnnotationMethodName;

    private String mAnnotationMethodDesc;

    private P mAnnotationProcessor;

    public BaseAnnotation(P processor){
        mAnnotationProcessor = processor;
        mAnnotation = getAnnotationClass();
        ITargetInject inject = mAnnotation.getAnnotation(ITargetInject.class);
        if(inject != null){
            mTarget = new TargetInfo();
            mTarget.setTargetType(inject.type());
            mTarget.setTargetMethod(inject.method());
            mTarget.setTargetMethodDesc(inject.methodDesc());
        }
        IAnnotationDesc annotationDescClass = mAnnotation.getAnnotation(IAnnotationDesc.class);
        if(annotationDescClass != null){
            IAnnotationDesc.AnnotationTypeEnum value = annotationDescClass.value();
            mAnnotationDesc = value.getAnnotationDesc();
        }
        ITargetCallback iTargetCallback = mAnnotation.getAnnotation(ITargetCallback.class);
        if(iTargetCallback != null){
            mTargetCallback = new TargetCallback();
            mTargetCallback.setMethod(iTargetCallback.method());
            mTargetCallback.setMethodDesc(iTargetCallback.methodDesc());
        }

        IInjectMethod iInjectMethod = mAnnotation.getAnnotation(IInjectMethod.class);
        if(iInjectMethod != null){
            mTargetType = new TargetType();
            mTargetType.setVisitMethod(iInjectMethod.visitMethod());
            mTargetType.setVisitMethodDes(iInjectMethod.visitMethodDesc());
        }
    }

    public void analysisAnnotation(String className,String methodName,String methodDesc){
        mAnnotationClassName = className;
        mAnnotationMethodDesc = methodDesc;
        mAnnotationMethodName = methodName;
        getAnnotationProcessor().addAnnotationDescMap(className + methodName + methodDesc,this);
    }

    private Class<A> getAnnotationClass() {
        Type type = getClass().getGenericSuperclass();
        if (type == null) {
            return null;
        }
        if (type.toString().contains("<")
                && type.toString().contains(">")) {
            Type[] tClass = ((ParameterizedType) getClass().
                    getGenericSuperclass()).getActualTypeArguments();
            if (tClass != null
                    && tClass.length >= 1
                    && tClass[0].toString().contains("interface")) {
                Class<A> cls = (Class<A>) tClass[0];
                return cls;
            }
        }
        return null;
    }

    public Class<A> getAnnotation() {
        return mAnnotation;
    }

    public String getAnnotationDesc(){
        return mAnnotationDesc;
    }

    public Class<? extends BaseAnnotation<A,P>> getChildClass(){
        return (Class<? extends BaseAnnotation<A,P>>) this.getClass();
    }

    public TargetInfo getTargetInfo(){
        return mTarget;
    }

    public TargetCallback getTargetCallback(){
        return mTargetCallback;
    }

    public TargetType getTargetType(){
        return mTargetType;
    }

    public String getAnnotationClassName() {
        return mAnnotationClassName;
    }

    public String getAnnotationMethodName() {
        return mAnnotationMethodName;
    }

    public String getAnnotationMethodDesc() {
        return mAnnotationMethodDesc;
    }

    public P getAnnotationProcessor(){
        return mAnnotationProcessor;
    }
}
