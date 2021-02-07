package com.example.annotation.method;
import com.example.annotation.desc.IAnnotationDesc;
import com.example.annotation.inject.ITargetCallback;
import com.example.annotation.inject.ITargetInject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@ITargetInject(method = "addOnItemClick",methodDesc = "(Landroid/view/ViewGroup;Ljava/lang/Object;Ljava/lang/String;)V")
@ITargetCallback
@IAnnotationDesc(IAnnotationDesc.AnnotationTypeEnum.I_VIEW_CLICK)
public @interface IViewClick {
    /**
     * 返回方法中必须有的参数
     * @return
     */
    String methodDesc() default "(I)V";
}
