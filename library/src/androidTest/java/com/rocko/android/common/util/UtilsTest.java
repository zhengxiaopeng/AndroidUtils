/*
 * Copyright 2015 Rocko(zhengxiaopeng).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rocko.android.common.util;

import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.rocko.android.common.app.ACApplication;

import org.junit.Test;

/**
 * Created by Rocko on 2015/2/28 11:07
 */
@SmallTest
public class UtilsTest extends ApplicationTestCase<ACApplication> {

    public UtilsTest() {
        super(ACApplication.class);
    }

    @Test
    public void testBatteryUtilsIsCharing() throws Exception {
        assertTrue("testBatteryUtilsIsCharing() test wrong", BatteryUtils.isCharging(getContext()));
    }
}
