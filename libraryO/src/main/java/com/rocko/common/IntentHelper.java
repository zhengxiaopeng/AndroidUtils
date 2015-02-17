package com.rocko.common;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.rocko.androidutils.R;

/**
 * @author Mr.Zheng
 * @date 2014年9月15日 下午7:56:05
 */
public class IntentHelper {
    /**
     * 跳转activity
     *
     * @param context
     * @param cls     目标Activity
     */
    public static void startActivity(Context context, Class<? extends Activity> cls) {
        context.startActivity(new Intent(context, cls));
    }

    /**
     * 跳转activity
     *
     * @param context
     * @param intent  需要携带数据或设置的Intent
     * @param cls     目标Activity
     */
    public static void startActivity(Context context, Intent intent, Class<? extends Activity> cls) {
        intent.setComponent(new ComponentName(context, cls));
        context.startActivity(intent);
    }

    /**
     * 创建快捷图标
     *
     * @param context 需要创建快捷图标的Activity
     */
    public static void addShortcut(Context context) {
        final Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        final Parcelable icon = Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_launcher); // 获取快捷键的图标
        addIntent.putExtra("duplicate", false); // 设置快捷方式不能重复
        final Intent myIntent = new Intent(context, context.getClass());
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "");// 快捷方式的标题
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);// 快捷方式的图标
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);// 快捷方式的动作
        context.sendBroadcast(addIntent);
    }

    public static void startService(Context context, Class<? extends Service> cls) {
        context.startService(new Intent(context, cls));
    }

}
