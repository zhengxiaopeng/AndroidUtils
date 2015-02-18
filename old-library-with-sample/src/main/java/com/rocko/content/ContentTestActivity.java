package com.rocko.content;

import android.os.Bundle;

import com.rocko.androidutils.R;
import com.rocko.annotation.ContentView;
import com.rocko.common.DebugLog;
import com.rocko.ui.BaseActivity;

/**
 * @author Mr.Zheng
 * @date 2014年10月24日 下午11:16:57
 */
public class ContentTestActivity extends BaseActivity {
    private SharedPreferencesManager spManager;

    @Override
    @ContentView(id = R.layout.activity_content)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spManager = getSharedPreferencesManager("test");
        // 性能测试
        long time;
        DebugLog.v(">>>" + (time = System.currentTimeMillis()));
        for (int i = 0; i < 10000; i++) {
            spManager.putStringValue("a" + i, "test");
        }
        spManager.commit();
        DebugLog.v(">>>" + (System.currentTimeMillis() - time));
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this, "s");
    }
}
