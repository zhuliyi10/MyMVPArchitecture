package com.zhuliyi.commonlib.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.zhuliyi.commonlib.base.delegate.App;
import com.zhuliyi.commonlib.base.delegate.AppDelegate;
import com.zhuliyi.commonlib.base.delegate.AppLifecycle;
import com.zhuliyi.commonlib.di.component.AppComponent;

/**
 * Describe : 本框架由 MVP + Dagger2 + Retrofit + RxJava + Androideventbus + Butterknife 组成
 * 这个基类不是必须要继承，如果是继承其他Application，要把实现代理的方法写到对应的Application
 * Author : zhuly
 * Date : 2018-06-12
 */

public class BaseApplication extends Application implements App{
    private AppLifecycle appLifecycle;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if(appLifecycle==null){
            appLifecycle=new AppDelegate(base);
        }
        appLifecycle.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if(appLifecycle!=null){
            appLifecycle.onCreate(this);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if(appLifecycle!=null){
            appLifecycle.onCreate(this);
        }
    }


    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return ((App)appLifecycle).getAppComponent();
    }
}
