package com.zhuliyi.commonlib.utils;

import android.util.Log;

import com.zhulilyi.commonlib.BuildConfig;

/**
 * Describe : 自定义log工具
 * Author : zhuly
 * Date : 2018-06-15
 */

public class LogUtils {
    /**
     * 是否开户log 在build.gradle 中定义
     */
    public static boolean logDebug = BuildConfig.LOG_DEBUG;

    public static void e(String tag, String message) {
        if (logDebug) {
            Log.e(tag, message);
        }
    }

    public static void e(String message) {
        e("error", message);

    }

    public static void w(String tag, String message) {
        if (logDebug) {
            Log.w(tag, message);
        }
    }

    public static void w(String message) {
        w("warn", message);
    }

    public static void i(String tag, String message) {
        if (logDebug) {
            Log.i(tag, message);
        }
    }

    public static void i(String message) {
        i("info", message);
    }

    public static void d(String tag, String message) {
        if (logDebug) {
            Log.d(tag, message);
        }
    }

    public static void d(String message) {
        d("debug", message);
    }

    public static void v(String tag, String message) {
        if (logDebug) {
            Log.v(tag, message);
        }
    }

    public static void v(String message) {
        if (logDebug) {
            v("verbose", message);
        }
    }
}
