package com.rocko.android.common.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.rocko.android.common.util.DialogUtils;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogUtils.stratProgressDialog(this, "ss", false, false);
//        final LoadingDialog loadingDialog = new LoadingDialog(this);
//        loadingDialog.setMsg("Loading");
//        loadingDialog.show();
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                loadingDialog.dismiss();
//                DialogUtils.stopProgressDialog();
            }
        }.sendEmptyMessageDelayed(0, 3000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
