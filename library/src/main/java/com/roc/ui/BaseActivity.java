package com.roc.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.roc.annotation.ViewInstaller;
import com.roc.app.AppManager;
import com.roc.content.SharedPreferencesManager;
import com.roc.utils.CommonUtils;

/**
 * 应用程序Activity的基类
 * 
 * @author Mr.Zheng
 * @date 2014年8月17日20:50:32
 * @bug 在继承FragmengActivity时会有bug，无法使用
 */
public abstract class BaseActivity extends Activity {
	private AppManager appManager = null;
	private boolean isExit = false; // 双击返回键退出标记

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.v(this.getClass().getSimpleName(), ">>>onCreate");
		if (appManager == null)
			appManager = AppManager.getAppManager();
		appManager.pushActivity(this);
		ViewInstaller.processAnnotation(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v(this.getClass().getSimpleName(), ">>>onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(this.getClass().getSimpleName(), ">>>onResume");
	}

	@Override
	protected void onStop() {
		super.onResume();
		Log.v(this.getClass().getSimpleName(), ">>>onStop");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v(this.getClass().getSimpleName(), ">>>onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v(this.getClass().getSimpleName(), ">>>onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(this.getClass().getSimpleName(), ">>>onDestroy");
		appManager.finishActivity(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			/* 返回键处理，当栈中剩1时即主界面了，双击退出 */
			if (appManager.getActivityStack().size() > 1)
				break;
			if (!isExit) {
				isExit = true;
				CommonUtils.showShortToast(this, "再按一次退出程序");
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					@Override
					public void run() {
						isExit = false;
					}
				}, 2000);
			} else
				appManager.AppExit();
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public Activity getActivity() {
		return this;
	}

	public Context getContext() {
		return this;
	}

	/**
	 * 不想每次findViewById都需要强转......
	 * 
	 * @param id
	 * @return
	 */
	public <T extends View> T findView(int id) {
		return (T) findViewById(id);
	}

	/**
	 * 得到sp的管理器,模式为Context.MODE_PRIVATE
	 * 
	 * @param spFileName
	 *            sp文件名
	 * @return 返回一个新的sp管理器实例,此时sp管理的是spFileName对应的SharedPreferences文件
	 */
	public SharedPreferencesManager getSharedPreferencesManager(String spFileName) {
		return getSharedPreferencesManager(spFileName, Context.MODE_PRIVATE);
	}

	/**
	 * 得到sp的管理器
	 * 
	 * @param spFileName
	 *            sp文件名
	 * @param mode
	 *            读写模式
	 * @return 返回一个新的sp管理器实例，此时sp管理的是spFileName对应的SharedPreferences文件
	 */
	public SharedPreferencesManager getSharedPreferencesManager(String spFileName, int mode) {
		return new SharedPreferencesManager(spFileName, mode);
	}

}