package com.zhuliyi.commonlib.base.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.zhuliyi.commonlib.base.BaseFragment;
import com.zhuliyi.commonlib.di.component.AppComponent;
import com.zhuliyi.commonlib.mvp.IPresenter;


/**
 * Despcription : 框架要求框架中的每个 {@link Activity} 都需要实现此类,以满足规范
 * Author : Leory
 * Time : 2018-04-15
 */
public interface IActivity {




    /**
     * 初始化 View, 如果 {@link #initView(Bundle)} 返回 0, 框架则不会调用 {@link Activity#setContentView(int)}
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);


    /**
     * 是否使用EventBus
     * @return
     */
    boolean useEventBus();

    /**
     * 这个 Activity 是否会使用 Fragment,框架会根据这个属性判断是否注册 {@link FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回{@code false},那意味着这个 Activity 不需要绑定 Fragment,那你再在这个 Activity 中绑定继承于 {@link BaseFragment} 的 Fragment 将不起任何作用
     *
     * @return
     */
    boolean useFragment();
}
