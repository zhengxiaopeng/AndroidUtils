package com.rocko.android.common.http;

import android.content.Context;

/**
 * @author Mr.Zheng
 * @date 2014年7月13日12:06:00
 */
public class HttpUtils {
    /**
     * 判断mobile网络是否可用
     *
     * @param context ApplicationContext
     * @return
     */
    public static boolean isMobileDataEnable(Context context) {
        try {
            return NetWorkHelper.isMobileDataEnable(context);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断wifi网络是否可用
     *
     * @param context ApplicationContext
     * @return
     */
    public static boolean isWifiDataEnable(Context context) {
        try {
            return NetWorkHelper.isWifiDataEnable(context);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否有网络连接
     *
     * @param context ApplicationContext
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        return NetWorkHelper.isNetworkAvailable(context);
    }

    /**
     * 设置Mobile网络开关
     *
     * @param context ApplicationContext
     * @param enabled
     */
    public static void setMobileDataEnabled(Context context, boolean enabled) {
        try {
            NetWorkHelper.setMobileDataEnabled(context, enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否为漫游
     *
     * @param context ApplicationContext
     * @return
     */
    public static boolean isNetworkRoaming(Context context) {
        return NetWorkHelper.isNetworkRoaming(context);
    }
}
