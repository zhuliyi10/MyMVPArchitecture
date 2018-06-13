package com.zhuliyi.commonlib.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Describe :用于代理 {@link Application} 的生命周期
 * Author : zhuly
 * Date : 2018-06-12
 */

public interface AppLifecycle {
    void attachBaseContext(@NonNull Context base);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
