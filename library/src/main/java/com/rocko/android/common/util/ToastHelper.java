package com.rocko.android.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast显示工具
 *
 * @author Mr.Zheng
 * @date 2014年9月4日 下午10:41:17
 */
public class ToastHelper {
    /**
     * 显示一个短Toast
     *
     * @param context ApplicationContext
     * @param text
     */
    public static void showShortToast(Context context, String text) {
        initBaseToast(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个长Toast
     *
     * @param context ApplicationContext
     * @param text
     */
    public static void showLongToast(Context context, String text) {
        initBaseToast(context, text, Toast.LENGTH_LONG).show();
    }

    private static Toast initBaseToast(Context context, String text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        return toast;
    }

}
