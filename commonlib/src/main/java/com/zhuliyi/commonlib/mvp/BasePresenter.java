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
import android.app.Service;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.view.View;

import org.simple.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Despcription : 框架要求框架中的每个 Presenter 都需要实现此类,以满足规范
 * Author : Leory
 * Time : 2018-04-15
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable compositeDisposable;
    protected M model;

    protected V rootView;

    public BasePresenter() {
        this(null, null);
    }

    public BasePresenter(V rootView) {
        this(null, rootView);
    }

    public BasePresenter(M model, V rootView) {
        this.model = model;
        this.rootView = rootView;
        onStart();
    }


    @Override
    public void onStart() {
        if (useEventBus()) {//如果要使用 Eventbus 请将此方法返回 true
            EventBus.getDefault().register(this);//注册 Eventbus
        }
    }

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()}
     */
    @Override
    public void onDestroy() {
        if (useEventBus()) {//如果要使用 Eventbus 请将此方法返回 true
            EventBus.getDefault().unregister(this);//解除注册 Eventbus
        }
        unDispose();//解除订阅
        if (model != null)
            model.onDestroy();
        this.model = null;
        this.compositeDisposable = null;
    }

    /**
     * 只有当 {@code view} 实现了 {@link LifecycleOwner} 时, 此方法才会被调用
     * 所以当您想在 {@link Service} 以及一些自定义 {@link View} 或自定义类中使用 {@code Presenter} 时
     * 您也将不能继续使用 {@link OnLifecycleEvent} 绑定生命周期
     *
     * @param owner link {@link SupportActivity} and {@link Fragment}
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
    }

    /**
     * 是否使用 {@link EventBus},默认为使用(true)，
     *
     * @return
     */
    public boolean useEventBus() {
        return true;
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务,避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);//将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }

}
