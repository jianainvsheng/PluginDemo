package com.example.buildsrc.plugin.processor.annotaion;

import com.example.annotation.method.IViewClick;
import com.example.buildsrc.plugin.processor.InjectProcessor;
import com.example.buildsrc.plugin.processor.base.BaseAnnotation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ViewClickAnnotation extends BaseAnnotation<IViewClick, InjectProcessor> {

    private String mCallbackMethodDesc = "";

    private String mCallbackMethodName = "";

    private boolean canInject = false;

    public ViewClickAnnotation(InjectProcessor processor) {
        super(processor);
    }

    public void analysisMethod(String name, Object value){
        if("methodDesc".equals(name)){
            mCallbackMethodDesc = (String) value;
        }
    }

    @Override
    public void analysisAnnotation(String className, String methodName, String methodDesc) {
        mCallbackMethodName = methodName;
        if(methodDesc.equals(mCallbackMethodDesc)){
            canInject = true;
        }
        if(getTargetCallback() != null){
            super.analysisAnnotation(className, getTargetCallback().getMethod(), getTargetCallback().getMethodDesc());
        }
    }

    public boolean canInject(){
        return canInject;
    }

    /**
     *   mv.visitVarInsn(ALOAD, 2);
     *             mv.visitVarInsn(ALOAD, 0);
     *             mv.visitLdcInsn("onItemClick");
     *             mv.visitMethodInsn(INVOKESTATIC, "com/example/viewcompiler/ViewProcessor", "addOnItemClick", "(Landroid/view/ViewGroup;Ljava/lang/Object;Ljava/lang/String;)V", false);
     * @param methodVisitor
     * @return
     */
    public MethodVisitor createMethodVisitor(MethodVisitor methodVisitor){
        MethodVisitor method = new MethodVisitor(Opcodes.ASM4,methodVisitor){
            @Override
            public void visitInsn(int opcode) {
                if(canInject){
                    if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
                        mv.visitVarInsn(Opcodes.ALOAD,2);
                        mv.visitVarInsn(Opcodes.ALOAD,0);
                        mv.visitLdcInsn(mCallbackMethodName);
                        mv.visitMethodInsn(INVOKESTATIC,getTargetInfo().getTargetType(),getTargetInfo().getTargetMethod(),getTargetInfo().getTargetMethodDesc(),false);
                    }
                }
                super.visitInsn(opcode);
            }

            @Override
            public void visitMaxs(int maxStack, int maxLocals) {
                super.visitMaxs(Math.max(3, maxStack), maxLocals);
            }
        };
        return method;
    }
}
