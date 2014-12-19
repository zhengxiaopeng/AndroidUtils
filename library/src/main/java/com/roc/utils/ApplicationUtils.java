package com.roc.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Environment;

import com.roc.app.MainApplication;
import com.roc.security.MD5Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Rocko on 2014/12/19 0019.
 */
public class ApplicationUtils {

    /**
     * 备份本程序的apk安装文件到SD卡根目录下
     *
     * @param packageName
     * @param mActivity
     * @throws java.io.IOException
     */
    public static void backupApp() throws IOException {
        // 存放位置
        String newFile = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        String oldFile = null;
        try {
            // 原始位置
            oldFile = MainApplication.getContext().getPackageManager().getApplicationInfo(MainApplication.getContext().getPackageName(), 0).sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(newFile);
        System.out.println(oldFile);

        File in = new File(oldFile);
        File out = new File(newFile + MainApplication.getContext().getPackageName() + ".apk");
        if (!out.exists()) {
            out.createNewFile();
        } else {
        }

        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);

        int count;
        // 文件太大的话，我觉得需要修改
        byte[] buffer = new byte[256 * 1024];
        while ((count = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    /**
     * 获取应用的MD5签名值
     *
     * @param context
     * @return
     */
    public static String getSign() {
        PackageManager packageManager = MainApplication.getContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(MainApplication.getContext().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            return MD5Helper.hexdigest(sign.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用版本Code
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取应用版本Name
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "unkonwn";
    }
}
