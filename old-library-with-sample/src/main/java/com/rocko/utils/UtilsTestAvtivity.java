package com.rocko.utils;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.rocko.androidutils.R;
import com.rocko.annotation.ContentView;
import com.rocko.ui.BaseActivity;
import static com.rocko.common.DebugLog.*;
import java.io.IOException;

public class UtilsTestAvtivity extends BaseActivity {
    @Override
    @ContentView(id = R.layout.activity_utils)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v(ApplicationUtils.getSign(getContext()));
        v("Has com.roc.utils.UtilsTestAvtivity? " + ClassHelper.hasClass("com.roc.utils.UtilsTestAvtivity"));
        v("Has com.roc.utils.XXXClass? " + ClassHelper.hasClass("com.roc.utils.XXXClass"));

    }

    public void click(View v) throws IOException {
    }

    /**
     * patcher测试
     *
     * @param v
     */
    public void getNewApk(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String oldApkPath = Environment.getExternalStorageDirectory() + "/old.apk";
                String newApkPath = Environment.getExternalStorageDirectory() + "/new.apk";
                String patchPath = Environment.getExternalStorageDirectory() + "/update_file.patch";
                PatcherUtils.patcher(oldApkPath, newApkPath, patchPath);
            }
        }).start();
    }
}
