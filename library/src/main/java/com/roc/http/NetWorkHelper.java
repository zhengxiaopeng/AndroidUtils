package com.roc.http;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.roc.common.DebugLog;

import java.util.List;

/**
 * 2014年7月13日12:06:14
 *
 * @author Mr.Zheng
 */
public class NetWorkHelper {
    public static Uri uri = Uri.parse("content://telephony/carriers");

    /**
     * 判断是否有网络连接
     *
     * @param context ApplicationContext
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            DebugLog.v("无法获得网络连接");
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].isAvailable()) {
                        DebugLog.v("网络可用");
                        return true;
                    }
                }
            }
        }
        DebugLog.v("网络不可用");
        return false;
    }

    public static boolean checkNetState(Context context) {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    /**
     * 判断网络是否为漫游
     *
     * @param context ApplicationContext
     */
    public static boolean isNetworkRoaming(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            DebugLog.w("无法得到 connectivity manager");
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(
                        Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    DebugLog.v("漫游状态");
                    return true;
                } else {
                    DebugLog.v("非漫游状态");
                }
            } else {
                DebugLog.v("没有使用手机网络");
            }
        }
        return false;
    }

    /**
     * 判断手机网络是否可用
     *
     * @param context ApplicationContext
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;

        isMobileDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();

        return isMobileDataEnable;
    }

    /**
     * 判断wifi 是否可用
     *
     * @param context ApplicationContext
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) throws Exception {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 设置手机网络开关
     *
     * @param context ApplicationContext
     * @param enabled
     * @throws Exception
     */
    public static void setMobileDataEnabled(Context context, boolean enabled) throws Exception {
        APNManager apnManager = APNManager.getInstance(context);
        List<APN> list = apnManager.getAPNList();
        if (enabled) {
            for (APN apn : list) {
                ContentValues cv = new ContentValues();
                cv.put("apn", apnManager.matchAPN(apn.apn));
                cv.put("type", apnManager.matchAPN(apn.type));
                context.getContentResolver()
                        .update(uri, cv, "_id=?", new String[]{apn.apnId});
            }
        } else {
            for (APN apn : list) {
                ContentValues cv = new ContentValues();
                cv.put("apn", apnManager.matchAPN(apn.apn) + "mdev");
                cv.put("type", apnManager.matchAPN(apn.type) + "mdev");
                context.getContentResolver()
                        .update(uri, cv, "_id=?", new String[]{apn.apnId});
            }
        }
    }

}
