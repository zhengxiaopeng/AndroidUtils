package com.rocko.drawable;

import android.os.Bundle;

import com.rocko.androidutils.R;
import com.rocko.annotation.ContentView;
import com.rocko.ui.BaseActivity;

/**
 * Created by Rocko on 2015/1/4 0004.
 */
public class DrawableTestActivity extends BaseActivity {

    @Override
    @ContentView(id = R.layout.drawable_test_background_layer_list)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
