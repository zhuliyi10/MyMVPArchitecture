package com.zhuliyi.commonlib.utils;

import android.widget.Toast;

/**
 * Describe : toast工具
 * Author : zhuly
 * Date : 2018-06-15
 */

public class ToastUtils {
    private static Toast toast = null;

    public static void showShort(CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(AppUtils.getApplication(), charSequence, Toast.LENGTH_SHORT);
        } else {
            toast.setText(charSequence);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showShort(int resId) {
        String text = AppUtils.getApplication().getResources().getString(resId);
        showShort(text);
    }

    public static void showLong(CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(AppUtils.getApplication(), charSequence, Toast.LENGTH_LONG);
        } else {
            toast.setText(charSequence);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    public static void showLong(int resId) {
        String text = AppUtils.getApplication().getResources().getString(resId);
        showLong(text);
    }
}
