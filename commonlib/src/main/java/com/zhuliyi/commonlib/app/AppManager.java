package com.zhuliyi.commonlib.app;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Describe : 用于管理所有 {@link Activity},和在前台的 {@link Activity}
 * Author : zhuly
 * Date : 2018-06-13
 */

@Singleton
public class AppManager {
    //管理所有存活的 Activity, 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
    private List<Activity> activityList;
    @Inject
    public AppManager(){
    }

    /**
     * 添加 {@link Activity} 到集合
     */
    public void addActivity(Activity activity) {
        synchronized (AppManager.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
        }
    }
    /**
     * 删除集合里的指定的 {@link Activity} 实例
     *
     * @param {@link Activity}
     */
    public void removeActivity(Activity activity) {
        if (activityList == null) {
            return;
        }
        synchronized (AppManager.class) {
            if (activityList.contains(activity)) {
                activityList.remove(activity);
            }
        }
    }
    /**
     * 返回一个存储所有未销毁的 {@link Activity} 的集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        if (activityList == null) {
            activityList = new LinkedList<>();
        }
        return activityList;
    }
}
