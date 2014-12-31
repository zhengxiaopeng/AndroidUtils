package com.roc.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.roc.common.DebugLog;

import java.util.Stack;

/**
 * @author Mr.Zheng
 * @date 2014年9月15日 下午6:46:32
 * 注意：Application对象并不是始终在内存中的，它有可能会由于系统内存不足而被杀掉(例如Home键或任务切换退出此程序后内存不足，Application被杀)。但Android在你恢复这个应用时
 * 并不是重新开始启动这个应用，它会创建一个新的Application对象并且启动上次用户离开时的activity以造成这个app从
 * 来没有被kill掉得假象。
 */
public class MainApplication extends Application {
    private static MainApplication mainApplication;
    /**
     * activity栈
     */
    private Stack<Activity> activitiesStack;

    /**
     * 获取全局Application Context
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
