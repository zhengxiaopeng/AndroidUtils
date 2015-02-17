package com.rocko.annotation;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyListener implements OnClickListener, OnItemSelectedListener {

    @Override
    public void onClick(View v) {
        System.out.println("MyListener onClick");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("MyListener onItemSelected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

}
