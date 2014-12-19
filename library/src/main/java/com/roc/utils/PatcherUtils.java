package com.roc.utils;

/**
 * 增量更新工具<br>
 * 这个类需要在这个包下，如需换到其它包中请看：<br>
 * https://github.com/zhengxiaopeng/AndroidPatcherUpdate
 * 
 * @author Mr.Zheng
 * @date 2014年8月20日21:34:59
 */
public class PatcherUtils {
	static {
		System.loadLibrary("Patcher");
	}

	/**
	 * 调用Patcher.so库中的方法,合并apk
	 * 
	 * @param old
	 *            旧Apk地址
	 * @param newapk
	 *            新apk地址
	 * @param patch
	 *            增量包地址
	 */
	public static native void patcher(String oldApkPath, String newapkPath, String patchPath);
}
