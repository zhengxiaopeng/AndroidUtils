package com.roc.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

/**
 * 动画效果工具类
 * 
 * @author Mr.Zheng
 * @date 2014年7月16日21:51:05
 */
public class AnimUtils {
	private static Animation animation;

	/**
	 * 为控件开启anim资源里的动画
	 * 
	 * @param context
	 * @param view
	 *            控件
	 * @param animID
	 *            动画资源文件id
	 */
	public static void startAnimation(Context context, View view, int animID) {
		animation = AnimationUtils.loadAnimation(context, animID);
		view.startAnimation(animation);
	}

	/**
	 * 为控件开启左右平移动画
	 * 
	 * @param view
	 *            控件
	 */
	public static void startTranslationAnimation(View view) {
		TranslateAnimation translateAnimation = new TranslateAnimation(-50f, 50f, 0, 0);
		translateAnimation.setDuration(1000);
		translateAnimation.setRepeatCount(Animation.INFINITE);
		translateAnimation.setRepeatMode(Animation.REVERSE);
		view.setAnimation(translateAnimation);
		translateAnimation.start();
	}

	/**
	 * GONE view展开动画
	 * 
	 * @param v
	 */
	public static void expand(final View v) {
		v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		final int targtetHeight = v.getMeasuredHeight();

		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT
						: (int) (targtetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		a.setDuration(300);
		v.startAnimation(a);
	}

	/**
	 * view关闭动画(变为GONE)
	 * 
	 * @param v
	 */
	public static void collapse(final View v) {
		final int initialHeight = v.getMeasuredHeight();
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		a.setDuration(300);
		v.startAnimation(a);
	}
}
