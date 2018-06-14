package com.zhuliyi.commonlib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Describe : Application生命周期的回调，在这里实现Module通用的生命周期
 * Author : zhuly
 * Date : 2018-06-12
 */

public class AppLifecycleImpl implements AppLifecycle {
    private static final String TAG = AppLifecycleImpl.class.getSimpleName();

    @Override
    public void attachBaseContext(@NonNull Context base) {
        Log.d(TAG, "attachBaseContext: ");
    }

    @Override
    public void onCreate(@NonNull Application application) {
        Log.d(TAG, "onCreate: ");
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(application); // 尽可能早,推荐在Application中初始化

    }

    @Override
    public void onTerminate(@NonNull Application application) {
        Log.d(TAG, "onTerminate: ");
    }
}
