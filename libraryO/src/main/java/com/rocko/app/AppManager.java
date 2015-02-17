package com.rocko.app;

import android.app.Activity;

import com.rocko.ui.BaseActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author Mr.Zheng
 * @date 2014年8月17日20:41:16
 */
public class AppManager {
    private static Stack<BaseActivity> activityStack;
    private final static AppManager appManager = new AppManager();

    private AppManager() {
    }

    public static AppManager getAppManager() {
        return appManager;
    }

    public Stack<BaseActivity> getActivityStack() {
        return activityStack;
    }

    /**
     * push Activity到栈
     */
    public void pushActivity(BaseActivity activity) {
        if (activityStack == null)
            activityStack = new Stack<BaseActivity>();
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public BaseActivity getTopActivity() {
        if (activityStack == null || activityStack.isEmpty())
            return null;
        return activityStack.lastElement();
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public BaseActivity findActivity(Class<?> cls) {
        BaseActivity activity = null;
        for (BaseActivity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity(Activity activity) {
        if (activityStack.remove(activity))
            activity.finish();
    }

    /**
     * 从栈顶开始结束所有Activity
     */
    public void finishAllActivity() {
        while (activityStack.size() > 0)
            activityStack.pop().finish();
        activityStack.clear();
    }

    /**
     * 应用程序退出
     */
    public void AppExit() {
        try {
            finishAllActivity();
            // ActivityManager activityMgr = (ActivityManager)
            // context.getSystemService(Context.ACTIVITY_SERVICE);
            // activityMgr.killBackgroundProcesses(context.getPackageName());
            // System.exit(0);
            // android.os.Process.killProcess(Process.myPid());
        } catch (Exception e) {
            // System.exit(0);
        }
    }
}