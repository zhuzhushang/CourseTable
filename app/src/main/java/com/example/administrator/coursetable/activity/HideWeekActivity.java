package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.adapter.HideWeekAdapter;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.HideWeekModel;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaoQuanwei on 2017/2/13.
 */

public class HideWeekActivity  extends BaseActivity implements View.OnClickListener {

    private RecyclerView hw_recycle;
    private HideWeekAdapter mAdapter;
    private List<HideWeekModel> mList;
    private String weekData[] = {"周一","周二","周三","周四","周五","周六","周日",};
    private String checkData[];
    private String ww = "1,1,1,1,1,1,1";


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_hide_week);
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

        hw_recycle = (RecyclerView) findViewById(R.id.hw_recycle);


    }

    private void dataInit() {
        // TODO Auto-generated method stub


        mList = new ArrayList<>();
        initList();
        mAdapter = new HideWeekAdapter(context,mList);
        hw_recycle.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        hw_recycle.setAdapter(mAdapter);



    }

    private void initList() {

        String str = (String) SharedPreferencesUtils.getParam(context, Constants.HIDE_WEEK_DATA,ww);
        checkData = str.split(",");

        for (int i = 0; i < weekData.length; i++) {

            HideWeekModel model = new HideWeekModel();
            model.setIsCheck(checkData[i]);
            model.setWhatDay(weekData[i]);

            mList.add(model);
        }


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