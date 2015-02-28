package com.rocko.android.common;

import android.test.ApplicationTestCase;

import com.rocko.android.common.app.ACApplication;


public class ApplicationTest extends ApplicationTestCase<ACApplication> {
    public ApplicationTest() {
        super(ACApplication.class);
    }

    /**
     *
     */
    public void testContext() {
        assertNotNull(ACApplication.getContext());
    }
}