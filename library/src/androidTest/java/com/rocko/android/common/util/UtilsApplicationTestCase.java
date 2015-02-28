package com.rocko.android.common.util;

import android.content.SharedPreferences;
import android.os.Environment;
import android.test.ApplicationTestCase;

import com.rocko.android.common.app.ACApplication;
import com.rocko.android.common.content.SharedPreferencesManager;

/**
 * Created by Rocko on 2015/2/18 11:41
 * Util包的测试
 */
public class UtilsApplicationTestCase extends ApplicationTestCase<ACApplication> {

    public UtilsApplicationTestCase() {
        super(ACApplication.class);
    }

    /**
     * SharedPreferencesManager
     */
    public void testSharedPreferences() throws Exception {
        SharedPreferencesManager spm = new SharedPreferencesManager(getContext(), "test");
        spm.putStringValue("key", "value");
        spm.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                assertNotNull("Value is null", key);
            }
        });
    }

    /**
     * 增量更新测试，so的代码是否能运行
     *
     * @throws Exception
     */
    public void testPatcher() throws Exception {
        PatcherUtils.patcher(Environment.getExternalStorageState(), Environment.getExternalStorageState(), Environment.getExternalStorageState());
    }
}
