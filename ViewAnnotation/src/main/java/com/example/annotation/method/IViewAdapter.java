package com.example.annotation.method;

import com.example.annotation.desc.IAnnotationDesc;
import com.example.annotation.inject.ITargetInject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@ITargetInject(method = "setAdapter",methodDesc = "(Landroid/view/ViewGroup;Lcom/example/tv/widget/recyclerview/BaseRecyclerAdapter;)V")
@IAnnotationDesc(IAnnotationDesc.AnnotationTypeEnum.I_VIEW_ADAPTER)
public @interface IViewAdapter {

}
