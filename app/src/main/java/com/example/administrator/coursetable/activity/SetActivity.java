package com.example.administrator.coursetable.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.abstracts.OnActionDown;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.dialog.ExitStyleDialog;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;

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

        boolean isRemind = (boolean) SharedPreferencesUtils.getParam(context,Constants.UP_CLASS_REMIND,true);
        boolean isExam =   (boolean) SharedPreferencesUtils.getParam(context,Constants.EXAM_REMIND,false);
        boolean isAuto =   (boolean) SharedPreferencesUtils.getParam(context,Constants.AUTO_ADD_BG_COLOR,false);

        up_class_tip_checkbox.setChecked(isRemind);
        test_tip_checkbox.setChecked(isExam);
        auto_add_bg_color_checkbox.setChecked(isAuto);

    }

    private void eventInit() {
        up_class_tip_checkbox.setOnCheckedChangeListener(this);
        test_tip_checkbox.setOnCheckedChangeListener(this);
        auto_add_bg_color_checkbox.setOnCheckedChangeListener(this);
        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        about_us.setOnClickListener(this);
        exit.setOnClickListener(this);
        up_class_time_set.setOnClickListener(this);
        gone.setOnClickListener(this);
        about.setOnClickListener(this);
        test_time_set.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.test_time_set:

                startActivity(new Intent(context,SetExamTimeActivity.class));

                break;
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
            case R.id.gone:

                startActivity(new Intent(context,HideWeekActivity.class));

                break;
            case R.id.about_us:

                about_us.setVisibility(View.GONE);

                break;
            case R.id.about:

                startActivity(new Intent(context,AboutActivity.class));
                break;
            case R.id.exit:

                ExitStyleDialog dialog = new ExitStyleDialog(context, new OnActionDown() {
                    @Override
                    public void onExit() {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        startActivity(intent);

                    }
                }, Constants.EXIT_STYLE_EXIT);
                dialog.show();

                break;
            case R.id.up_class_time_set:

                startActivity(new Intent(context,CourseMessageNoteActivity.class));

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId())
        {
            case R.id.up_class_tip_checkbox:

                SharedPreferencesUtils.putParam(context,Constants.UP_CLASS_REMIND,b);

                break;
            case R.id.test_tip_checkbox:
                SharedPreferencesUtils.putParam(context,Constants.EXAM_REMIND,b);
                break;
            case R.id.auto_add_bg_color_checkbox:
                SharedPreferencesUtils.putParam(context,Constants.AUTO_ADD_BG_COLOR,b);
                break;
        }
    }
}
