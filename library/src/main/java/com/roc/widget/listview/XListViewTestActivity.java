package com.roc.widget.listview;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.roc.androidutils.R;
import com.roc.annotation.ContentView;
import com.roc.common.DebugLog;
import com.roc.ui.BaseActivity;
import com.roc.widget.listview.XListView.IXListViewListener;

import java.util.ArrayList;

public class XListViewTestActivity extends BaseActivity implements IXListViewListener {
    private XListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    @ContentView(id = R.layout.activity_widget_listview_xlistview)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geneItems();
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        mAdapter = new ArrayAdapter<String>(this, R.layout.listview_xlistview_item, items);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
        // mListView.setPullLoadEnable(false);
        // mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
        mListView.setHeaderDividersEnabled(false);
        mListView.setFooterDividersEnabled(false);
    }

    private void geneItems() {
        for (int i = 0; i != 35; ++i) {
            items.add("refresh cnt " + (++start));
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        // mListView.setRefreshTime("刚刚");
        mListView.setPullEnd();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                geneItems();
                // mAdapter.notifyDataSetChanged();
                mAdapter = new ArrayAdapter<String>(XListViewTestActivity.this,
                        R.layout.listview_xlistview_item, items);
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
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
            TextView tv = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_xlistview_item, null);
                DebugLog.i("1111111111111111111111111111111<<");
                tv = (TextView) convertView.findViewById(R.id.list_item_textview);
            }
//			tv.setText(">>" + items.get(position));

            return convertView;
        }

    }
}
