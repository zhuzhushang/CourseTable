package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.coursetable.R;

/**
 * Created by ShaoQuanwei on 2017/2/13.
 */

public class CopyActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_main);
        appbarInit();
        viewInit();
        dataInit();
        eventInit();


    }

    private void appbarInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
    }

    private void viewInit() {
        // TODO Auto-generated method stub

    }

    private void dataInit() {
        // TODO Auto-generated method stub

    }

    private void eventInit() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appbar_left_tv_arrow:

                finish();
                break;

        }

    }
}