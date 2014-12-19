package com.roc.common;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roc.androidutils.R;
import com.roc.app.MainApplication;

/**
 * Toast显示工具
 * 
 * @author Mr.Zheng
 * @date 2014年9月4日 下午10:41:17
 */
public class ToastHelper {
	private final static int TOAST_CUSTOM_INFO = 0;
	private final static int TOAST_CUSTOM_ERROR = 1;

	/**
	 * 显示一个短Toast
	 * 
	 * @param context
	 * @param text
	 */
	public static void showShortToast(String text) {
		initBaseToast(text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 显示一个长Toast
	 * 
	 * @param context
	 * @param text
	 */
	public static void showLongToast(String text) {
		initBaseToast(text, Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示layout:toast_info的CustomView的短toast
	 * 
	 * @param text
	 */
	public static void showCustomInfoShortToast(String text) {
		initCustomViewToast(text, Toast.LENGTH_SHORT, TOAST_CUSTOM_INFO).show();
	}

	/**
	 * 显示layout:toast_info的CustomView的长toast
	 * 
	 * @param text
	 */
	public static void showCustomInfoLongToast(String text) {
		initCustomViewToast(text, Toast.LENGTH_LONG, TOAST_CUSTOM_INFO).show();
	}

	/**
	 * 显示layout:toast_error的CustomView的短toast
	 * 
	 * @param text
	 */
	public static void showCustomErrorShortToast(String text) {
		initCustomViewToast(text, Toast.LENGTH_SHORT, TOAST_CUSTOM_ERROR).show();
	}

	/**
	 * 显示layout:toast_error的CustomView的长toast
	 * 
	 * @param text
	 */
	public static void showCustomErrorLongToast(String text) {
		initCustomViewToast(text, Toast.LENGTH_LONG, TOAST_CUSTOM_ERROR).show();
	}

	private static Toast initBaseToast(String text, int duration) {
		Toast toast = Toast.makeText(MainApplication.getContext(), text, duration);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		return toast;
	}

	/**
	 * 
	 * @param text
	 * @param duration
	 * @param customViewType
	 * <br>
	 *            {@link TOAST_CUSTOM_INFO}：0 <br>
	 *            {@link TOAST_CUSTOM_ERROR}：1
	 * @return
	 */
	private static Toast initCustomViewToast(String text, int duration, int customViewType) {
		Toast toast = initBaseToast(text, duration);
		View customView = LayoutInflater.from(MainApplication.getContext()).inflate(
				R.layout.toast_customview, null);
		((TextView) customView.findViewById(R.id.txtView_toast_customview_message)).setText(text);
		switch (customViewType) {
		case TOAST_CUSTOM_INFO:// 默认
			break;
		case TOAST_CUSTOM_ERROR:
			((ImageView) customView.findViewById(R.id.imgView_toast_customview_icon))
					.setImageResource(R.drawable.toast_error);
			break;
		default:
			break;
		}
		toast.setView(customView);
		return toast;
	}
}
