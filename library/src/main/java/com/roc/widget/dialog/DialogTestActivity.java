package com.roc.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.roc.androidutils.R;
import com.roc.annotation.InitView;
import com.roc.annotation.ContentView;
import com.roc.annotation.ViewInstaller;
import com.roc.ui.BaseActivity;
import com.roc.utils.DialogUtils;

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
