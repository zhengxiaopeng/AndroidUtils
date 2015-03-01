package com.rocko.android.common.util;

import android.os.Environment;
import android.test.InstrumentationTestCase;

/**
 * Created by Rocko on 2015/2/28 11:07
 */
public class UtilsTest extends InstrumentationTestCase {

    public UtilsTest() {
    }

    public void testPatcher() throws Exception {
        PatcherUtils.patcher(Environment.getExternalStorageState(), Environment.getExternalStorageState(), Environment.getExternalStorageState());
    }
}
