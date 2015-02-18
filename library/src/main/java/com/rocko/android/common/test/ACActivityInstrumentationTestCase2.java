package com.rocko.android.common.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

/**
 * Created by Rocko on 2015/2/17 17:23
 */
public abstract class ACActivityInstrumentationTestCase2<T extends Activity> extends ActivityInstrumentationTestCase2 {
    public ACActivityInstrumentationTestCase2(Class activityClass) {
        super(activityClass);

    }

    /**
     * findViewById
     *
     * @param id
     * @return
     */
    protected <T extends View> T findViewById(int id) {
        return (T) getActivity().findViewById(id);
    }
}
