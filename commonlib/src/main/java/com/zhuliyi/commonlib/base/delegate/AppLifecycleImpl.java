package com.zhuliyi.commonlib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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

    }

    @Override
    public void onTerminate(@NonNull Application application) {
        Log.d(TAG, "onTerminate: ");
    }
}
