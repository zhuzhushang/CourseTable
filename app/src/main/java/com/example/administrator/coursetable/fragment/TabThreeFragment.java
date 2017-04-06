package com.example.administrator.coursetable.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
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

    //还有多久时间上课
    private AppCompatTextView how_long_up_class;

    //课程表控件
//    private TextView mon_mor_read_tv_1, mon_mor_read_tv_2, mon_morning_tv_1, mon_morning_tv_2, mon_morning_tv_3, mon_morning_tv_4, mon_afternoon_tv_1, mon_afternoon_tv_2, mon_afternoon_tv_3, mon_evening_tv_1, mon_evening_tv_2;
//    private TextView tues_mor_read_tv_1, tues_mor_read_tv_2, tues_morning_tv_1, tues_morning_tv_2, tues_morning_tv_3, tues_morning_tv_4, tues_afternoon_tv_1, tues_afternoon_tv_2, tues_afternoon_tv_3, tues_evening_tv_1, tues_evening_tv_2;
//    private TextView wed_mor_read_tv_1, wed_mor_read_tv_2, wed_morning_tv_1, wed_morning_tv_2, wed_morning_tv_3, wed_morning_tv_4, wed_afternoon_tv_1, wed_afternoon_tv_2, wed_afternoon_tv_3, wed_evening_tv_1, wed_evening_tv_2;
//    private TextView thur_mor_read_tv_1, thur_mor_read_tv_2, thur_morning_tv_1, thur_morning_tv_2, thur_morning_tv_3, thur_morning_tv_4, thur_afternoon_tv_1, thur_afternoon_tv_2, thur_afternoon_tv_3, thur_evening_tv_1, thur_evening_tv_2;
//    private TextView fri_mor_read_tv_1, fri_mor_read_tv_2, fri_morning_tv_1, fri_morning_tv_2, fri_morning_tv_3, fri_morning_tv_4, fri_afternoon_tv_1, fri_afternoon_tv_2, fri_afternoon_tv_3, fri_evening_tv_1, fri_evening_tv_2;
//    private TextView sat_mor_read_tv_1, sat_mor_read_tv_2, sat_morning_tv_1, sat_morning_tv_2, sat_morning_tv_3, sat_morning_tv_4, sat_afternoon_tv_1, sat_afternoon_tv_2, sat_afternoon_tv_3, sat_evening_tv_1, sat_evening_tv_2;
//    private TextView sun_mor_read_tv_1, sun_mor_read_tv_2, sun_morning_tv_1, sun_morning_tv_2, sun_morning_tv_3, sun_morning_tv_4, sun_afternoon_tv_1, sun_afternoon_tv_2, sun_afternoon_tv_3, sun_evening_tv_1, sun_evening_tv_2;
//
//    private TextView monTvArray[] = {mon_mor_read_tv_1, mon_mor_read_tv_2, mon_morning_tv_1, mon_morning_tv_2, mon_morning_tv_3, mon_morning_tv_4, mon_afternoon_tv_1, mon_afternoon_tv_2, mon_afternoon_tv_3, mon_evening_tv_1, mon_evening_tv_2};
//    private int monTvArrayID[] = {R.id.mon_mor_read_tv_1, R.id.mon_mor_read_tv_2, R.id.mon_morning_tv_1, R.id.mon_morning_tv_2, R.id.mon_morning_tv_3, R.id.mon_morning_tv_4, R.id.mon_afternoon_tv_1, R.id.mon_afternoon_tv_2, R.id.mon_afternoon_tv_3, R.id.mon_evening_tv_1, R.id.mon_evening_tv_2};
//
//    private TextView tuesTvArray[] = {tues_mor_read_tv_1, tues_mor_read_tv_2, tues_morning_tv_1, tues_morning_tv_2, tues_morning_tv_3, tues_morning_tv_4, tues_afternoon_tv_1, tues_afternoon_tv_2, tues_afternoon_tv_3, tues_evening_tv_1, tues_evening_tv_2};
//    private int tuesTvArrayID[] = {R.id.tues_mor_read_tv_1, R.id.tues_mor_read_tv_2, R.id.tues_morning_tv_1, R.id.tues_morning_tv_2, R.id.tues_morning_tv_3, R.id.tues_morning_tv_4, R.id.tues_afternoon_tv_1, R.id.tues_afternoon_tv_2, R.id.tues_afternoon_tv_3, R.id.tues_evening_tv_1, R.id.tues_evening_tv_2};
//
//
//    private TextView wedTvArray[] = {wed_mor_read_tv_1, wed_mor_read_tv_2, wed_morning_tv_1, wed_morning_tv_2, wed_morning_tv_3, wed_morning_tv_4, wed_afternoon_tv_1, wed_afternoon_tv_2, wed_afternoon_tv_3, wed_evening_tv_1, wed_evening_tv_2};
//    private int wedTvArrayID[] = {R.id.wed_mor_read_tv_1, R.id.wed_mor_read_tv_2, R.id.wed_morning_tv_1, R.id.wed_morning_tv_2, R.id.wed_morning_tv_3, R.id.wed_morning_tv_4, R.id.wed_afternoon_tv_1, R.id.wed_afternoon_tv_2, R.id.wed_afternoon_tv_3, R.id.wed_evening_tv_1, R.id.wed_evening_tv_2};
//
//
//    private TextView thurTvArray[] = {thur_mor_read_tv_1, thur_mor_read_tv_2, thur_morning_tv_1, thur_morning_tv_2, thur_morning_tv_3, thur_morning_tv_4, thur_afternoon_tv_1, thur_afternoon_tv_2, thur_afternoon_tv_3, thur_evening_tv_1, thur_evening_tv_2};
//    private int thurTvArrayID[] = {R.id.thur_mor_read_tv_1, R.id.thur_mor_read_tv_2, R.id.thur_morning_tv_1, R.id.thur_morning_tv_2, R.id.thur_morning_tv_3, R.id.thur_morning_tv_4, R.id.thur_afternoon_tv_1, R.id.thur_afternoon_tv_2, R.id.thur_afternoon_tv_3, R.id.thur_evening_tv_1, R.id.thur_evening_tv_2};
//
//    private TextView friTvArray[] = {fri_mor_read_tv_1, fri_mor_read_tv_2, fri_morning_tv_1, fri_morning_tv_2, fri_morning_tv_3, fri_morning_tv_4, fri_afternoon_tv_1, fri_afternoon_tv_2, fri_afternoon_tv_3, fri_evening_tv_1, fri_evening_tv_2};
//    private int friTvArrayID[] = {R.id.fri_mor_read_tv_1, R.id.fri_mor_read_tv_2, R.id.fri_morning_tv_1, R.id.fri_morning_tv_2, R.id.fri_morning_tv_3, R.id.fri_morning_tv_4, R.id.fri_afternoon_tv_1, R.id.fri_afternoon_tv_2, R.id.fri_afternoon_tv_3, R.id.fri_evening_tv_1, R.id.fri_evening_tv_2};
//
//
//    private TextView satTvArray[] = {sat_mor_read_tv_1, sat_mor_read_tv_2, sat_morning_tv_1, sat_morning_tv_2, sat_morning_tv_3, sat_morning_tv_4, sat_afternoon_tv_1, sat_afternoon_tv_2, sat_afternoon_tv_3, sat_evening_tv_1, sat_evening_tv_2};
//    private int satTvArrayID[] = {R.id.sat_mor_read_tv_1, R.id.sat_mor_read_tv_2, R.id.sat_morning_tv_1, R.id.sat_morning_tv_2, R.id.sat_morning_tv_3, R.id.sat_morning_tv_4, R.id.sat_afternoon_tv_1, R.id.sat_afternoon_tv_2, R.id.sat_afternoon_tv_3, R.id.sat_evening_tv_1, R.id.sat_evening_tv_2};
//
//    private TextView sunTvArray[] = {sun_mor_read_tv_1, sun_mor_read_tv_2, sun_morning_tv_1, sun_morning_tv_2, sun_morning_tv_3, sun_morning_tv_4, sun_afternoon_tv_1, sun_afternoon_tv_2, sun_afternoon_tv_3, sun_evening_tv_1, sun_evening_tv_2};
//    private int sunTvArrayID[] = {R.id.sun_mor_read_tv_1, R.id.sun_mor_read_tv_2, R.id.sun_morning_tv_1, R.id.sun_morning_tv_2, R.id.sun_morning_tv_3, R.id.sun_morning_tv_4, R.id.sun_afternoon_tv_1, R.id.sun_afternoon_tv_2, R.id.sun_afternoon_tv_3, R.id.sun_evening_tv_1, R.id.sun_evening_tv_2};


    /*星期一的textview*/
    private TextView monTvArray[];
    private int monTvArrayID[] = {R.id.mon_mor_read_tv_1, R.id.mon_mor_read_tv_2, R.id.mon_mor_read_tv_3, R.id.mon_morning_tv_1, R.id.mon_morning_tv_2, R.id.mon_morning_tv_3, R.id.mon_morning_tv_4, R.id.mon_morning_tv_5, R.id.mon_afternoon_tv_1, R.id.mon_afternoon_tv_2, R.id.mon_afternoon_tv_3, R.id.mon_afternoon_tv_4, R.id.mon_afternoon_tv_5, R.id.mon_evening_tv_1, R.id.mon_evening_tv_2, R.id.mon_evening_tv_3};

    /*星期二的textview*/
    private TextView tuesTvArray[];
    private int tuesTvArrayID[] = {R.id.tues_mor_read_tv_1, R.id.tues_mor_read_tv_2, R.id.tues_mor_read_tv_3, R.id.tues_morning_tv_1, R.id.tues_morning_tv_2, R.id.tues_morning_tv_3, R.id.tues_morning_tv_4, R.id.tues_morning_tv_5, R.id.tues_afternoon_tv_1, R.id.tues_afternoon_tv_2, R.id.tues_afternoon_tv_3, R.id.tues_afternoon_tv_4, R.id.tues_afternoon_tv_5, R.id.tues_evening_tv_1, R.id.tues_evening_tv_2, R.id.tues_evening_tv_3};

    /*星期三的textview*/
    private TextView wedTvArray[];
    private int wedTvArrayID[] = {R.id.wed_mor_read_tv_1, R.id.wed_mor_read_tv_2, R.id.wed_mor_read_tv_3, R.id.wed_morning_tv_1, R.id.wed_morning_tv_2, R.id.wed_morning_tv_3, R.id.wed_morning_tv_4, R.id.wed_morning_tv_5, R.id.wed_afternoon_tv_1, R.id.wed_afternoon_tv_2, R.id.wed_afternoon_tv_3, R.id.wed_afternoon_tv_4, R.id.wed_afternoon_tv_5, R.id.wed_evening_tv_1, R.id.wed_evening_tv_2, R.id.wed_evening_tv_3};

    /*星期四的textview*/
    private TextView thurTvArray[];
    private int thurTvArrayID[] = {R.id.thur_mor_read_tv_1, R.id.thur_mor_read_tv_2, R.id.thur_mor_read_tv_3, R.id.thur_morning_tv_1, R.id.thur_morning_tv_2, R.id.thur_morning_tv_3, R.id.thur_morning_tv_4, R.id.thur_morning_tv_5, R.id.thur_afternoon_tv_1, R.id.thur_afternoon_tv_2, R.id.thur_afternoon_tv_3, R.id.thur_afternoon_tv_4, R.id.thur_afternoon_tv_5, R.id.thur_evening_tv_1, R.id.thur_evening_tv_2, R.id.thur_evening_tv_3};

    /*星期五的textview*/
    private TextView friTvArray[];
    private int friTvArrayID[] = {R.id.fri_mor_read_tv_1, R.id.fri_mor_read_tv_2, R.id.fri_mor_read_tv_3, R.id.fri_morning_tv_1, R.id.fri_morning_tv_2, R.id.fri_morning_tv_3, R.id.fri_morning_tv_4, R.id.fri_morning_tv_5, R.id.fri_afternoon_tv_1, R.id.fri_afternoon_tv_2, R.id.fri_afternoon_tv_3, R.id.fri_afternoon_tv_4, R.id.fri_afternoon_tv_5, R.id.fri_evening_tv_1, R.id.fri_evening_tv_2, R.id.fri_evening_tv_3};

    /*星期六的textview*/
    private TextView satTvArray[];
    private int satTvArrayID[] = {R.id.sat_mor_read_tv_1, R.id.sat_mor_read_tv_2, R.id.sat_mor_read_tv_3, R.id.sat_morning_tv_1, R.id.sat_morning_tv_2, R.id.sat_morning_tv_3, R.id.sat_morning_tv_4, R.id.sat_morning_tv_5, R.id.sat_afternoon_tv_1, R.id.sat_afternoon_tv_2, R.id.sat_afternoon_tv_3, R.id.sat_afternoon_tv_4, R.id.sat_afternoon_tv_5, R.id.sat_evening_tv_1, R.id.sat_evening_tv_2, R.id.sat_evening_tv_3};

    /*星期天的textview*/
    private TextView sunTvArray[];
    private int sunTvArrayID[] = {R.id.sun_mor_read_tv_1, R.id.sun_mor_read_tv_2, R.id.sun_mor_read_tv_3, R.id.sun_morning_tv_1, R.id.sun_morning_tv_2, R.id.sun_morning_tv_3, R.id.sun_morning_tv_4, R.id.sun_morning_tv_5, R.id.sun_afternoon_tv_1, R.id.sun_afternoon_tv_2, R.id.sun_afternoon_tv_3, R.id.sun_afternoon_tv_4, R.id.sun_afternoon_tv_5, R.id.sun_evening_tv_1, R.id.sun_evening_tv_2, R.id.sun_evening_tv_3};


    /**
     * 二维数组
     */
    private TextView qrArray[][];

    /*上课时间设置*/
   private TextView classTimeTvArray[];
    private int classTimeTvArrayID[] = {R.id.morning_reading_1, R.id.morning_reading_2, R.id.morning_reading_3, R.id.morning_1, R.id.morning_2, R.id.morning_3, R.id.morning_4, R.id.morning_5, R.id.afternoon_1, R.id.afternoon_2, R.id.afternoon_3, R.id.afternoon_4, R.id.afternoon_5, R.id.evening_1, R.id.evening_2, R.id.evening_3};


    private MySqliteHelper mySqliteHelper;
    private List<UpClassTimeModel> mListUpClassTime;

    private Random random;

    /**
     * 星期数组
     */
    private int xqLlID[] = {R.id.xq_1_ll, R.id.xq_2_ll, R.id.xq_3_ll, R.id.xq_4_ll, R.id.xq_5_ll, R.id.xq_6_ll, R.id.xq_7_ll};
    private LinearLayout xqLl[] = new LinearLayout[xqLlID.length];


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

        //初始化Textview
        monTvArray = new TextView[monTvArrayID.length];
        tuesTvArray = new TextView[tuesTvArrayID.length];
        wedTvArray = new TextView[wedTvArrayID.length];
        thurTvArray = new TextView[thurTvArrayID.length];
        friTvArray = new TextView[friTvArrayID.length];
        satTvArray = new TextView[satTvArrayID.length];
        sunTvArray = new TextView[sunTvArrayID.length];
        classTimeTvArray = new TextView[classTimeTvArrayID.length];

        qrArray = new TextView[][]{monTvArray, tuesTvArray, wedTvArray, thurTvArray, friTvArray, satTvArray, sunTvArray};

        for (int i = 0; i < xqLlID.length; i++) {

            xqLl[i] = (LinearLayout) view.findViewById(xqLlID[i]);
        }

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

            monTvArray[i].setOnClickListener(courseOnClick);
            tuesTvArray[i].setOnClickListener(courseOnClick);
            wedTvArray[i].setOnClickListener(courseOnClick);
            thurTvArray[i].setOnClickListener(courseOnClick);
            friTvArray[i].setOnClickListener(courseOnClick);
            satTvArray[i].setOnClickListener(courseOnClick);
            sunTvArray[i].setOnClickListener(courseOnClick);
            classTimeTvArray[i].setOnClickListener(upClassClick);

        }

        how_long_up_class = (AppCompatTextView) view.findViewById(R.id.how_long_up_class);
    }


    private void dataInit() {

        baseHeigth = getActivity().getResources().getDimensionPixelOffset(R.dimen.base_heigth);
        mySqliteHelper = new MySqliteHelper(getActivity(), Constants.DB_NAME, null, Constants.DB_VERSION);

        mListCurrentDay = new ArrayList<>();
//        listInit();
        mAdapter = new CurrentDayAdapter(getActivity(), mListCurrentDay, R.layout.item_current_day);

        current_day_listview.setAdapter(mAdapter);


    }

    /**
     * 设置时间和课时
     */
    private void setUpClassTimeData() {
        mListUpClassTime = mySqliteHelper.queryUpClassTimeAllData();

        for (int i = 0; i < mListUpClassTime.size(); i++) {

            UpClassTimeModel model = mListUpClassTime.get(i);
            int startHour = model.getStartHour();
            int endHour = model.getEndHour();
            int startMinute = model.getStartMinute();
            int endMinute = model.getEndMinute();

            if (startHour != -1) {
                classTimeTvArray[i].setText(formatTime(startHour) + ":" + formatTime(startMinute) + " - " + formatTime(endHour) + ":" + formatTime(endMinute));
            } else {
                classTimeTvArray[i].setText("");
            }
        }

    }

    /**
     * @param i
     * @return 格式化时间，不足两位补0
     */
    private String formatTime(int i) {

        if (i < 10) {
            return "0" + i;
        }

        return "" + i;
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
        hideWeek();
        setUpClassLong();


    }

    /**
     * 设置还有多久上课
     */
    private void setUpClassLong() {

        mListCourseTable = mySqliteHelper.queryCourseTableAllData();
        List<UpClassTimeModel> list = mySqliteHelper.queryUpClassTimeAllDataById();

        if (mListCourseTable == null || mListCourseTable.size() == 0) {
            addCourseTableData();
            mListCourseTable = mySqliteHelper.queryCourseTableAllData();
        }

        Calendar calendar = Calendar.getInstance();
        int dow = transformWeek(calendar.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < qrArray.length; i++) {

            for (int j = 0; j < qrArray[i].length; j++) {


                CourseTableModel model = getGroupChildPositionData(i, j);
                qrArray[i][j].setText(model.getClassName());
                setTvBg(qrArray[i][j], model.getBgColor());
                if (model.getClassNum() < 1) {
                    qrArray[i][j].setVisibility(View.GONE);
                } else {
                    qrArray[i][j].setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) qrArray[i][j].getLayoutParams();
                    params.height = baseHeigth * model.getClassNum();
                    params.gravity = Gravity.CENTER;
                    qrArray[i][j].setLayoutParams(params);

                    //判断是否是今天，如果是，就设置最近一堂课距离的时间
                    if (dow == i) {
                        //得到这节课的上课时间
                        for (int k = 0; k < list.size(); k++) {

                            UpClassTimeModel upModel = list.get(k);
                            if (model.getClassIndex() == upModel.getClassIndex()) {
                                long howlong = getUpClassTime(upModel.getStartHour(), upModel.getStartMinute());
                                if (howlong > System.currentTimeMillis()) {
                                    String uplong = millis2FitTimeSpan(howlong - System.currentTimeMillis(), 3);
                                    how_long_up_class.setText("距离上课还有" + uplong + "哦");
                                    return;

                                }
                            }
                        }

                        how_long_up_class.setText("今日无课");

                    }

                }
            }
        }


    }


    /**
     * 毫秒时间戳转合适时间长度
     *
     * @param millis    毫秒时间戳
     *                  <p>小于等于0，返回null</p>
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适时间长度
     */
    public static String millis2FitTimeSpan(long millis, int precision) {
        if (millis <= 0 || precision <= 0) return null;
        StringBuilder sb = new StringBuilder();
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        precision = Math.min(precision, 5);
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }


    private void hideWeek() {

        String data[] = getHideWeekData();
        for (int i = 0; i < data.length; i++) {

            if ("1".equals(data[i])) {
                xqLl[i].setVisibility(View.VISIBLE);
            } else {
                xqLl[i].setVisibility(View.GONE);
            }
        }


    }

    /*默认不隐藏*/
    private String ww = "1,1,1,1,1,1,1";

    private String[] getHideWeekData() {
        String str = (String) SharedPreferencesUtils.getParam(getActivity(), Constants.HIDE_WEEK_DATA, ww);

        return str.split(",");
    }

    /**
     * 设置课程表数据
     */
    private void setCourseTableData() {

        mListCourseTable = mySqliteHelper.queryCourseTableAllData();
//        List<UpClassTimeModel> list = mySqliteHelper.queryUpClassTimeAllDataById();

        if (mListCourseTable == null || mListCourseTable.size() == 0) {
            addCourseTableData();
            mListCourseTable = mySqliteHelper.queryCourseTableAllData();
        }

        Calendar calendar = Calendar.getInstance();
        int dow = transformWeek(calendar.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < qrArray.length; i++) {

            for (int j = 0; j < qrArray[i].length; j++) {


                CourseTableModel model = getGroupChildPositionData(i, j);
                qrArray[i][j].setText(model.getClassName());
                setTvBg(qrArray[i][j], model.getBgColor());
                if (model.getClassNum() < 1) {
                    qrArray[i][j].setVisibility(View.GONE);
                } else {
                    qrArray[i][j].setVisibility(View.VISIBLE);

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) qrArray[i][j].getLayoutParams();
                    params.height = baseHeigth * model.getClassNum();
                    params.gravity = Gravity.CENTER;
                    qrArray[i][j].setLayoutParams(params);

                }
            }
        }
    }


    /**
     * 得到这节课的世界
     */
    private long getUpClassTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTimeInMillis();
    }


    /**
     * 这个方法是由于 我们这里第一天是星期一   手机的第一天是星期天
     * <p>
     * 这里转化一下
     * <p>
     * 把手机的转化成我们自己的
     */
    private int transformWeek(int phoneDayOfWeek) {
        int week = 1;

        switch (phoneDayOfWeek) {
            case 1:
                week = 7;
                break;
            case 2:
                week = 1;
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

    private int ctBgColor[] = {R.color.ct_bg_color_2, R.color.ct_bg_color_3, R.color.ct_bg_color_4, R.color.ct_bg_color_5, R.color.ct_bg_color_6, R.color.ct_bg_color_7, R.color.ct_bg_color_8, R.color.ct_bg_color_9, R.color.ct_bg_color_10};
    private int colorDrawable[] = {R.drawable.shape_choice_color_2, R.drawable.shape_choice_color_3, R.drawable.shape_choice_color_4, R.drawable.shape_choice_color_5, R.drawable.shape_choice_color_6, R.drawable.shape_choice_color_7, R.drawable.shape_choice_color_8, R.drawable.shape_choice_color_9, R.drawable.shape_choice_color_10};

    private void setTvBg(TextView textView, int bgColor) {

        switch (bgColor) {
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


    private CourseTableModel getGroupChildPositionData(int groupPosition, int childPosotion) {
        for (int i = 0; i < mListCourseTable.size(); i++) {

            CourseTableModel model = mListCourseTable.get(i);

            if (model.getGroupPosition() == groupPosition && model.getChildPosition() == childPosotion) {

                return model;
            }
        }

        return null;

    }


    /**
     * 添加默认课程表数据
     */
    private void addCourseTableData() {

        MySqliteHelper mySqliteHelper = new MySqliteHelper(getActivity(), Constants.DB_NAME, null, Constants.DB_VERSION);

        boolean isAdd = (boolean) SharedPreferencesUtils.getParam(getActivity(), Constants.IS_ONCE_ADD_COURSE_TABLE_DATA, false);
        if (!isAdd) {
            mySqliteHelper.deleteCourseTableAllData();
            mySqliteHelper.addCourseTableAllData();
            SharedPreferencesUtils.putParam(getActivity(), Constants.IS_ONCE_ADD_COURSE_TABLE_DATA, true);
        }
    }


    /**给课程设置点击事件*/
    View.OnClickListener courseOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            for (int i = 0; i < qrArray.length; i++) {

                for (int j = 0; j < qrArray[i].length; j++) {

                    if(v.getId() == qrArray[i][j].getId())
                    {
                        onWeekClick(i, j);
                    }
                }
            }
        }
    };

    /**给上课事件设置点击事件*/
    View.OnClickListener upClassClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            for (int i = 0; i < classTimeTvArray.length; i++) {

                if(v.getId() == classTimeTvArray[i].getId())
                {

                    onUpClassTimeClick(i);
                }

            }
        }
    };


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
        }
    }

    private String WEEK_ARRAY[] = {"日", "一", "二", "三", "四", "五", "六"};

    /**
     * 获取当天课程数据
     */
    private void getListDataAgain() {


        int dow = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int dayOfWeek = transformWeek(dow);
        week_position.setText("星期" + WEEK_ARRAY[dow - 1]);


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


        } else if (requestCode == Constants.UP_CLASS_TIME_POSITION && requestCode == Activity.RESULT_OK) {


        }
    }
}