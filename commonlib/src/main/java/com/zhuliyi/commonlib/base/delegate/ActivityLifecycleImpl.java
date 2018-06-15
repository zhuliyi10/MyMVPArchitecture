package com.zhuliyi.commonlib.base.delegate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.zhuliyi.commonlib.app.AppManager;
import com.zhuliyi.commonlib.utils.AppUtils;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Describe : 通用Activity生命周期的回调，包括第三方Activity，在这里实现通用Activity生命周期的方法
 * Author : zhuly
 * Date : 2018-06-12
 */

public class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = ActivityLifecycleImpl.class.getSimpleName();

    @Inject
    AppManager appManager;

    private IActivity iActivity;
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityCreated");
        AppUtils.obtainAppComponent().inject(this);
        appManager.addActivity(activity);
        if(activity instanceof  IActivity){
            iActivity= (IActivity) activity;
            if(iActivity.useEventBus()){
                //注册到事件主线
                EventBus.getDefault().register(activity);
            }
            if(iActivity.useFragment()&&iActivity instanceof FragmentActivity){
                ((FragmentActivity) iActivity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentLifecycleImpl(),true);
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, activity.getClass().getSimpleName() + ":onActivityDestroyed");
        appManager.removeActivity(activity);
        if(iActivity!=null&&iActivity.useEventBus()){
            //取消注册
            EventBus.getDefault().unregister(activity);
        }
        iActivity=null;
    }

}
