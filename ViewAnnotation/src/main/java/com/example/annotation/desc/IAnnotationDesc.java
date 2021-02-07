package com.example.annotation.desc;

import com.example.annotation.method.IViewAdapter;
import com.example.annotation.method.IViewClick;
import com.example.annotation.method.IViewCreate;
import com.example.annotation.type.IViewType;
import com.example.annotation.utils.TypeUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IAnnotationDesc {

    AnnotationTypeEnum value();

    enum AnnotationTypeEnum{

        I_PROCESSOR_TYPE(TypeUtils.getDescriptor(IViewType.class)),
        I_VIEW_ADAPTER(TypeUtils.getDescriptor(IViewAdapter.class)),
        I_VIEW_CLICK(TypeUtils.getDescriptor(IViewClick.class)),
        I_VIEW_CREATE(TypeUtils.getDescriptor(IViewCreate.class)),
        ;
        private String mAnnotationDesc;

        private AnnotationTypeEnum(String annotationDesc){
            this.mAnnotationDesc = annotationDesc;
        }

        public String getAnnotationDesc(){
            return mAnnotationDesc;
        }
    }
}
