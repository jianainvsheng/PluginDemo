package com.example.buildsrc.plugin.processor;
import com.example.buildsrc.plugin.processor.annotaion.ViewAdapterAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewClickAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewCreateAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewTypeAnnotation;
import com.example.buildsrc.plugin.processor.base.BaseAnnotation;
import com.example.buildsrc.plugin.processor.base.BaseProcessor;
import java.util.HashSet;
import java.util.Set;

public class InjectProcessor extends BaseProcessor<InjectProcessor> {

    @Override
    public Set<Class<? extends BaseAnnotation<?, ?>>> getSupportedAnnotationTypes() {
        Set<Class<? extends BaseAnnotation<?, ?>>> annotations = new HashSet<>();
        annotations.add(ViewCreateAnnotation.class);
        annotations.add(ViewTypeAnnotation.class);
        annotations.add(ViewClickAnnotation.class);
        annotations.add(ViewAdapterAnnotation.class);
        return annotations;
    }
}
