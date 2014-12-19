package com.roc.app;

import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.roc.common.DebugLog;

/**
 * @author Mr.Zheng
 * @date 2014年9月15日 下午6:46:32
 */
public class MainApplication extends Application {
	private static MainApplication mainApplication;
	/**
	 * activity栈
	 */
	private Stack<Activity> activitiesStack;

	/**
	 * 获取全局Application Context
	 * 
	 */
	public static MainApplication getContext() {
		return mainApplication;
	}

	public MainApplication() {
		mainApplication = this;
	}

	@Override
	public void onCreate() {
		DebugLog.v("<<<");
		super.onCreate();
		activitiesStack = new Stack<Activity>();
	}

	@Override
	public void onTerminate() {
		DebugLog.v("<<<");
		super.onTerminate();
	}

	@Override
	public void onLowMemory() {
		DebugLog.w("low memory!!!");
		super.onLowMemory();
	}

	/**
	 * 判断当前版本是否兼容目标版本的方法
	 * 
	 * @param VersionCode
	 * @return
	 */
	public boolean isMethodsCompat(int VersionCode) {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}

	/**
	 * 把activity压进栈
	 * 
	 * @param activity
	 */
	public void pushActivity(Activity activity) {
		activitiesStack.push(activity);
	}

	/**
	 * activity出栈,在activity onDestroy时应调用
	 * 
	 * @param activity
	 */
	public void popActivity() {
		if (!activitiesStack.isEmpty())
			activitiesStack.pop();
	}

	/**
	 * 退出程序
	 */
	public void exitApp() {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		manager.killBackgroundProcesses(getPackageName());
		while (!activitiesStack.isEmpty())
			activitiesStack.pop().finish();
		DebugLog.v("exitApp finsh!");
	}

}
