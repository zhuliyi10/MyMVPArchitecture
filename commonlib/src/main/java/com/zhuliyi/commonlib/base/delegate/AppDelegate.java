package com.zhuliyi.commonlib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.zhuliyi.commonlib.di.component.AppComponent;
import com.zhuliyi.commonlib.di.component.DaggerAppComponent;
import com.zhuliyi.commonlib.di.module.GlobalConfigModule;
import com.zhuliyi.commonlib.app.ConfigModule;
import com.zhuliyi.commonlib.app.ManifestParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe : Application代理
 * Author : zhuly
 * Date : 2018-06-12
 */

public class AppDelegate implements AppLifecycle,App{

    private AppComponent appComponent;
    private static Application application;
    private List<ConfigModule> modules;
    private List<AppLifecycle> appLifecycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks>activityLifecycles=new ArrayList<>();
    public AppDelegate(@NonNull Context context){
        //用反射, 将 AndroidManifest.xml 中带有 ConfigModule 标签的 class 转成对象集合（List<ConfigModule>）
        modules=new ManifestParser(context).parseManifest();
        for (ConfigModule module:modules){
            //将框架外部, 开发者实现的 Application 的生命周期回调 (AppLifecycles) 存入 mAppLifecycles 集合 (此时还未注册回调)
            module.injectAppLifecycle(context,appLifecycles);
            //将框架外部, 开发者实现的 Activity 的生命周期回调 (ActivityLifecycleCallbacks) 存入 mActivityLifecycles 集合 (此时还未注册回调)
            module.injectActivityLifecycle(context,activityLifecycles);
        }
    }
    @Override
    public void attachBaseContext(@NonNull Context base) {
        //遍历 mAppLifecycles, 执行所有已注册的 AppLifecycles 的 attachBaseContext() 方法 (框架外部, 开发者扩展的逻辑)
       for (AppLifecycle lifecycle:appLifecycles){
           lifecycle.attachBaseContext(base);
       }
    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.application=application;
        appComponent= DaggerAppComponent
                .builder()
                .application(application)
                .globalConfigModule(getGlobalConfigModule(application,modules))
                .build();
        appComponent.inject(this);
        //执行框架外部, 开发者扩展的 App onCreate 逻辑
        for (AppLifecycle lifecycle:appLifecycles){
            lifecycle.onCreate(application);
        }

        for (Application.ActivityLifecycleCallbacks lifecycle:activityLifecycles){
            application.registerActivityLifecycleCallbacks(lifecycle);
        }
    }

    @Override
    public void onTerminate(@NonNull Application application) {
        application=null;
        if(appLifecycles!=null){
            for (AppLifecycle lifecycle:appLifecycles){
                lifecycle.onTerminate(application);
            }
        }
        appLifecycles=null;

    }

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     * 需要在AndroidManifest中声明{@link ConfigModule}的实现类,和Glide的配置方式相似
     *
     * @return GlobalConfigModule
     */
    private GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> modules) {

        GlobalConfigModule.Builder builder = GlobalConfigModule
                .builder();

        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }

        return builder.build();
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 获取application
     * @return
     */
    public static Application getApplication(){
        return application;
    }
}
