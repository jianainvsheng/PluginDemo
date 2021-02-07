package com.example.buildsrc.plugin.processor.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseProcessor<R extends BaseProcessor<R>> {

    private Map<String,BaseAnnotation<?,?>> mAnnotationInfo = new HashMap<>();

    private Map<String,BaseAnnotation<?,?>> mAnnotationDesc = new HashMap<>();

    public BaseProcessor(){

        Set<Class<? extends BaseAnnotation<?,?>>> annotationClsSet = getSupportedAnnotationTypes();
        for(Class<? extends BaseAnnotation<?,?>> cls : annotationClsSet){
            try {
                Constructor<? extends BaseAnnotation<?, ?>> constructor = cls.getConstructor(getAnnotationProcessClass());
                BaseAnnotation annotation = constructor.newInstance(this);
                mAnnotationInfo.put(annotation.getAnnotationDesc(),annotation);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract Set<Class<? extends BaseAnnotation<?,?>>> getSupportedAnnotationTypes();

    public boolean containsAnnotationDesc(String annotationDesc){
        return mAnnotationInfo.containsKey(annotationDesc);
    }

    public <A extends BaseAnnotation<?,?>> A findAnnotation(String annotationDesc,Class<A> cls){
        if(cls != null){
            if(containsAnnotationDesc(annotationDesc)){
                BaseAnnotation<?,?> annotation = mAnnotationInfo.get(annotationDesc);
                if(annotation.getChildClass() == cls){
                    return (A) annotation;
                }
            }
            if(mAnnotationDesc.containsKey(annotationDesc)){
                BaseAnnotation<?,?> annotation = mAnnotationDesc.get(annotationDesc);
                if(annotation.getChildClass() == cls){
                    return (A) annotation;
                }
            }
        }
        return null;
    }

    private Class<R> getAnnotationProcessClass() {

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
                    && tClass[0].toString().contains("class")) {
                Class<R> cls = (Class<R>) tClass[0];
                return cls;
            }
        }
        return null;
    }

    public void addAnnotationDescMap(String key,BaseAnnotation<?,?> value){
        mAnnotationDesc.put(key,value);
    }
}
