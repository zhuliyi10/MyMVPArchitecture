package com.zhuliyi.commonlib.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe : 用于解析 AndroidManifest 中的 Meta 属性
 * 配合 {@link ConfigModule} 使用
 * Author : zhuly
 * Date : 2018-06-12
 */

public class ManifestParser {
    private static final String MODULE_VALUE = "ConfigModule";

    private Context context;
    public ManifestParser(Context context){
        this.context=context;
    }
    public List<ConfigModule> parseManifest(){
        List<ConfigModule>modules=new ArrayList<>();
        try {
            ApplicationInfo appInfo=context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if(appInfo.metaData!=null){
                for (String key:appInfo.metaData.keySet()){
                    if(MODULE_VALUE.equals(appInfo.metaData.get(key))){
                        modules.add(parseModule(key));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse ConfigModule", e);
        }
        return modules;
    }

    /**
     * 通过类名反射实例
     * @param className
     * @return
     */
    private static ConfigModule parseModule(String className){
        Class<?>clazz;
        try {
            clazz =Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to find class "+className,e);
        }
        ConfigModule module;
        try {
            module= (ConfigModule) clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to instantiate ConfigModule implementation for " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate ConfigModule implementation for " + clazz, e);
        }
        return module;
    }
}
