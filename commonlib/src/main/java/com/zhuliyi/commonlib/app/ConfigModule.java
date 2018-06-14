package com.zhuliyi.commonlib.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.zhuliyi.commonlib.base.delegate.AppLifecycle;
import com.zhuliyi.commonlib.di.module.GlobalConfigModule;

import java.util.List;

/**
 * Describe : Module的参数配置，在AndroidManifest中声明该实现类
 * Author : zhuly
 * Date : 2018-06-12
 */

public interface ConfigModule {
    /**
     * 给框架配置一些参数
     * @param context
     */
    void applyOptions(Context context,GlobalConfigModule.Builder builder);
    /**
     *  使用{@link AppLifecycle}在Application的生命周期中注入一些操作
     * @param context
     * @param lifecycles
     */
    void injectAppLifecycle(Context context, List<AppLifecycle>lifecycles);

    /**
     * 使用{@link Application.ActivityLifecycleCallbacks}在Application的生命周期中注入一些操作
     *
     * @param context
     * @param lifecycles
     */
    void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks>lifecycles);
}
