package com.example.buildsrc.plugin.transform;

import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.example.buildsrc.plugin.classvistor.ViewClassVisitor;
import com.example.buildsrc.plugin.extension.ViewExtension;
import com.example.buildsrc.plugin.processor.InjectProcessor;
import com.source.plugin.kernel.transform.BaseAnnotationTransform;

import org.gradle.api.Project;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by yangjian on 2020/2/29.
 */

public class ViewTransform extends BaseAnnotationTransform<ViewExtension> {


    private InjectProcessor mInjectProcessor;

    public ViewTransform(ViewExtension extentsion, Project project) {
        super(extentsion, project);
        mInjectProcessor = new InjectProcessor();
    }

    @Override
    public ClassWriter createAnnotationClassWriter(InputStream inputStream, String name, boolean isInject) {
        try {
            ClassReader classReader = new ClassReader(inputStream);
            ClassWriter classWriter = new ClassWriter(classReader, 0);
            ViewClassVisitor classVisitor = new ViewClassVisitor(Opcodes.ASM4, classWriter, isInject, getExtension(),mInjectProcessor);
            classReader.accept(classVisitor, 0);
            return classWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
    }

    @Override
    public boolean checkContainExtents(String name) {
        if (getExtension() != null && getExtension().getViewPathList() != null) {
            String separator = File.separator;
            List<String> viewPaths = getExtension().getViewPathList();
            for (String path : viewPaths) {
                if (!path.contains(separator)) {
                    name = name.replaceAll("\\\\", "/");
                }
                if (name.contains(path)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY;
    }
}
