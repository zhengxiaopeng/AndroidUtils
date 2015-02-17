package com.rocko.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rocko.androidutils.R;
import com.rocko.annotation.ContentView;
import com.rocko.annotation.InitView;
import com.rocko.ui.BaseActivity;
import com.rocko.utils.DialogUtils;

public class DialogTestActivity extends BaseActivity implements OnClickListener {
    @InitView(id = R.id.btn_dialog_1, onClickListener = true)
    private Button btn1;

    @Override
    @ContentView(id = R.layout.activity_dialog)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_1:
                SimpleDialog simpleDialog = DialogUtils.createSimpleDialog(this, null);
                simpleDialog.setTitle("title");
                simpleDialog.setMsg("msg").showDialog(false, true);
                break;
            default:
                break;
        }
    }
}
