package com.roc.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.roc.androidutils.R;
import com.roc.widget.dialog.SimpleDialog;
import com.roc.widget.dialog.SimpleDialog.SimpleDialogConfirmListener;

import java.lang.reflect.Field;

/**
 * 基本Dialog的工具
 *
 * @author Mr.Zheng
 * @date 2014年7月30日11:16:53
 */
public class DialogUtils {
    private static Dialog mDialog = null;
    private static View mDialogView = null;
    private static SimpleDialog mSimpleDialog = null;

    /**
     * 开启全屏无进度的progress
     *
     * @param context                Activity的上下文，不可为ApplicationContext
     * @param msg                    显示的提示信息
     * @param cancelable             返回键是否可取消
     * @param canceledOnTouchOutside 点击边缘是否可取消
     */
    public static void stratProgressDialog(Context context, String msg, boolean cancelable,
                                           boolean canceledOnTouchOutside) {
        mDialog = new AlertDialog.Builder(context).create();
        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        mDialog.show();
        mDialog.getWindow().setContentView(getDialogView(context, msg));
    }

    private static View getDialogView(Context context, String msg) {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        ((TextView) mDialogView.findViewById(R.id.txtView_dialog_show_msg)).setText(msg);
        return mDialogView;
    }

    /**
     * 停止显示全屏无进度的progress 确保在主（UI）线程执行
     */
    public static void stopProgressDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
            // mDialog = null;
            // mDialogView.clearAnimation();
            // mDialogView = null;
        }

    }

    /**
     * 创建简单提示的对话框
     *
     * @param context 上下文
     * @param l       dialog确认按钮的监听器
     * @return SimpleDialog
     */
    public static SimpleDialog createSimpleDialog(Context context, SimpleDialogConfirmListener l) {
        mSimpleDialog = new SimpleDialog(context);
        mSimpleDialog.setSimpleDialogConfirmListener(l);
        return mSimpleDialog;
    }

    /**
     * 通过反射区设置默认的AlertDialog在点击确定时是否自动关闭dialog
     *
     * @param pDialog
     * @param pIsClose
     */
    public static void setAlertDialogIsClose(DialogInterface pDialog, Boolean pIsClose) {
        try {
            Field field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(pDialog, pIsClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
