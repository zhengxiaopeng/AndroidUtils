package com.roc.http;

import android.content.Context;

import com.roc.common.DebugLog;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * @author Mr.Zheng
 * @date 2014年7月13日12:06:00
 */
public class HttpUtil {
    /**
     * ****************************** 网络连接部分 ********************************
     */
    public static String postByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.PostFromWebByHttpURLConnection(strUrl, nameValuePairs);
    }

    public static String getByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
        return CustomHttpURLConnection.GetFromWebByHttpUrlConnection(strUrl, nameValuePairs);
    }

    public static String postByHttpClient(Context context, String strUrl, List<NameValuePair> nameValuePairs) {
        return CustomHttpClient.PostFromWebByHttpClient(context, strUrl, nameValuePairs);
    }

    public static String getByHttpClient(Context context, String strUrl, NameValuePair... nameValuePairs) throws Exception {
        return CustomHttpClient.getFromWebByHttpClient(context, strUrl, nameValuePairs);
    }

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
            DebugLog.e(e.getMessage());
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
            DebugLog.e(e.getMessage());
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
            DebugLog.e(e.getMessage());
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
