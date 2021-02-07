package com.example.buildsrc.plugin.classvistor;

import com.example.buildsrc.plugin.extension.ViewExtension;
import com.example.buildsrc.plugin.processor.InjectProcessor;
import com.example.buildsrc.plugin.processor.annotaion.ViewAdapterAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewClickAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewCreateAnnotation;
import com.example.buildsrc.plugin.processor.annotaion.ViewTypeAnnotation;
import com.source.plugin.kernel.classvisitor.BaseClassVisitor;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
public class ViewClassVisitor extends BaseClassVisitor<ViewExtension> {

    private boolean isInject = false;

    private String className = "";

    private InjectProcessor mInjectProcessor;

    public ViewClassVisitor(int i, ClassVisitor classVisitor, boolean isInject,
                            ViewExtension extension,InjectProcessor injectProcessor) {
        super(i, classVisitor,extension);
        this.isInject = isInject;
        this.mInjectProcessor = injectProcessor;
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s, s1, s2, strings);
        className = s;
        if(!isInject){
            System.out.println("====className : " + s);
        }

    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if(!isInject && mInjectProcessor != null){
            ViewTypeAnnotation annotation = mInjectProcessor.findAnnotation(desc,ViewTypeAnnotation.class);
            if(annotation != null){
                annotation.analysisAnnotation(className,null,null);
            }
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        if(!isInject){
            methodVisitor = new MethodVisitor(Opcodes.ASM4,methodVisitor) {
                @Override
                public AnnotationVisitor visitAnnotation(String desc1, boolean visible1) {
                    AnnotationVisitor visitor = super.visitAnnotation(desc1, visible1);
                    ViewClickAnnotation annotation = mInjectProcessor.findAnnotation(desc1, ViewClickAnnotation.class);
                    if(annotation != null){
                        visitor = new AnnotationVisitor(Opcodes.ASM4,visitor) {
                            @Override
                            public void visit(String name1, Object value) {
                                super.visit(name1, value);
                                annotation.analysisMethod(name1,value);
                                annotation.analysisAnnotation(className,name,desc);
                            }
                            @Override
                            public void visitEnd() {
                                annotation.analysisMethod("methodDesc","(I)V");
                                annotation.analysisAnnotation(className,name,desc);
                                super.visitEnd();
                            }
                        };
                    }

                    ViewAdapterAnnotation adapterAnnotation = mInjectProcessor.findAnnotation(desc1, ViewAdapterAnnotation.class);
                    if(adapterAnnotation != null){
                        adapterAnnotation.analysisAnnotation(className,name,desc);
                    }

                    ViewCreateAnnotation createAnnotation = mInjectProcessor.findAnnotation(desc1, ViewCreateAnnotation.class);
                    if(createAnnotation != null){
                        createAnnotation.analysisAnnotation(className,name,desc);
                    }
                    return visitor;
                }
            };
//            methodVisitor = new MethodVisitor(Opcodes.ASM4,methodVisitor) {
//                @Override
//                public AnnotationVisitor visitAnnotation(String desc1, boolean visible) {
//
//
//
//                    return super.visitAnnotation(desc1, visible);
//                }
//            };
        }else{
            String key = className + name + desc;
            ViewTypeAnnotation viewTypeAnnotation = mInjectProcessor.findAnnotation(key,ViewTypeAnnotation.class);
            if(viewTypeAnnotation != null){
                methodVisitor = viewTypeAnnotation.createMethodVisitor(methodVisitor);
            }

            ViewCreateAnnotation viewCreateAnnotation = mInjectProcessor.findAnnotation(key,ViewCreateAnnotation.class);
            if(viewCreateAnnotation != null){
                methodVisitor = viewCreateAnnotation.createMethodVisitor(methodVisitor);
            }

            ViewAdapterAnnotation adapterAnnotation = mInjectProcessor.findAnnotation(key,ViewAdapterAnnotation.class);
            if(adapterAnnotation != null){
                methodVisitor = adapterAnnotation.createMethodVisitor(methodVisitor);
            }

            ViewClickAnnotation clickAnnotation = mInjectProcessor.findAnnotation(key,ViewClickAnnotation.class);
            if(clickAnnotation != null){
                methodVisitor = clickAnnotation.createMethodVisitor(methodVisitor);
            }
        }
        return methodVisitor;
    }
}
