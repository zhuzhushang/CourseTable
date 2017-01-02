package com.example.administrator.coursetable.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.coursetable.R;

/**
 * Created by Administrator on 2017/1/2.
 */

public class SetActivity extends BaseActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {


    private RelativeLayout up_class_time_set,gone,test_time_set,about,exit;
    private CheckBox up_class_tip_checkbox,test_tip_checkbox,auto_add_bg_color_checkbox;
    private ImageView about_us;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_set);

        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        up_class_time_set = (RelativeLayout) findViewById(R.id.up_class_time_set);
        gone = (RelativeLayout) findViewById(R.id.gone);
        test_time_set = (RelativeLayout) findViewById(R.id.test_time_set);
        about = (RelativeLayout) findViewById(R.id.about);
        exit = (RelativeLayout) findViewById(R.id.exit);


        up_class_tip_checkbox = (CheckBox) findViewById(R.id.up_class_tip_checkbox);
        test_tip_checkbox = (CheckBox) findViewById(R.id.test_tip_checkbox);
        auto_add_bg_color_checkbox = (CheckBox) findViewById(R.id.auto_add_bg_color_checkbox);

        about_us = (ImageView) findViewById(R.id.about_us);
    }

    private void dataInit() {

    }

    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        about_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
            case R.id.about_us:

                about_us.setVisibility(View.GONE);

                break;
            case R.id.about:

                about_us.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId())
        {
            case R.id.up_class_tip_checkbox:

                break;
            case R.id.test_tip_checkbox:

                break;
            case R.id.auto_add_bg_color_checkbox:

                break;
        }
    }
}
