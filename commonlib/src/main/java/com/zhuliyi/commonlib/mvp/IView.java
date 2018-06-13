/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhuliyi.commonlib.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Despcription : 这里可以定义一些比较常用,每个 {@link View} 都会用到的方法
 *  因为 {@link View} 的实现类可能会是 {@link Activity}, {@link Fragment} 或者 {@link Dialog} 以及一些自定义 {@link View}
 *  所以不能定义一些某个类特有的方法比如 {@link Activity#startActivity(Intent)} 就是 {@link Activity} 特有的,其他 {@link View} 实现类并不一定具备这个功能
 * Author : Leory
 * Time : 2018-04-15
 */
public interface IView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    void showMessage(@NonNull String message);

}
