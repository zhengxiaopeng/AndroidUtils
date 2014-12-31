package com.roc.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.roc.androidutils.R;

/**
 * 显示基本信息的Dialog
 *
 * @author Mr.Zheng
 * @date 2014年7月31日18:02:49
 */
public class SimpleDialog implements OnClickListener {
    private Dialog dialog = null;
    private Context mContext = null;
    private TextView txtViewTitle, txtViewMsg;
    private Button btnCancel, btnConfirm;
    private View contentView = null;
    private SimpleDialogConfirmListener l = null;

    /**
     * SimpleDialog确认按钮监听接口
     */
    public interface SimpleDialogConfirmListener {
        /**
         * SimpleDialog确认按钮监听回调方法
         */
        void confirm();
        // void cancel();
    }

    public SimpleDialog(Context context) {
        this.mContext = context;
        this.contentView = LayoutInflater.from(context).inflate(R.layout.dialog_simple, null);

        this.txtViewTitle = (TextView) contentView.findViewById(R.id.txtView_dialog_simple_title);
        this.txtViewMsg = (TextView) contentView.findViewById(R.id.txtView_dialog_simple_msg);
        this.btnCancel = (Button) contentView.findViewById(R.id.btn_dialog_simple_cancel);
        this.btnConfirm = (Button) contentView.findViewById(R.id.btn_dialog_simple_confirm);

        this.btnCancel.setOnClickListener(this);
        this.btnConfirm.setOnClickListener(this);
    }

    /**
     * 设置Dialog的标题
     *
     * @param title
     */
    public SimpleDialog setTitle(String title) {
        txtViewTitle.setText(title);
        return this;
    }

    /**
     * 设置Dialog的提示信息
     *
     * @param msg
     */
    public SimpleDialog setMsg(String msg) {
        txtViewMsg.setText(msg);
        return this;
    }

    /**
     * 显示Dialog
     *
     * @param canceledOnTouchOutside 点击外边缘是否可取消
     * @param cancelable             按返回键是否可取消
     */
    public void showDialog(boolean canceledOnTouchOutside, boolean cancelable) {
        if (dialog == null)
            dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setCancelable(cancelable);
        dialog.show();
        Window window = dialog.getWindow();
        // window.setGravity(Gravity.BOTTOM);
        window.setContentView(contentView);
    }

    /**
     * 取消dialog显示
     */
    public void dimissDialog() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    /**
     * 设置监听器
     *
     * @param l
     */
    public void setSimpleDialogConfirmListener(SimpleDialogConfirmListener l) {
        this.l = l;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_simple_cancel:
                break;
            case R.id.btn_dialog_simple_confirm:
                if (l != null) {
                    l.confirm();
                }
                break;
            default:
                break;
        }
        dimissDialog();
    }
}
