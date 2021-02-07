package com.example.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ITargetCallback {

    String method() default "<init>";

    String methodDesc() default "(Landroid/content/Context;Landroid/view/ViewGroup;)V";
}
