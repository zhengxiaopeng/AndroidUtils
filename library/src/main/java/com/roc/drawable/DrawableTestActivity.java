package com.roc.drawable;

import android.os.Bundle;

import com.roc.androidutils.R;
import com.roc.annotation.ContentView;
import com.roc.ui.BaseActivity;

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
