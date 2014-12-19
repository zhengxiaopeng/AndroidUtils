package com.roc.io;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

import com.roc.utils.ApplicationUtils;
import com.roc.utils.CommonUtils;

/**
 * @author Mr.Zheng
 * @date 2014年11月2日 上午12:49:30
 */
public class IOHelper {
	/**
	 * 获取DiskLruCache实例
	 * 
	 * @param context
	 * @param uniqueName
	 *            对不同类型的数据进行区分而设定的一个唯一值
	 * @return
	 */
	public static DiskLruCache getDiskLruCache(Context context, String uniqueName) {
		DiskLruCache mDiskLruCache = null;
		try {
			File cacheDir = getDiskCacheDir(context, uniqueName);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = DiskLruCache.open(cacheDir, ApplicationUtils.getAppVersionCode(), 1,
					50 * 1024 * 1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mDiskLruCache;
	}

	/**
	 * 获取缓存存放地址
	 * 
	 * @param context
	 * @param uniqueName
	 *            对不同类型的数据进行区分而设定的一个唯一值
	 * @return
	 */
	private static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable())
			cachePath = context.getExternalCacheDir().getPath();
		else
			cachePath = context.getCacheDir().getPath();
		// cachePath = Environment.getExternalStorageDirectory() +
		// File.separator + "PhotoWall";
		return new File(cachePath + File.separator + uniqueName);
	}

}
