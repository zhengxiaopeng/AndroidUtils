package com.rocko.test;

import android.os.Bundle;
import android.view.View;

import com.rocko.androidutils.R;
import com.rocko.ui.BaseActivity;
import com.rocko.update.UpdateManager;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void click(View v) {
        UpdateManager.beginUpdate(this, true);
    }

}
