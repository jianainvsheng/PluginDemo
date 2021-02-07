package com.example.annotation.type;

import com.example.annotation.desc.IAnnotationDesc;
import com.example.annotation.inject.IInjectMethod;
import com.example.annotation.inject.ITargetInject;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@ITargetInject(method = "onCreateViewInfo",methodDesc = "(Landroid/view/ViewGroup;)V")
@IInjectMethod
@IAnnotationDesc(IAnnotationDesc.AnnotationTypeEnum.I_PROCESSOR_TYPE)
public @interface IViewType {
}
