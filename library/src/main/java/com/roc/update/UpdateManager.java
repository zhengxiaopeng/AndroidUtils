package com.roc.update;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.roc.androidutils.R;
import com.roc.common.DebugLog;
import com.roc.http.HttpUtil;
import com.roc.utils.ApplicationUtils;
import com.roc.utils.CommonUtils;
import com.roc.utils.DialogUtils;
import com.roc.widget.dialog.SimpleDialog;
import com.roc.widget.dialog.SimpleDialog.SimpleDialogConfirmListener;

/**
 * 更新管理类
 * 
 * @author Mr.Zheng
 * @date 2014年7月9日11:09:55
 */
public class UpdateManager {
	private static Context mContext;
	private static UpdateInfo mUpdateInfo = null;
	/* Handler标识 */
	public static final int NOT_NEED_UPDATE = 0;
	public static final int IS_NEED_UPDATE = 1;
	/* 需要更新 */
	private static SimpleDialog needUpdateDialog = null;
	private static UpdateHandler handler = null;
	private static boolean isShowProgress = true;

	/**
	 * 选择是否更新Dialog
	 */
	private static void setupNeedUpdateDialog(final UpdateInfo info) {
		needUpdateDialog = DialogUtils.createSimpleDialog(mContext, new MySimpleDialogConfirmListener());
		needUpdateDialog.setTitle("发现更新，是否更新？");
		// 服务器端规定"/"符号分行
		try {
			String[] msg = info.getDescription().split("/");
			StringBuilder builder = new StringBuilder();
			for (String str : msg) {
				builder.append(str + "\n");
			}
			needUpdateDialog.setMsg(builder + "");
		} catch (Exception e) {
			DebugLog.e(e.getMessage() + "\n更新描述为空");
			e.printStackTrace();
		}
		needUpdateDialog.showDialog(false, false);
	}

	/**
	 * 检查更新的入口开始方法
	 * 
	 * @param context
	 * @param isShowProgress
	 *            是否显示进度条和检查失败后Toast提示
	 */
	public static void beginUpdate(Context context, boolean isShowProgress) {
		UpdateManager.mContext = context;
		UpdateManager.isShowProgress = isShowProgress;
		handler = new UpdateHandler();
		mUpdateInfo = null;
		if (isShowProgress) {
			/* 显示进度条 */
			DialogUtils.stratProgressDialog(context, "获取更新信息中···", false, false);
		}
		new Thread(new UpdateThread()).start();
	}

	/**
	 * 判断是否需要更新
	 * 
	 * @return
	 */
	private static boolean isNeedUpdate(Context context) {
		UpdateInfo info = getUpdateInfo(context);
		String currentVersion = ApplicationUtils.getAppVersionName();
		DebugLog.v("当前程序版本：" + currentVersion);
		try {
			String v = info.getVersion();
			DebugLog.v("更新程序版本：" + v);
			if (v.equals(currentVersion)) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取更新信息
	 * 
	 * @param context
	 * @return
	 */
	private static UpdateInfo getUpdateInfo(Context context) {
		UpdateInfoService updateInfoService = new UpdateInfoService();
		UpdateInfo info = null;
		try {
			info = updateInfoService.getUpdateInfo();
		} catch (Exception e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		mUpdateInfo = info;
		return info;
	}

	/**
	 * 检查更新线程
	 * 
	 * @author Mr.Zheng
	 * 
	 */
	private static class UpdateThread implements Runnable {
		@Override
		public void run() {
			if (isNeedUpdate(UpdateManager.mContext)) {
				UpdateInfo info = UpdateManager.getUpdateInfo(mContext);
				Message msg = Message.obtain();
				msg.obj = info;
				msg.what = IS_NEED_UPDATE;
				handler.sendMessage(msg);
			} else {
				handler.sendEmptyMessage(NOT_NEED_UPDATE);
			}
		}

	}

	/**
	 * 更新Handler
	 * 
	 * @author Mr.Zheng
	 * 
	 */
	private static class UpdateHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 需要更新时
			case IS_NEED_UPDATE:
				if (isShowProgress) {
					DialogUtils.stopProgressDialog();
				}
				DebugLog.v("需要更新");
				final UpdateInfo info = (UpdateInfo) msg.obj;
				setupNeedUpdateDialog(info);
				break;
			// 不需要更新时
			case NOT_NEED_UPDATE:
				if (isShowProgress) {
					// 取消进度条显示
					DialogUtils.stopProgressDialog();
					DebugLog.v("已是最新版！");
					Toast.makeText(mContext, "已是最新版，无需更新！", 1).show();
				}
				UpdateManager.clearQuote();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 按钮点击监听
	 * 
	 * @author Mr.Zheng
	 * 
	 */
	private static class MySimpleDialogConfirmListener implements SimpleDialogConfirmListener {
		@Override
		public void confirm() {
			if (!HttpUtil.isWifiDataEnable() && !HttpUtil.isMobileDataEnable()) {
				Toast.makeText(mContext, mContext.getResources().getString(R.string.no_network), 1).show();
				return;
			}
			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				Toast.makeText(mContext, mContext.getResources().getString(R.string.no_sdcrad), 1).show();
				return;
			}
			// 开启服务下载
			Intent intent = new Intent();
			intent.setAction(UpdateService.INTENT_FILTER);
			mContext.startService(intent);
			// 取消dialog显示
			needUpdateDialog.dimissDialog();

		}

	}

	/**
	 * Clear
	 */
	public static void clearQuote() {
		mContext = null;
		mUpdateInfo = null;
		needUpdateDialog = null;
		handler = null;
		DebugLog.v("UpdateManager GC");
		System.gc();
	}
}
