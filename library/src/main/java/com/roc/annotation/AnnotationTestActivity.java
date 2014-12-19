package com.roc.annotation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.roc.androidutils.R;
import com.roc.ui.BaseActivity;

public class AnnotationTestActivity extends BaseActivity implements OnClickListener, OnLongClickListener,
		OnItemClickListener, OnItemSelectedListener, android.widget.CompoundButton.OnCheckedChangeListener,
		OnItemLongClickListener {
	// 注入需要初始化的内容：id为必填，监听和监听类根据需要填，都有默认值
	@InitView(id = R.id.button1, onClickListener = true)
	private Button btn1;
	@InitView(id = R.id.button2, onClickListener = true)
	private Button btn2;
	// ItitView注解的id设置为了数组，这样可以同时给定义在一起的控件一起初始化，避免一个个定义的麻烦
	@InitView(id = R.id.toggleButton1, onCompoundButtonCheckedChangeListener = true)
	private ToggleButton toggleButton1;
	@InitView(id = R.id.toggleButton2, onCompoundButtonCheckedChangeListener = true)
	private ToggleButton toggleButton2;
	@InitView(id = R.id.listview, onItemClickListener = true, onItemLongClickListener = true)
	private ListView listView;
	@InitView(id = R.id.spinner1, onItemSelectedListener = true)
	private Spinner spinner;
	@InitView(id = R.id.checkBox1, onCompoundButtonCheckedChangeListener = true)
	private CheckBox checkBox;

	@Override
	@ContentView(id = R.layout.activity_annotation)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		listView.setAdapter(new MyAdapter());
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "onClick", 0).show();
		if (v.getId() == R.id.button2) {
			Toast.makeText(this, "button2", 0).show();
			btn1.setText("111");
			btn2.setText("222");
		}
	}

	@Override
	public boolean onLongClick(View v) {
		Toast.makeText(this, "onLongClick", 0).show();
		return false;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Toast.makeText(this, "CheckedChanged", 0).show();
		if (buttonView.getId() == R.id.toggleButton2)
			Toast.makeText(this, "toggleButton2", 0).show();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "item click", 0).show();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "item select", 0).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "onItemLongClick", 0).show();
		return true;
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(AnnotationTestActivity.this).inflate(
					R.layout.notification_update, null);
			return convertView;
		}

	}

	private class MyOnItemSelectedListener implements OnItemSelectedListener {
		public MyOnItemSelectedListener() {

		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(AnnotationTestActivity.this, "MyOnItemSelectedListener onItemSelected", 0).show();
			System.out.println("MyOnItemSelectedListener onItemSelected");
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}

	}

}
