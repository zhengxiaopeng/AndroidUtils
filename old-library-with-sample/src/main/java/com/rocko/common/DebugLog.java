package com.rocko.common;

import android.util.Log;

import com.rocko.androidutils.BuildConfig;

/**
 * 调试信息输出类
 *
 * @author Mr.Zheng
 * @data 2014年7月9日16:52:24
 */
public class DebugLog {
    /**
     * Log输出所在类
     */
    private static String className;
    /**
     * Log输出所在方法
     */
    private static String methodName;
    /**
     * Log输出所行号
     */
    private static int lineNumber;

    private DebugLog() {
    }

    /**
     * 是否可Debug状态
     *
     * @return
     */
    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    /**
     * 创建Log输出的基本信息
     *
     * @param log
     * @return
     */
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append("()");
        buffer.append(" line:");
        buffer.append(lineNumber);
        buffer.append("] ");
        buffer.append(log);
        return buffer.toString();
    }

    /**
     * 取得输出所在位置的信息 className methodName lineNumber
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName().split("\\.")[0];
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }

}