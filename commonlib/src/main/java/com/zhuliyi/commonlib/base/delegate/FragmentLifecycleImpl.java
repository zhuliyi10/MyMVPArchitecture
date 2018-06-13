package com.zhuliyi.commonlib.base.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import org.simple.eventbus.EventBus;

/**
 * Describe : 通用Fragment生命周期的回调，在这里实现通用Fragment生命周期的方法
 * Author : zhuly
 * Date : 2018-06-13
 */

public class FragmentLifecycleImpl extends FragmentManager.FragmentLifecycleCallbacks{
    private static final String TAG=FragmentLifecycleImpl.class.getSimpleName();

    private IFragment iFragment;
    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        Log.d(TAG, "onFragmentAttached: ");
    }

    @Override
    public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        Log.d(TAG, "onFragmentPreCreated: ");
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        Log.d(TAG, "onFragmentCreated: ");
        if(f instanceof  IFragment){
            iFragment= (IFragment) f;
            if(iFragment.useEventBus()){
                //注册到事件主线
                EventBus.getDefault().register(f);
            }
        }
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        Log.d(TAG, "onFragmentActivityCreated: ");
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        Log.d(TAG, "onFragmentViewCreated: ");
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentStarted: ");
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentResumed: ");
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentPaused: ");
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentStopped: ");
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        Log.d(TAG, "onFragmentSaveInstanceState: ");
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentViewDestroyed: ");
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentDestroyed: ");
        if(iFragment!=null&&iFragment.useEventBus()){
            //取消注册
            EventBus.getDefault().unregister(f);
        }
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        Log.d(TAG, "onFragmentDetached: ");
    }
}
