package com.roc.update;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.roc.androidutils.R;
import com.roc.annotation.InitView;
import com.roc.annotation.ContentView;
import com.roc.ui.BaseActivity;

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
