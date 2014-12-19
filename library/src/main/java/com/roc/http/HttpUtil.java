package com.roc.http;

import java.util.List;

import org.apache.http.NameValuePair;

import com.roc.common.DebugLog;

/**
 * 
 * @author Mr.Zheng
 * @date 2014年7月13日12:06:00
 */
public class HttpUtil {
	/********************************* 网络连接部分 *********************************/
	public static String postByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
		return CustomHttpURLConnection.PostFromWebByHttpURLConnection(strUrl, nameValuePairs);
	}

	public static String getByHttpURLConnection(String strUrl, NameValuePair... nameValuePairs) {
		return CustomHttpURLConnection.GetFromWebByHttpUrlConnection(strUrl, nameValuePairs);
	}

	public static String postByHttpClient(String strUrl, List<NameValuePair> nameValuePairs) {
		return CustomHttpClient.PostFromWebByHttpClient(strUrl, nameValuePairs);
	}

	public static String getByHttpClient(String strUrl, NameValuePair... nameValuePairs) throws Exception {
		return CustomHttpClient.getFromWebByHttpClient(strUrl, nameValuePairs);
	}

	/**
	 * 判断mobile网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileDataEnable() {
		try {
			return NetWorkHelper.isMobileDataEnable();
		} catch (Exception e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断wifi网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiDataEnable() {
		try {
			return NetWorkHelper.isWifiDataEnable();
		} catch (Exception e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkAvailable() {
		return NetWorkHelper.isNetworkAvailable();
	}

	/**
	 * 设置Mobile网络开关
	 * 
	 * @param context
	 * @param enabled
	 */
	public static void setMobileDataEnabled(boolean enabled) {
		try {
			NetWorkHelper.setMobileDataEnabled(enabled);
		} catch (Exception e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否为漫游
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkRoaming() {
		return NetWorkHelper.isNetworkRoaming();
	}
}
