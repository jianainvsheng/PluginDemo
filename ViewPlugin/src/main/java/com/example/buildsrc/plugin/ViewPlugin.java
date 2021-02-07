package com.example.buildsrc.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.LibraryExtension;
import com.example.buildsrc.plugin.extension.ViewExtension;
import com.example.buildsrc.plugin.transform.ViewTransform;
import com.source.plugin.kernel.plugin.BasePlugin;

import org.gradle.api.Project;

public class ViewPlugin extends BasePlugin<ViewExtension, ViewTransform> {

    @Override
    protected void attachAppProject(Project project, AppExtension appExtension, ViewTransform viewTransform, ViewExtension viewExtension) {

    }

    @Override
    protected void attachLibProject(Project project, LibraryExtension libraryExtension, ViewTransform viewTransform, ViewExtension viewExtension) {
        libraryExtension.registerTransform(viewTransform);
    }

    @Override
    protected void attachGraphPopulated(BasePlugin.ProjectType projectType, Project project, ViewExtension viewExtension, ViewTransform viewTransform) {

    }

    @Override
    protected String onExtensionName() {
        return "viewExt";
    }
}
