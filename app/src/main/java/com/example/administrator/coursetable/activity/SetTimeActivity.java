package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.coursetable.R;

/**
 * Created by Administrator on 2017/1/2.
 */

public class SetTimeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_set_time);
        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

    }

    private void dataInit() {

    }

    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
        }
    }

}
