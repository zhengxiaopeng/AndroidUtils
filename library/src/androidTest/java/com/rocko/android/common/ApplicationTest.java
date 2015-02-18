package com.rocko.android.common;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.rocko.android.common.app.ACApplication;
import com.rocko.android.common.util.ToastHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<ACApplication> {
    public ApplicationTest() {
        super(ACApplication.class);
    }
    
    public void testContext(){
        assertNotNull(ACApplication.getContext());
    }
}