package com.example.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface IInjectMethod {
    /**
     * 插入注解类的构造方法中
     * @return
     */
    String visitMethod() default "<init>";

    /**
     * 构造的参数
     * @return
     */
    String visitMethodDesc() default "(Landroid/content/Context;Landroid/view/ViewGroup;)V";
}
