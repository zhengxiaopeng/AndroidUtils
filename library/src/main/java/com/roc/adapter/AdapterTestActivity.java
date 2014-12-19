package com.roc.adapter;

import android.os.Bundle;
import android.widget.Button;

import com.roc.androidutils.R;
import com.roc.ui.BaseActivity;

public class AdapterTestActivity extends BaseActivity {
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btn = findView(R.id.button1);
	}
}
