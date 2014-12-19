package com.roc.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.roc.common.DebugLog;

import android.R.fraction;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

/**
 * 为带InitView注解的View控件初始化并设置监听器的加载器<br>
 * 主要方法： {@link #processAnnotation(Object obj)}
 * 
 * @author Mr.Zheng
 * @date 2014年8月11日21:59:01
 */
public class ViewInstaller {

	public static void processAnnotation(Activity obj) {
		process(obj);
	}

	// public static void processAnnotation(Fragment obj) {
	// processAnnotation(obj);
	// }

	/**
	 * 初始化控件，处理View控件的Annotation注解
	 * 
	 * @param obj
	 *            Object实例对象 其或父类应有findViewById方法
	 */
	private static void process(Object obj) {
		int viewId; // 当前Field注解的id值
		View view; // 控件
		InitView initView; // InitView注解
		ContentView layoutView;// LayoutView注解
		try {
			// 获取obj对象的类
			Class cl = obj.getClass();
			/* 设置setContentView */
			Method method = cl.getDeclaredMethod("onCreate", Bundle.class);
			layoutView = method.getAnnotation(ContentView.class);
			if (layoutView != null)
				((Activity) obj).setContentView(layoutView.id());
			// 获取指定obj对象的所有Field，并遍历每个Field
			for (Field f : cl.getDeclaredFields()) {
				initView = f.getAnnotation(InitView.class);
				if (initView != null) {
					DebugLog.v(">>>>>注解的field：" + f.getName());
					viewId = initView.id();
					view = ((Activity) obj).findViewById(viewId);
					// 将Field设置成可以自由访问的，避免private的Field
					f.setAccessible(true);
					// 将obj中属性f的值设置为view,实现findViewById
					f.set(obj, view);
					// 监听添加处理
					if (initView.listenerClass().equals(InitView.class))
						addListener(obj, initView, view);
					else {
						Class listenCl = initView.listenerClass();
						Object listenetObj = null;
						try {
							// 非静态内部类时。要是很多控件使用这个listenerClass的话会new出很多，不太好。debuging
							listenetObj = listenCl.getConstructor(cl).newInstance(obj);
						} catch (Exception e) {
							// 一般类或静态内部类时
							listenetObj = listenCl.newInstance();
							e.printStackTrace();
						}
						addListener(listenetObj, initView, view);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("View控件初始化异常，请检查控件InitView注解配置。\n" + e);
		}
	}

	/**
	 * 添加监听
	 * 
	 * @param obj
	 *            监听类实例
	 * @param initView
	 *            View控件锁配置的InitView
	 * @param view
	 */
	private static void addListener(Object obj, InitView initView, View view) {
		// 单击
		if (initView.onClickListener()) {
			view.setOnClickListener((android.view.View.OnClickListener) obj);
		}
		// 长按
		if (initView.onLongClickListener()) {
			view.setOnLongClickListener((android.view.View.OnLongClickListener) obj);
		}
		// item单击
		if (initView.onItemClickListener()) {
			((AdapterView) view).setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) obj);
		}
		// item长按
		if (initView.onItemLongClickListener()) {
			((AdapterView) view)
					.setOnItemLongClickListener((android.widget.AdapterView.OnItemLongClickListener) obj);
		}
		// item选择
		if (initView.onItemSelectedListener()) {
			((AdapterView) view)
					.setOnItemSelectedListener((android.widget.AdapterView.OnItemSelectedListener) obj);
		}
		// CompoundButton的item选择更改
		if (initView.onCompoundButtonCheckedChangeListener()) {
			((CompoundButton) view)
					.setOnCheckedChangeListener((android.widget.CompoundButton.OnCheckedChangeListener) obj);
		}
		// RadioGroup的item选择更改
		else if (initView.onRadioGroupCheckedChangeListener()) {
			((RadioGroup) view)
					.setOnCheckedChangeListener((android.widget.RadioGroup.OnCheckedChangeListener) obj);
		}
	}
}