package com.example.administrator.coursetable.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.administrator.coursetable.R;

import java.util.Calendar;

/**
 * Created by ShaoQuanwei on 2017/2/14.
 */

public class SetExamTimeActivity extends BaseActivity implements View.OnClickListener {

    private TextView exam_test_date,exam_test_time;
    private TextView english_date,english_time;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_set_exam_time);
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

        exam_test_date = (TextView) findViewById(R.id.exam_test_date);
        exam_test_time = (TextView) findViewById(R.id.exam_test_time);

        english_date = (TextView) findViewById(R.id.english_date);
        english_time = (TextView) findViewById(R.id.english_time);
    }

    private void dataInit() {
        // TODO Auto-generated method stub

    }

    private void eventInit() {
        // TODO Auto-generated method stub

        exam_test_date.setOnClickListener(this);
        exam_test_time.setOnClickListener(this);

        english_date.setOnClickListener(this);
        english_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appbar_left_tv_arrow:

                finish();
                break;
            case R.id.exam_test_date:

                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        exam_test_date.setText(year+"-"+month+"-"+dayOfMonth);

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


                break;
            case R.id.exam_test_time:

                break;
            case R.id.english_date:

                break;
            case R.id.english_time:

                break;

        }

    }
}