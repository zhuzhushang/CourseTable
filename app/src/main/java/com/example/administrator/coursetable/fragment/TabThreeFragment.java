package com.example.administrator.coursetable.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.activity.CourseMessageNoteActivity;
import com.example.administrator.coursetable.activity.NotesActivity;
import com.example.administrator.coursetable.activity.SetActivity;
import com.example.administrator.coursetable.adapter.CurrentDayAdapter;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.CurrentDayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TabThreeFragment extends BaseFragment implements View.OnClickListener {

    private AppCompatImageView appbar_note, appbar_change, appbar_more;
    private LinearLayout current_day_ll;
    private TextView week_position;
    private ListView current_day_listview;
    private List<CurrentDayModel> mList;
    private CurrentDayAdapter mAdapter;

    private TextView appbar_center_tv;

    //课程表控件
    private TextView mon_mor_read_tv_1, mon_mor_read_tv_2, mon_morning_tv_1, mon_morning_tv_2, mon_morning_tv_3, mon_morning_tv_4, mon_afternoon_tv_1, mon_afternoon_tv_2, mon_afternoon_tv_3, mon_evening_tv_1, mon_evening_tv_2;
    private TextView tues_mor_read_tv_1, tues_mor_read_tv_2, tues_morning_tv_1, tues_morning_tv_2, tues_morning_tv_3, tues_morning_tv_4, tues_afternoon_tv_1, tues_afternoon_tv_2, tues_afternoon_tv_3, tues_evening_tv_1, tues_evening_tv_2;
    private TextView wed_mor_read_tv_1, wed_mor_read_tv_2, wed_morning_tv_1, wed_morning_tv_2, wed_morning_tv_3, wed_morning_tv_4, wed_afternoon_tv_1, wed_afternoon_tv_2, wed_afternoon_tv_3, wed_evening_tv_1, wed_evening_tv_2;
    private TextView thur_mor_read_tv_1, thur_mor_read_tv_2, thur_morning_tv_1, thur_morning_tv_2, thur_morning_tv_3, thur_morning_tv_4, thur_afternoon_tv_1, thur_afternoon_tv_2, thur_afternoon_tv_3, thur_evening_tv_1, thur_evening_tv_2;
    private TextView fri_mor_read_tv_1, fri_mor_read_tv_2, fri_morning_tv_1, fri_morning_tv_2, fri_morning_tv_3, fri_morning_tv_4, fri_afternoon_tv_1, fri_afternoon_tv_2, fri_afternoon_tv_3, fri_evening_tv_1, fri_evening_tv_2;
    private TextView sat_mor_read_tv_1, sat_mor_read_tv_2, sat_morning_tv_1, sat_morning_tv_2, sat_morning_tv_3, sat_morning_tv_4, sat_afternoon_tv_1, sat_afternoon_tv_2, sat_afternoon_tv_3, sat_evening_tv_1, sat_evening_tv_2;
    private TextView sun_mor_read_tv_1, sun_mor_read_tv_2, sun_morning_tv_1, sun_morning_tv_2, sun_morning_tv_3, sun_morning_tv_4, sun_afternoon_tv_1, sun_afternoon_tv_2, sun_afternoon_tv_3, sun_evening_tv_1, sun_evening_tv_2;

    private TextView monTvArray[] = {mon_mor_read_tv_1, mon_mor_read_tv_2, mon_morning_tv_1, mon_morning_tv_2, mon_morning_tv_3, mon_morning_tv_4, mon_afternoon_tv_1, mon_afternoon_tv_2, mon_afternoon_tv_3, mon_evening_tv_1, mon_evening_tv_2};
    private int monTvArrayID[] = {R.id.mon_mor_read_tv_1, R.id.mon_mor_read_tv_2, R.id.mon_morning_tv_1, R.id.mon_morning_tv_2, R.id.mon_morning_tv_3, R.id.mon_morning_tv_4, R.id.mon_afternoon_tv_1, R.id.mon_afternoon_tv_2, R.id.mon_afternoon_tv_3, R.id.mon_evening_tv_1, R.id.mon_evening_tv_2};

    private TextView tuesTvArray[] = {tues_mor_read_tv_1, tues_mor_read_tv_2, tues_morning_tv_1, tues_morning_tv_2, tues_morning_tv_3, tues_morning_tv_4, tues_afternoon_tv_1, tues_afternoon_tv_2, tues_afternoon_tv_3, tues_evening_tv_1, tues_evening_tv_2};
    private int tuesTvArrayID[] = {R.id.tues_mor_read_tv_1, R.id.tues_mor_read_tv_2, R.id.tues_morning_tv_1, R.id.tues_morning_tv_2, R.id.tues_morning_tv_3, R.id.tues_morning_tv_4, R.id.tues_afternoon_tv_1, R.id.tues_afternoon_tv_2, R.id.tues_afternoon_tv_3, R.id.tues_evening_tv_1, R.id.tues_evening_tv_2};


    private TextView wedTvArray[] = {wed_mor_read_tv_1, wed_mor_read_tv_2, wed_morning_tv_1, wed_morning_tv_2, wed_morning_tv_3, wed_morning_tv_4, wed_afternoon_tv_1, wed_afternoon_tv_2, wed_afternoon_tv_3, wed_evening_tv_1, wed_evening_tv_2};
    private int wedTvArrayID[] = {R.id.wed_mor_read_tv_1, R.id.wed_mor_read_tv_2, R.id.wed_morning_tv_1, R.id.wed_morning_tv_2, R.id.wed_morning_tv_3, R.id.wed_morning_tv_4, R.id.wed_afternoon_tv_1, R.id.wed_afternoon_tv_2, R.id.wed_afternoon_tv_3, R.id.wed_evening_tv_1, R.id.wed_evening_tv_2};


    private TextView thurTvArray[] = {thur_mor_read_tv_1, thur_mor_read_tv_2, thur_morning_tv_1, thur_morning_tv_2, thur_morning_tv_3, thur_morning_tv_4, thur_afternoon_tv_1, thur_afternoon_tv_2, thur_afternoon_tv_3, thur_evening_tv_1, thur_evening_tv_2};
    private int thurTvArrayID[] = {R.id.thur_mor_read_tv_1, R.id.thur_mor_read_tv_2, R.id.thur_morning_tv_1, R.id.thur_morning_tv_2, R.id.thur_morning_tv_3, R.id.thur_morning_tv_4, R.id.thur_afternoon_tv_1, R.id.thur_afternoon_tv_2, R.id.thur_afternoon_tv_3, R.id.thur_evening_tv_1, R.id.thur_evening_tv_2};

    private TextView friTvArray[] = {fri_mor_read_tv_1, fri_mor_read_tv_2, fri_morning_tv_1, fri_morning_tv_2, fri_morning_tv_3, fri_morning_tv_4, fri_afternoon_tv_1, fri_afternoon_tv_2, fri_afternoon_tv_3, fri_evening_tv_1, fri_evening_tv_2};
    private int friTvArrayID[] = {R.id.fri_mor_read_tv_1, R.id.fri_mor_read_tv_2, R.id.fri_morning_tv_1, R.id.fri_morning_tv_2, R.id.fri_morning_tv_3, R.id.fri_morning_tv_4, R.id.fri_afternoon_tv_1, R.id.fri_afternoon_tv_2, R.id.fri_afternoon_tv_3, R.id.fri_evening_tv_1, R.id.fri_evening_tv_2};


    private TextView satTvArray[] = {sat_mor_read_tv_1, sat_mor_read_tv_2, sat_morning_tv_1, sat_morning_tv_2, sat_morning_tv_3, sat_morning_tv_4, sat_afternoon_tv_1, sat_afternoon_tv_2, sat_afternoon_tv_3, sat_evening_tv_1, sat_evening_tv_2};
    private int satTvArrayID[] = {R.id.sat_mor_read_tv_1, R.id.sat_mor_read_tv_2, R.id.sat_morning_tv_1, R.id.sat_morning_tv_2, R.id.sat_morning_tv_3, R.id.sat_morning_tv_4, R.id.sat_afternoon_tv_1, R.id.sat_afternoon_tv_2, R.id.sat_afternoon_tv_3, R.id.sat_evening_tv_1, R.id.sat_evening_tv_2};

    private TextView sunTvArray[] = {sun_mor_read_tv_1, sun_mor_read_tv_2, sun_morning_tv_1, sun_morning_tv_2, sun_morning_tv_3, sun_morning_tv_4, sun_afternoon_tv_1, sun_afternoon_tv_2, sun_afternoon_tv_3, sun_evening_tv_1, sun_evening_tv_2};
    private int sunTvArrayID[] = {R.id.sun_mor_read_tv_1, R.id.sun_mor_read_tv_2, R.id.sun_morning_tv_1, R.id.sun_morning_tv_2, R.id.sun_morning_tv_3, R.id.sun_morning_tv_4, R.id.sun_afternoon_tv_1, R.id.sun_afternoon_tv_2, R.id.sun_afternoon_tv_3, R.id.sun_evening_tv_1, R.id.sun_evening_tv_2};

    private TextView qrArray[][] = {monTvArray,tuesTvArray,wedTvArray,thurTvArray,friTvArray,satTvArray,sunTvArray};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tab_3, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewInit(view);
        dataInit();
        eventInit();

    }

    private void ViewInit(View view) {

        appbar_note = (AppCompatImageView) view.findViewById(R.id.appbar_note);
        appbar_change = (AppCompatImageView) view.findViewById(R.id.appbar_change);
        appbar_more = (AppCompatImageView) view.findViewById(R.id.appbar_more);

        current_day_ll = (LinearLayout) view.findViewById(R.id.current_day_ll);
        week_position = (TextView) view.findViewById(R.id.week_position);
        current_day_listview = (ListView) view.findViewById(R.id.current_day_listview);

        appbar_center_tv = (TextView) view.findViewById(R.id.appbar_center_tv);

        for (int i = 0; i < monTvArray.length; i++) {

            monTvArray[i] = (TextView) view.findViewById(monTvArrayID[i]);
            tuesTvArray[i] = (TextView) view.findViewById(tuesTvArrayID[i]);
            wedTvArray[i] = (TextView) view.findViewById(wedTvArrayID[i]);
            thurTvArray[i] = (TextView) view.findViewById(thurTvArrayID[i]);
            friTvArray[i] = (TextView) view.findViewById(friTvArrayID[i]);
            satTvArray[i] = (TextView) view.findViewById(satTvArrayID[i]);
            sunTvArray[i] = (TextView) view.findViewById(sunTvArrayID[i]);

            monTvArray[i].setOnClickListener(this);
            tuesTvArray[i].setOnClickListener(this);
            wedTvArray[i].setOnClickListener(this);
            thurTvArray[i].setOnClickListener(this);
            friTvArray[i].setOnClickListener(this);
            satTvArray[i].setOnClickListener(this);
            sunTvArray[i].setOnClickListener(this);

        }
    }

    private void dataInit() {

        mList = new ArrayList<>();
        listInit();
        mAdapter = new CurrentDayAdapter(getActivity(), mList, R.layout.item_current_day);

        current_day_listview.setAdapter(mAdapter);

    }

    private void listInit() {

        for (int i = 0; i < 6; i++) {

            CurrentDayModel model = new CurrentDayModel();
            model.setClass_name("政治");
            model.setEndTime("07:00");
            model.setStartTime("07:30");
            model.setUp_class_place("南楼");
            mList.add(model);

        }
    }

    private void eventInit() {

        appbar_note.setOnClickListener(this);
        appbar_change.setOnClickListener(this);
        appbar_more.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.appbar_note:

                Intent intent = new Intent(getActivity(), NotesActivity.class);
                startActivity(intent);

                break;
            case R.id.appbar_change:

                if (current_day_ll.getVisibility() == View.GONE) {
                    current_day_ll.setVisibility(View.VISIBLE);
                    appbar_center_tv.setText("当天");

                } else {
                    appbar_center_tv.setText("总表");
                    current_day_ll.setVisibility(View.GONE);
                }


                break;
            case R.id.appbar_more:

                startActivity(new Intent(getActivity(), SetActivity.class));

                break;
            //====================================
            case R.id.mon_mor_read_tv_1:

                onWeekClick(0,0);
                break;
            case R.id.mon_mor_read_tv_2:
                onWeekClick(0,1);
                break;
            case R.id.mon_morning_tv_1:
                onWeekClick(0,2);

                break;
            case R.id.mon_morning_tv_2:

                onWeekClick(0,3);
                break;
            case R.id.mon_morning_tv_3:

                onWeekClick(0,4);
                break;
            case R.id.mon_morning_tv_4:

                onWeekClick(0,5);
                break;
            case R.id.mon_afternoon_tv_1:

                onWeekClick(0,6);
                break;
            case R.id.mon_afternoon_tv_2:

                onWeekClick(0,7);
                break;
            case R.id.mon_afternoon_tv_3:

                onWeekClick(0,8);
                break;
            case R.id.mon_evening_tv_1:

                onWeekClick(0,9);
                break;
            case R.id.mon_evening_tv_2:

                onWeekClick(0,10);
                break;
            //===================================
            case R.id.tues_mor_read_tv_1:

                onWeekClick(1,0);
                break;
            case R.id.tues_mor_read_tv_2:
                onWeekClick(1,1);
                break;
            case R.id.tues_morning_tv_1:
                onWeekClick(1,2);

                break;
            case R.id.tues_morning_tv_2:

                onWeekClick(1,3);
                break;
            case R.id.tues_morning_tv_3:

                onWeekClick(1,4);
                break;
            case R.id.tues_morning_tv_4:

                onWeekClick(1,5);
                break;
            case R.id.tues_afternoon_tv_1:

                onWeekClick(1,6);
                break;
            case R.id.tues_afternoon_tv_2:

                onWeekClick(1,7);
                break;
            case R.id.tues_afternoon_tv_3:

                onWeekClick(1,8);
                break;
            case R.id.tues_evening_tv_1:

                onWeekClick(1,9);
                break;
            case R.id.tues_evening_tv_2:

                onWeekClick(1,10);
                break;
            //========================
            case R.id.wed_mor_read_tv_1:

                onWeekClick(2,0);
                break;
            case R.id.wed_mor_read_tv_2:
                onWeekClick(2,1);
                break;
            case R.id.wed_morning_tv_1:
                onWeekClick(2,2);

                break;
            case R.id.wed_morning_tv_2:

                onWeekClick(2,3);
                break;
            case R.id.wed_morning_tv_3:

                onWeekClick(2,4);
                break;
            case R.id.wed_morning_tv_4:

                onWeekClick(2,5);
                break;
            case R.id.wed_afternoon_tv_1:

                onWeekClick(2,6);
                break;
            case R.id.wed_afternoon_tv_2:

                onWeekClick(2,7);
                break;
            case R.id.wed_afternoon_tv_3:

                onWeekClick(2,8);
                break;
            case R.id.wed_evening_tv_1:

                onWeekClick(2,9);
                break;
            case R.id.wed_evening_tv_2:

                onWeekClick(2,10);
                break;

            //========================
            case R.id.thur_mor_read_tv_1:

                onWeekClick(3,0);
                break;
            case R.id.thur_mor_read_tv_2:
                onWeekClick(3,1);
                break;
            case R.id.thur_morning_tv_1:
                onWeekClick(3,2);

                break;
            case R.id.thur_morning_tv_2:

                onWeekClick(3,3);
                break;
            case R.id.thur_morning_tv_3:

                onWeekClick(3,4);
                break;
            case R.id.thur_morning_tv_4:

                onWeekClick(3,5);
                break;
            case R.id.thur_afternoon_tv_1:

                onWeekClick(3,6);
                break;
            case R.id.thur_afternoon_tv_2:

                onWeekClick(3,7);
                break;
            case R.id.thur_afternoon_tv_3:

                onWeekClick(3,8);
                break;
            case R.id.thur_evening_tv_1:

                onWeekClick(3,9);
                break;
            case R.id.thur_evening_tv_2:

                onWeekClick(3,10);
                break;

            //========================
            case R.id.fri_mor_read_tv_1:

                onWeekClick(4,0);
                break;
            case R.id.fri_mor_read_tv_2:
                onWeekClick(4,1);
                break;
            case R.id.fri_morning_tv_1:
                onWeekClick(4,2);

                break;
            case R.id.fri_morning_tv_2:

                onWeekClick(4,3);
                break;
            case R.id.fri_morning_tv_3:

                onWeekClick(4,4);
                break;
            case R.id.fri_morning_tv_4:

                onWeekClick(4,5);
                break;
            case R.id.fri_afternoon_tv_1:

                onWeekClick(4,6);
                break;
            case R.id.fri_afternoon_tv_2:

                onWeekClick(4,7);
                break;
            case R.id.fri_afternoon_tv_3:

                onWeekClick(4,8);
                break;
            case R.id.fri_evening_tv_1:

                onWeekClick(4,9);
                break;
            case R.id.fri_evening_tv_2:

                onWeekClick(4,10);
                break;

            //========================
            case R.id.sat_mor_read_tv_1:

                onWeekClick(5,0);
                break;
            case R.id.sat_mor_read_tv_2:
                onWeekClick(5,1);
                break;
            case R.id.sat_morning_tv_1:
                onWeekClick(5,2);

                break;
            case R.id.sat_morning_tv_2:

                onWeekClick(5,3);
                break;
            case R.id.sat_morning_tv_3:

                onWeekClick(5,4);
                break;
            case R.id.sat_morning_tv_4:

                onWeekClick(5,5);
                break;
            case R.id.sat_afternoon_tv_1:

                onWeekClick(5,6);
                break;
            case R.id.sat_afternoon_tv_2:

                onWeekClick(5,7);
                break;
            case R.id.sat_afternoon_tv_3:

                onWeekClick(5,8);
                break;
            case R.id.sat_evening_tv_1:

                onWeekClick(5,9);
                break;
            case R.id.sat_evening_tv_2:

                onWeekClick(5,10);
                break;

            //========================
            case R.id.sun_mor_read_tv_1:

                onWeekClick(6,0);
                break;
            case R.id.sun_mor_read_tv_2:
                onWeekClick(6,1);
                break;
            case R.id.sun_morning_tv_1:
                onWeekClick(6,2);

                break;
            case R.id.sun_morning_tv_2:

                onWeekClick(6,3);
                break;
            case R.id.sun_morning_tv_3:

                onWeekClick(6,4);
                break;
            case R.id.sun_morning_tv_4:

                onWeekClick(6,5);
                break;
            case R.id.sun_afternoon_tv_1:

                onWeekClick(6,6);
                break;
            case R.id.sun_afternoon_tv_2:

                onWeekClick(6,7);
                break;
            case R.id.sun_afternoon_tv_3:

                onWeekClick(6,8);
                break;
            case R.id.sun_evening_tv_1:

                onWeekClick(6,9);
                break;
            case R.id.sun_evening_tv_2:

                onWeekClick(6,10);
                break;
            
        }
    }

    private void onWeekClick(int groupPosition,int postion){

        Intent intent = new Intent(getActivity(), CourseMessageNoteActivity.class);
        intent.putExtra(Constants.CLASS_NAME_NOTE_POSITION,postion);
        intent.putExtra(Constants.CLASS_NAME_NOTE_ARRAY_POSITION,groupPosition);
        startActivityForResult(intent,Constants.CLASS_NAME_NOTE_REQUEST_CODE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.CLASS_NAME_NOTE_REQUEST_CODE)
        {


        }



    }
}