package com.example.administrator.coursetable.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
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
import com.example.administrator.coursetable.activity.SetTimeActivity;
import com.example.administrator.coursetable.adapter.CurrentDayAdapter;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.CourseTableModel;
import com.example.administrator.coursetable.model.CurrentDayModel;
import com.example.administrator.coursetable.model.UpClassTimeModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/11.
 */

public class TabThreeFragment extends BaseFragment implements View.OnClickListener {


    private AppCompatImageView appbar_note, appbar_change, appbar_more;
    private LinearLayout current_day_ll;
    private TextView week_position;
    private ListView current_day_listview;
    private List<CourseTableModel> mListCurrentDay;
    private CurrentDayAdapter mAdapter;

    private TextView appbar_center_tv;

    private List<CourseTableModel> mListCourseTable;

    private int baseHeigth;

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

    /**
     * 二维数组
     */
    private TextView qrArray[][] = {monTvArray, tuesTvArray, wedTvArray, thurTvArray, friTvArray, satTvArray, sunTvArray};

    /*上课时间设置*/
    private TextView morning_reading_1, morning_reading_2, morning_1, morning_2, morning_3, morning_4, afternoon_1, afternoon_2, afternoon_3, evening_1, evening_2;
    private TextView classTimeTvArray[] = {morning_reading_1, morning_reading_2, morning_1, morning_2, morning_3, morning_4, afternoon_1, afternoon_2, afternoon_3, evening_1, evening_2};
    private int classTimeTvArrayID[] = {R.id.morning_reading_1, R.id.morning_reading_2, R.id.morning_1, R.id.morning_2, R.id.morning_3, R.id.morning_4, R.id.afternoon_1, R.id.afternoon_2, R.id.afternoon_3, R.id.evening_1, R.id.evening_2};


    private MySqliteHelper mySqliteHelper;
    private List<UpClassTimeModel> mListUpClassTime;

    private Random random;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        random = new Random();

    }

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
            classTimeTvArray[i] = (TextView) view.findViewById(classTimeTvArrayID[i]);

            monTvArray[i].setOnClickListener(this);
            tuesTvArray[i].setOnClickListener(this);
            wedTvArray[i].setOnClickListener(this);
            thurTvArray[i].setOnClickListener(this);
            friTvArray[i].setOnClickListener(this);
            satTvArray[i].setOnClickListener(this);
            sunTvArray[i].setOnClickListener(this);
            classTimeTvArray[i].setOnClickListener(this);

        }
    }



    private void dataInit() {

        baseHeigth = getActivity().getResources().getDimensionPixelOffset(R.dimen.base_heigth);
        mySqliteHelper = new MySqliteHelper(getActivity(), Constants.DB_NAME,null,Constants.DB_VERSION);

        mListCurrentDay = new ArrayList<>();
//        listInit();
        mAdapter = new CurrentDayAdapter(getActivity(), mListCurrentDay, R.layout.item_current_day);

        current_day_listview.setAdapter(mAdapter);


    }

    /**设置时间和课时*/
    private void setUpClassTimeData()
    {
        mListUpClassTime = mySqliteHelper.queryUpClassTimeAllData();

        for (int i = 0; i < mListUpClassTime.size(); i++) {

            UpClassTimeModel model = mListUpClassTime.get(i);
            int startHour = model.getStartHour();
            int endHour = model.getEndHour();
            int startMinute = model.getStartMinute();
            int endMinute = model.getEndMinute();

            if(startHour != -1)
            {
                classTimeTvArray[i].setText(formatTime(startHour) + ":"+ formatTime(startMinute)+" - "+formatTime(endHour)+":"+formatTime(endMinute));
            }else
            {
                classTimeTvArray[i].setText("");
            }
        }

    }

    /**
     * @param i
     * @return  格式化时间，不足两位补0
     */
    private String formatTime(int i)
    {

        if(i < 10)
        {
            return "0"+i;
        }

        return ""+i;
    }

    private void listInit() {

        for (int i = 0; i < 6; i++) {

            CurrentDayModel model = new CurrentDayModel();
            model.setClass_name("政治");
            model.setEndTime("07:00");
            model.setStartTime("07:30");
            model.setUp_class_place("南楼");
//            mListCurrentDay.add(model);

        }
    }

    private void eventInit() {

        appbar_note.setOnClickListener(this);
        appbar_change.setOnClickListener(this);
        appbar_more.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        setUpClassTimeData();
        setCourseTableData();

    }

    /**
     *设置课程表数据
     */
    private void setCourseTableData() {

        mListCourseTable = mySqliteHelper.queryCourseTableAllData();

        if (mListCourseTable == null || mListCourseTable.size() == 0)
        {
            addCourseTableData();
            mListCourseTable = mySqliteHelper.queryCourseTableAllData();
        }

        Calendar calendar = Calendar.getInstance();
        int dow = transformWeek(calendar.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < qrArray.length ; i++) {

            for (int j = 0 ; j < qrArray[i].length ; j++)
            {


                CourseTableModel model = getGroupChildPositionData(i,j);
                qrArray[i][j].setText(model.getClassName());
                setTvBg(qrArray[i][j],model.getBgColor());
                if(model.getClassNum()  < 1)
                {
                    qrArray[i][j].setVisibility(View.GONE);
                }else
                {
                    qrArray[i][j].setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) qrArray[i][j].getLayoutParams();
                    params.height = baseHeigth * model.getClassNum();
                    params.gravity = Gravity.CENTER;
                    qrArray[i][j].setLayoutParams(params);
                }

//                if(dow == i)
//                {
//                    if(!"".equals(model.getClassName())){
//
//                        mListCurrentDay.add(model);
//                    }
//
//                }
            }
        }
    }


    private int transformWeek(int  dayOf)
    {
        int week = 0;

        switch (dayOf)
        {
            case 1:
                week = 6;
                break;
            case 2:
                week = 0;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 5;
                break;
            case 7:
                week = 6;
                break;
        }

        return week;
    }


    /*颜色列表*/

    private int ctBgColor[] = {R.color.ct_bg_color_2,R.color.ct_bg_color_3,R.color.ct_bg_color_4,R.color.ct_bg_color_5,R.color.ct_bg_color_6,R.color.ct_bg_color_7,R.color.ct_bg_color_8,R.color.ct_bg_color_9,R.color.ct_bg_color_10};
    private int colorDrawable[] = {R.drawable.shape_choice_color_2,R.drawable.shape_choice_color_3,R.drawable.shape_choice_color_4,R.drawable.shape_choice_color_5,R.drawable.shape_choice_color_6,R.drawable.shape_choice_color_7,R.drawable.shape_choice_color_8,R.drawable.shape_choice_color_9,R.drawable.shape_choice_color_10};

    private void setTvBg(TextView textView, int bgColor) {

        switch (bgColor)
        {
            case Constants.COLOR_DEFAULT:

                textView.setBackgroundResource(R.drawable.shape_line);

                break;
            case Constants.COLOR_RANDOM:

                textView.setBackgroundResource(colorDrawable[random.nextInt(ctBgColor.length)]);

                break;
            case Constants.COLOR_2:
            case Constants.COLOR_3:
            case Constants.COLOR_4:
            case Constants.COLOR_5:
            case Constants.COLOR_6:
            case Constants.COLOR_7:
            case Constants.COLOR_8:
            case Constants.COLOR_9:
            case Constants.COLOR_10:

                textView.setBackgroundResource(colorDrawable[bgColor - 2]);
                break;

        }

    }


    private CourseTableModel getGroupChildPositionData(int groupPosition,int childPosotion)
    {
        for (int i = 0; i < mListCourseTable.size(); i++) {

            CourseTableModel model = mListCourseTable.get(i);

            if(model.getGroupPosition() == groupPosition && model.getChildPosition() == childPosotion)
            {

                return model;
            }
        }

        return null;

    }



    /**
     * 添加默认课程表数据
     */
    private void addCourseTableData() {

        MySqliteHelper mySqliteHelper = new MySqliteHelper(getActivity(),Constants.DB_NAME,null,Constants.DB_VERSION);

        boolean isAdd = (boolean) SharedPreferencesUtils.getParam(getActivity(),Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,false);
        if(!isAdd)
        {
            mySqliteHelper.deleteCourseTableAllData();
            mySqliteHelper.addCourseTableAllData();
            SharedPreferencesUtils.putParam(getActivity(),Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,true);
        }
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

                    getListDataAgain();

                } else {
                    appbar_center_tv.setText("总表");
                    current_day_ll.setVisibility(View.GONE);
                }




                break;
            case R.id.appbar_more:

                startActivity(new Intent(getActivity(), SetActivity.class));

                break;
            //========上课时间================================
            case R.id.morning_reading_1:

                onUpClassTimeClick(0);

                break;
            case R.id.morning_reading_2:
                onUpClassTimeClick(1);
                break;
            case R.id.morning_1:
                onUpClassTimeClick(2);
                break;
            case R.id.morning_2:
                onUpClassTimeClick(3);
                break;
            case R.id.morning_3:
                onUpClassTimeClick(4);
                break;
            case R.id.morning_4:
                onUpClassTimeClick(5);
                break;
            case R.id.afternoon_1:
                onUpClassTimeClick(6);
                break;
            case R.id.afternoon_2:
                onUpClassTimeClick(7);
                break;
            case R.id.afternoon_3:
                onUpClassTimeClick(8);
                break;
            case R.id.evening_1:
                onUpClassTimeClick(9);
                break;
            case R.id.evening_2:
                onUpClassTimeClick(10);
                break;

            //====================================
            case R.id.mon_mor_read_tv_1:

                onWeekClick(0, 0);
                break;
            case R.id.mon_mor_read_tv_2:
                onWeekClick(0, 1);
                break;
            case R.id.mon_morning_tv_1:
                onWeekClick(0, 2);

                break;
            case R.id.mon_morning_tv_2:

                onWeekClick(0, 3);
                break;
            case R.id.mon_morning_tv_3:

                onWeekClick(0, 4);
                break;
            case R.id.mon_morning_tv_4:

                onWeekClick(0, 5);
                break;
            case R.id.mon_afternoon_tv_1:

                onWeekClick(0, 6);
                break;
            case R.id.mon_afternoon_tv_2:

                onWeekClick(0, 7);
                break;
            case R.id.mon_afternoon_tv_3:

                onWeekClick(0, 8);
                break;
            case R.id.mon_evening_tv_1:

                onWeekClick(0, 9);
                break;
            case R.id.mon_evening_tv_2:

                onWeekClick(0, 10);
                break;
            //===================================
            case R.id.tues_mor_read_tv_1:

                onWeekClick(1, 0);
                break;
            case R.id.tues_mor_read_tv_2:
                onWeekClick(1, 1);
                break;
            case R.id.tues_morning_tv_1:
                onWeekClick(1, 2);

                break;
            case R.id.tues_morning_tv_2:

                onWeekClick(1, 3);
                break;
            case R.id.tues_morning_tv_3:

                onWeekClick(1, 4);
                break;
            case R.id.tues_morning_tv_4:

                onWeekClick(1, 5);
                break;
            case R.id.tues_afternoon_tv_1:

                onWeekClick(1, 6);
                break;
            case R.id.tues_afternoon_tv_2:

                onWeekClick(1, 7);
                break;
            case R.id.tues_afternoon_tv_3:

                onWeekClick(1, 8);
                break;
            case R.id.tues_evening_tv_1:

                onWeekClick(1, 9);
                break;
            case R.id.tues_evening_tv_2:

                onWeekClick(1, 10);
                break;
            //========================
            case R.id.wed_mor_read_tv_1:

                onWeekClick(2, 0);
                break;
            case R.id.wed_mor_read_tv_2:
                onWeekClick(2, 1);
                break;
            case R.id.wed_morning_tv_1:
                onWeekClick(2, 2);

                break;
            case R.id.wed_morning_tv_2:

                onWeekClick(2, 3);
                break;
            case R.id.wed_morning_tv_3:

                onWeekClick(2, 4);
                break;
            case R.id.wed_morning_tv_4:

                onWeekClick(2, 5);
                break;
            case R.id.wed_afternoon_tv_1:

                onWeekClick(2, 6);
                break;
            case R.id.wed_afternoon_tv_2:

                onWeekClick(2, 7);
                break;
            case R.id.wed_afternoon_tv_3:

                onWeekClick(2, 8);
                break;
            case R.id.wed_evening_tv_1:

                onWeekClick(2, 9);
                break;
            case R.id.wed_evening_tv_2:

                onWeekClick(2, 10);
                break;

            //========================
            case R.id.thur_mor_read_tv_1:

                onWeekClick(3, 0);
                break;
            case R.id.thur_mor_read_tv_2:
                onWeekClick(3, 1);
                break;
            case R.id.thur_morning_tv_1:
                onWeekClick(3, 2);

                break;
            case R.id.thur_morning_tv_2:

                onWeekClick(3, 3);
                break;
            case R.id.thur_morning_tv_3:

                onWeekClick(3, 4);
                break;
            case R.id.thur_morning_tv_4:

                onWeekClick(3, 5);
                break;
            case R.id.thur_afternoon_tv_1:

                onWeekClick(3, 6);
                break;
            case R.id.thur_afternoon_tv_2:

                onWeekClick(3, 7);
                break;
            case R.id.thur_afternoon_tv_3:

                onWeekClick(3, 8);
                break;
            case R.id.thur_evening_tv_1:

                onWeekClick(3, 9);
                break;
            case R.id.thur_evening_tv_2:

                onWeekClick(3, 10);
                break;

            //========================
            case R.id.fri_mor_read_tv_1:

                onWeekClick(4, 0);
                break;
            case R.id.fri_mor_read_tv_2:
                onWeekClick(4, 1);
                break;
            case R.id.fri_morning_tv_1:
                onWeekClick(4, 2);

                break;
            case R.id.fri_morning_tv_2:

                onWeekClick(4, 3);
                break;
            case R.id.fri_morning_tv_3:

                onWeekClick(4, 4);
                break;
            case R.id.fri_morning_tv_4:

                onWeekClick(4, 5);
                break;
            case R.id.fri_afternoon_tv_1:

                onWeekClick(4, 6);
                break;
            case R.id.fri_afternoon_tv_2:

                onWeekClick(4, 7);
                break;
            case R.id.fri_afternoon_tv_3:

                onWeekClick(4, 8);
                break;
            case R.id.fri_evening_tv_1:

                onWeekClick(4, 9);
                break;
            case R.id.fri_evening_tv_2:

                onWeekClick(4, 10);
                break;

            //========================
            case R.id.sat_mor_read_tv_1:

                onWeekClick(5, 0);
                break;
            case R.id.sat_mor_read_tv_2:
                onWeekClick(5, 1);
                break;
            case R.id.sat_morning_tv_1:
                onWeekClick(5, 2);

                break;
            case R.id.sat_morning_tv_2:

                onWeekClick(5, 3);
                break;
            case R.id.sat_morning_tv_3:

                onWeekClick(5, 4);
                break;
            case R.id.sat_morning_tv_4:

                onWeekClick(5, 5);
                break;
            case R.id.sat_afternoon_tv_1:

                onWeekClick(5, 6);
                break;
            case R.id.sat_afternoon_tv_2:

                onWeekClick(5, 7);
                break;
            case R.id.sat_afternoon_tv_3:

                onWeekClick(5, 8);
                break;
            case R.id.sat_evening_tv_1:

                onWeekClick(5, 9);
                break;
            case R.id.sat_evening_tv_2:

                onWeekClick(5, 10);
                break;

            //========================
            case R.id.sun_mor_read_tv_1:

                onWeekClick(6, 0);
                break;
            case R.id.sun_mor_read_tv_2:
                onWeekClick(6, 1);
                break;
            case R.id.sun_morning_tv_1:
                onWeekClick(6, 2);

                break;
            case R.id.sun_morning_tv_2:

                onWeekClick(6, 3);
                break;
            case R.id.sun_morning_tv_3:

                onWeekClick(6, 4);
                break;
            case R.id.sun_morning_tv_4:

                onWeekClick(6, 5);
                break;
            case R.id.sun_afternoon_tv_1:

                onWeekClick(6, 6);
                break;
            case R.id.sun_afternoon_tv_2:

                onWeekClick(6, 7);
                break;
            case R.id.sun_afternoon_tv_3:

                onWeekClick(6, 8);
                break;
            case R.id.sun_evening_tv_1:

                onWeekClick(6, 9);
                break;
            case R.id.sun_evening_tv_2:

                onWeekClick(6, 10);
                break;

        }

    }

    /**
     * 获取当天课程数据
     */
    private void getListDataAgain() {

        int dayOfWeek = transformWeek(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

        mListCurrentDay = mySqliteHelper.queryCurrentDayData(dayOfWeek);

        mAdapter.setList(mListCurrentDay);
    }

    private void onWeekClick(int groupPosition, int postion) {

        Intent intent = new Intent(getActivity(), CourseMessageNoteActivity.class);
        intent.putExtra(Constants.CLASS_NAME_NOTE_CHILD_POSITION, postion);
        intent.putExtra(Constants.CLASS_NAME_NOTE_GROUP_POSITION, groupPosition);
        startActivityForResult(intent, Constants.CLASS_NAME_NOTE_REQUEST_CODE);

    }

    private void onUpClassTimeClick(int position) {

        Intent intent = new Intent(getActivity(), SetTimeActivity.class);
        intent.putExtra(Constants.UP_CLASS_TIME_ARRAY_POSITION, position);
        startActivityForResult(intent, Constants.CLASS_NAME_NOTE_REQUEST_CODE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.CLASS_NAME_NOTE_REQUEST_CODE && requestCode == Activity.RESULT_OK) {


        }else if(requestCode == Constants.UP_CLASS_TIME_POSITION && requestCode == Activity.RESULT_OK)
        {


        }
    }
}