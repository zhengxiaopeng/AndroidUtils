package com.rocko.update;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rocko.androidutils.R;
import com.rocko.annotation.ContentView;
import com.rocko.annotation.InitView;
import com.rocko.ui.BaseActivity;

public class UpdateTestActivity extends BaseActivity implements OnClickListener {
    @Override
    @ContentView(id = R.layout.activity_update)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @InitView(id = R.id.button1, onClickListener = true)
    private Button button;

    @Override
    public void onClick(View v) {
        UpdateManager.beginUpdate(this, true);
    }

}
