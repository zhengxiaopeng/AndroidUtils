package com.rocko.android.common.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rocko.android.common.R;

/**
 * Created by Rocko on 2015/2/17 22:07
 * 显示加载中的dialog
 */
public class LoadingDialog extends AlertDialog {
    private View mContentView;
    private TextView mMsg;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public LoadingDialog setMsg(String msg) {
        mMsg.setText(msg);
        return this;
    }

    public LoadingDialog setMsg(int msgId) {
        mMsg.setText(msgId);
        return this;
    }

    protected void init(Context context, String msg) {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        mContentView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        mMsg = (TextView) mMsg.findViewById(R.id.txtView_dialog_show_msg);
    }

    public static LoadingDialog create(Context context) {
      return   new LoadingDialog(context);
    }

    @Override
    public void show() {
        super.show();
        getWindow().setContentView(mContentView);
    }

}
