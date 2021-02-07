package com.example.buildsrc.plugin.processor.annotaion;
import com.example.annotation.type.IViewType;
import com.example.buildsrc.plugin.processor.InjectProcessor;
import com.example.buildsrc.plugin.processor.base.BaseAnnotation;
import com.example.buildsrc.plugin.processor.target.TargetType;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.util.ArrayList;
import java.util.List;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ViewTypeAnnotation extends BaseAnnotation<IViewType, InjectProcessor> {

    private List<String> mClassInjectNames = new ArrayList<>();

    public ViewTypeAnnotation(InjectProcessor processor) {
        super(processor);
    }

    public boolean containsClassName(String className){
        return mClassInjectNames.contains(className);
    }

    @Override
    public void analysisAnnotation(String className, String methodName, String methodDesc) {
        addClassName(className);
        TargetType type = getTargetType();
        if(type != null){
            super.analysisAnnotation(className, type.getVisitMethod(), type.getVisitMethodDes());
        }
    }

    private void addClassName(String className){
        if(!containsClassName(className)){
            mClassInjectNames.add(className);
        }
    }
    /**
     *  mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
     *             mv.visitVarInsn(ALOAD, 2);
     *             mv.visitMethodInsn(INVOKESTATIC, "com/example/viewcompiler/ViewProcessor", "onCreateViewInfo", "(Landroid/view/ViewGroup;)V", false);
     */
    public MethodVisitor createMethodVisitor(MethodVisitor methodVisitor){
        MethodVisitor method = new MethodVisitor(Opcodes.ASM4,methodVisitor){
            @Override
            public void visitInsn(int opcode) {
                //此方法可以获取方法中每一条指令的操作类型，被访问多次
                //如应在方法结尾处添加新指令，则应判断：
                if (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) {
                    mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
                    mv.visitVarInsn(Opcodes.ALOAD,2);
                    mv.visitMethodInsn(INVOKESTATIC,getTargetInfo().getTargetType(),getTargetInfo().getTargetMethod(),getTargetInfo().getTargetMethodDesc(),false);
                }
                super.visitInsn(opcode);
            }

            @Override
            public void visitMaxs(int maxStack, int maxLocals) {
                super.visitMaxs(Math.max(1, maxStack), maxLocals);
            }
        };
        return method;
    }

}
