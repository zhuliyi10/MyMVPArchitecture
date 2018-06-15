package com.zhuliyi.commonlib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhuliyi.commonlib.base.delegate.App;
import com.zhuliyi.commonlib.base.delegate.AppDelegate;
import com.zhuliyi.commonlib.di.component.AppComponent;
import com.zhuliyi.commonlib.image.ImageLoader;

/**
 * Describe : 应用工具
 * Author : zhuly
 * Date : 2018-06-12
 */

public class AppUtils {

    public static Application getApplication(){
        return AppDelegate.getApplication();
    }
    /**
     * 获取AppComponent
     * @return
     */
    public static AppComponent obtainAppComponent(){
        return ((App) getApplication()).getAppComponent();
    }

    /**
     * 加载图片
     * @return
     */
    public static ImageLoader obtainImageLoader(){
        return obtainAppComponent().imageLoader();
    }

    /**
     * 使用 {@link ARouter} 根据 {@code path} 跳转到对应的页面, 这个方法因为没有使用 {@link Activity}跳转
     * 所以 {@link ARouter} 会自动给 {@link android.content.Intent} 加上 Intent.FLAG_ACTIVITY_NEW_TASK
     * 如果不想自动加上这个 Flag 请使用 {@link ARouter#getInstance()#navigation(Context, String)} 并传入 {@link Activity}
     *
     * @param path {@code path}
     */
    public static void navigation(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 使用 {@link ARouter} 根据 {@code path} 跳转到对应的页面, 如果参数 {@code context} 传入的不是 {@link Activity}
     * {@link ARouter} 就会自动给 {@link android.content.Intent} 加上 Intent.FLAG_ACTIVITY_NEW_TASK
     * 如果不想自动加上这个 Flag 请使用 {@link Activity} 作为 {@code context} 传入
     *
     * @param context
     * @param path
     */
    public static void navigation(Context context, String path) {
        ARouter.getInstance().build(path).navigation(context);
    }
}
