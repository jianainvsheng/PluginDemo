package com.example.buildsrc.plugin.extension;
import com.source.plugin.kernel.extension.BaseExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2020/2/29.
 */

public class ViewExtension extends BaseExtension {

    private List<String> viewPaths = new ArrayList<>();

    public void viewPath(String... paths){

        if(paths == null || paths.length <= 0){
            return;
        }
        for(String s : paths){
            if(!isEmpty(s)){
                changeSeparator(s);
                this.viewPaths.add(s);
            }
        }
    }

    private boolean isEmpty(String path){

        return path == null || "".equals(path);
    }

    public List<String> getViewPathList(){

        return viewPaths;
    }

    private void changeSeparator(String path){

        if(path != null && !"".equals(path)){

            path.replace("/", File.separator);
        }
    }
}
