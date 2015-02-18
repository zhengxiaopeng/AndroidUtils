package com.rocko.android.common.util;

import android.content.SharedPreferences;
import android.test.ApplicationTestCase;

import com.rocko.android.common.app.ACApplication;
import com.rocko.android.common.content.SharedPreferencesManager;

/**
 * Created by Rocko on 2015/2/18 11:41
 * Util包的测试
 */
public class UtilsTest extends ApplicationTestCase<ACApplication> {

    public UtilsTest() {
        super(ACApplication.class);
    }

    public void testSharedPreferences() {
        SharedPreferencesManager spm = new SharedPreferencesManager(getContext(), "test");
        spm.putStringValue("key", "value");
        spm.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                assertNotNull("Value is null", key);
            }
        });
    }
}
