package com.zhuliyi.commonlib.utils;

import android.content.Context;

import com.zhuliyi.commonlib.base.delegate.App;
import com.zhuliyi.commonlib.di.component.AppComponent;
import com.zhuliyi.commonlib.image.ImageLoader;

/**
 * Describe : 应用工具
 * Author : zhuly
 * Date : 2018-06-12
 */

public class AppUtils {
    /**
     * 获取AppComponent
     * @param context
     * @return
     */
    public static AppComponent obtainAppComponent(Context context){
        return ((App)context.getApplicationContext()).getAppComponent();
    }

    /**
     * 加载图片
     * @param context
     * @return
     */
    public static ImageLoader obtainImageLoader(Context context){
        return obtainAppComponent(context).imageLoader();
    }
}
