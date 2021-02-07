package com.example.buildsrc.plugin.processor.annotaion;
import com.example.annotation.method.IViewAdapter;
import com.example.annotation.type.IViewType;
import com.example.buildsrc.plugin.processor.InjectProcessor;
import com.example.buildsrc.plugin.processor.base.BaseAnnotation;
import com.example.buildsrc.plugin.utils.TypeUtils;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ViewAdapterAnnotation extends BaseAnnotation<IViewAdapter, InjectProcessor> {

    private boolean canInject = false;

    public ViewAdapterAnnotation(InjectProcessor processor) {
        super(processor);
    }

    @Override
    public void analysisAnnotation(String className, String methodName, String methodDesc) {
        super.analysisAnnotation(className, methodName, methodDesc);
        ViewTypeAnnotation typeAnnotation = getAnnotationProcessor().findAnnotation(TypeUtils.getDescriptor(IViewType.class),ViewTypeAnnotation.class);
        if(typeAnnotation != null){
            canInject = typeAnnotation.containsClassName(className);
        }
    }

    public boolean canInject(){
        return canInject;
    }

    /**
     *  mv.visitVarInsn(ALOAD, 0);
     *             mv.visitMethodInsn(INVOKEVIRTUAL, "com/example/share/viewprocessor/BaseViewProcessor", "getParentView", "()Landroid/view/ViewGroup;", false);
     *             mv.visitVarInsn(ALOAD, 1);
     *             mv.visitMethodInsn(INVOKESTATIC, "com/example/viewcompiler/ViewProcessor", "setAdapter", "(Landroid/view/ViewGroup;Lcom/example/tv/widget/recyclerview/BaseRecyclerAdapter;)V", false);
     * @param methodVisitor
     * @return
     */
    public MethodVisitor createMethodVisitor(MethodVisitor methodVisitor){
        MethodVisitor method = new MethodVisitor(Opcodes.ASM4,methodVisitor){
            @Override
            public void visitInsn(int opcode) {
                if(canInject){
                    if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
                        mv.visitVarInsn(Opcodes.ALOAD,0);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/example/share/viewprocessor/BaseViewProcessor", "getParentView", "()Landroid/view/ViewGroup;", false);
                        mv.visitVarInsn(Opcodes.ALOAD,1);
                        mv.visitMethodInsn(INVOKESTATIC,getTargetInfo().getTargetType(),getTargetInfo().getTargetMethod(),getTargetInfo().getTargetMethodDesc(),false);
                    }
                }
                super.visitInsn(opcode);
            }

            @Override
            public void visitMaxs(int maxStack, int maxLocals) {
                super.visitMaxs(Math.max(2, maxStack), maxLocals);
            }
        };
        return method;
    }
}
