package com.rocko.config;

import android.os.Environment;

/**
 * 全局基本信息
 *
 * @author Mr.Zheng
 */
public class Configs {
    /**
     * 更新文件夹
     */
    public static final String UPDATE_DOWNLOAD_PATH = "RepairSystem/update";
    /**
     * 外部存储地址
     */
    public static final String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory()
            .toString() + "/";
    /**
     * 更新下载后的文件名
     */
    public static final String NEW_APK_FILE_NAME = "/RepairSystem.apk";
}
