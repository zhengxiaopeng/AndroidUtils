package com.roc.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.roc.androidutils.R;
import com.roc.update.UpdateManager;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}

	public void click(View v) {
		UpdateManager.beginUpdate(this, true);
	}

}
