package com.example.plugindemo.application;

import android.app.Application;

import com.source.crouter.CRouter;

import androidx.multidex.MultiDexApplication;

public
class PluginApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CRouter.getIntance().init();
    }
}
