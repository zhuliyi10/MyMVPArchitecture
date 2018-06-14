package com.zhuliyi.commonlib.base.delegate;

import android.app.Application;
import android.content.Context;

import com.zhuliyi.commonlib.di.module.GlobalConfigModule;
import com.zhuliyi.commonlib.http.Api;
import com.zhuliyi.commonlib.app.ConfigModule;

import java.util.List;

/**
 * Describe : {@link GlobalConfiguration} 含有有每个组件都可公用的配置信息, 每个组件的 AndroidManifest 都应该声明此 ConfigModule
 * Author : zhuly
 * Date : 2018-06-12
 */

public class GlobalConfiguration implements ConfigModule {


    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseUrl(Api.APP_DOMAIN)
                .build();
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycle> lifecycles) {
        lifecycles.add(new AppLifecycleImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActivityLifecycleImpl());
    }

}
