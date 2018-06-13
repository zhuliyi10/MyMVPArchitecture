package com.zhuliyi.commonlib.base.delegate;

import android.support.annotation.NonNull;

import com.zhuliyi.commonlib.di.component.AppComponent;

/**
 * Describe : 框架要求框架中的每个 {@link android.app.Application} 都需要实现此类,以满足规范
 * Author : zhuly
 * Date : 2018-06-12
 */

public interface App {
    @NonNull
    AppComponent getAppComponent();
}
