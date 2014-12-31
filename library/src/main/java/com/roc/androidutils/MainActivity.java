package com.roc.androidutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.roc.animation.AnimationTestActivity;
import com.roc.annotation.AnnotationTestActivity;
import com.roc.annotation.ContentView;
import com.roc.annotation.InitView;
import com.roc.content.ContentTestActivity;
import com.roc.http.volley.HttpVolleyTestActivity;
import com.roc.io.LruCacheTestActivity;
import com.roc.test.TestActivity;
import com.roc.ui.BaseActivity;
import com.roc.update.UpdateTestActivity;
import com.roc.utils.UtilsTestAvtivity;
import com.roc.widget.dialog.DialogTestActivity;
import com.roc.widget.listview.XListViewTestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 程序入口
 */
public class MainActivity extends BaseActivity implements OnItemClickListener {
    @InitView(id = R.id.gv_main_menu, onItemClickListener = true)
    private GridView gridView;
    private List<Intent> listIntents;
    private List<Map<String, Object>> listGridItems;
    private SimpleAdapter simpleAdapter;

    @Override
    @ContentView(id = R.layout.activity_main)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setLeftFlingFinish(false);
        listIntents = new ArrayList<Intent>();

        listIntents.add(new Intent(this, TestActivity.class).putExtra("name", "test").putExtra("icon",
                R.drawable.ic_launcher));
        listIntents.add(new Intent(this, AnnotationTestActivity.class).putExtra("name", "anotation")
                .putExtra("icon", R.drawable.android));
        listIntents.add(new Intent(this, UpdateTestActivity.class).putExtra("name", "update").putExtra(
                "icon", R.drawable.ic_launcher));
        listIntents.add(new Intent(this, UtilsTestAvtivity.class).putExtra("name", "utils").putExtra("icon",
                R.drawable.ic_launcher));
        listIntents.add(new Intent(this, XListViewTestActivity.class).putExtra("name", "listview").putExtra(
                "icon", R.drawable.android));
        listIntents.add(new Intent(this, LruCacheTestActivity.class).putExtra("name", "io").putExtra("icon",
                R.drawable.ic_launcher));
        listIntents.add(new Intent(this, AnimationTestActivity.class).putExtra("name", "animation").putExtra(
                "icon", R.drawable.ic_launcher));
        listIntents.add(new Intent(this, DialogTestActivity.class).putExtra("name", "dialog").putExtra(
                "icon", R.drawable.android));
        listIntents.add(new Intent(this, HttpVolleyTestActivity.class).putExtra("name", "http").putExtra("icon",
                R.drawable.ic_launcher));
        listIntents.add(new Intent(this, ContentTestActivity.class).putExtra("name", "content").putExtra("icon",
                R.drawable.android));

        listGridItems = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapGridItem;
        for (int i = 0; i < listIntents.size(); i++) {
            mapGridItem = new HashMap<String, Object>();
            mapGridItem.put("icon", listIntents.get(i).getIntExtra("icon", R.drawable.ic_launcher));
            mapGridItem.put("name", listIntents.get(i).getStringExtra("name"));
            listGridItems.add(mapGridItem);
        }
        simpleAdapter = new SimpleAdapter(this, listGridItems, R.layout.androidutils_gridview_menu,
                new String[]{"icon", "name"}, new int[]{R.id.imgView_gridview, R.id.tv_gridview});

        gridView.setAdapter(simpleAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(listIntents.get(position));
    }

}
