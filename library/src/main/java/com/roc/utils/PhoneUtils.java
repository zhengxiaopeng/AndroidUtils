package com.roc.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;

import com.roc.app.MainApplication;

/**
 * Created by Rocko on 2014/12/19 0019.
 */
public class PhoneUtils {
    /**
     * 获取设备唯一的id标识.
     *
     * @param context
     * @return
     */
    public static String getDeviceId() {
        return ((TelephonyManager) MainApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     */
    public static void hideSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) context).getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
