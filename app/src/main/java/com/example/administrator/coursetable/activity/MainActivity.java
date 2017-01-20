package com.example.administrator.coursetable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.fragment.TabFiveFragment;
import com.example.administrator.coursetable.fragment.TabFourFragment;
import com.example.administrator.coursetable.fragment.TabOneFragment;
import com.example.administrator.coursetable.fragment.TabThreeFragment;
import com.example.administrator.coursetable.fragment.TabTwoFragment;
import com.example.administrator.coursetable.model.UpClassTimeModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{


    private TabOneFragment mTabOneFragment;
    private TabTwoFragment mTabTwoFragment;
    private TabThreeFragment mTabThreeFragment;
    private TabFourFragment mTabFourFragment;
    private TabFiveFragment mTabFiveFragment;

    private Fragment fragmentArray[];

    private RadioGroup hp_rg;

    /**当前选中的id*/
    private int currentCheckId = R.id.homepage_tab_3;
    /**当前选中的下标*/
    private int currentIndex = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        hp_rg = (RadioGroup) findViewById(R.id.hp_rg);

    }

    private void dataInit() {

        /*第一步就添加上课时间数据*/
        addUpClassTimeData();
        /*添加课程表数据*/
        addCourseTableData();

        mTabOneFragment = new TabOneFragment();
        mTabTwoFragment = new TabTwoFragment();
        mTabThreeFragment = new TabThreeFragment();
        mTabFourFragment = new TabFourFragment();
        mTabFiveFragment = new TabFiveFragment();

        fragmentArray = new Fragment[]{mTabOneFragment,mTabTwoFragment,mTabThreeFragment,mTabFourFragment,mTabFiveFragment};

        FragmentTransaction transAction = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < fragmentArray.length; i++) {

            transAction.add(R.id.comtainer,fragmentArray[i]);
            transAction.hide(fragmentArray[i]);
        }

        transAction.show(fragmentArray[2]);
        transAction.commit();

    }



    private void eventInit() {


        hp_rg.setOnCheckedChangeListener(this);


    }
    FragmentTransaction transAction;
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if(currentCheckId == i)
        {
            return;
        }

         transAction = getSupportFragmentManager().beginTransaction();

        int index = 2;

        switch (i)
        {
            case R.id.homepage_tab_1:

                index = 0;

                break;
            case R.id.homepage_tab_2:

                index = 1;
                break;
            case R.id.homepage_tab_3:
                index = 2;

                break;
            case R.id.homepage_tab_4:

                index = 3;

                break;
            case R.id.homepage_tab_5:
                Intent intent = new Intent();
                intent.setAction("com.eee");
                  //发送广播
                sendBroadcast(intent);

                index = 4;
                break;
        }


        transAction.show(fragmentArray[index]).hide(fragmentArray[currentIndex]).commit();
        currentIndex = index;
        currentCheckId = i;

    }


    private String startTimeArray[] = {"07:30","","08:10","09:05","10:15","11:10","14:30","15:25","16:20","17:25",""};
    private String endTimeArray[] = {"08:00","","08:55","09:50","11:00","11:55","15:15","16:10","17:05","18:10",""};


    private int startHourArray[] = {7,-1,8,9,10,11,14,15,16,17,-1};
    private int startMinuteArray[] = {30,-1,10,5,15,10,30,25,20,25,-1};
    private int endHourArray[] = {8,-1,8,9,11,11,15,16,17,18,-1};
    private int endMinuteArray[] = {0,-1,55,50,0,55,15,10,5,10,-1};
    /**课程数量*/
    private int classNumArray[] = {2,0,1,1,1,1,1,1,1,2,0};


    /**
     * 添加默认上课时间数据
     */
    private void addUpClassTimeData()
    {
        boolean isAdd = (boolean) SharedPreferencesUtils.getParam(context, Constants.IS_ONCE_ADD_UP_CLASS_TIME_DATA,false);

        if(!isAdd)
        {
            addTimeData();
            SharedPreferencesUtils.putParam(context,Constants.IS_ONCE_ADD_UP_CLASS_TIME_DATA,true);
        }
    }

    /**
     * 添加默认课程表数据
     */
    private void addCourseTableData() {

        MySqliteHelper mySqliteHelper = new MySqliteHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION);

        boolean isAdd = (boolean) SharedPreferencesUtils.getParam(context,Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,false);
        if(!isAdd)
        {
//            mySqliteHelper.deleteCourseTableAllData();
            mySqliteHelper.addCourseTableAllData();
            SharedPreferencesUtils.putParam(context,Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,true);
        }
    }


    /**
     * 添加数据
     */
    private void addTimeData()
    {

        MySqliteHelper mySqliteHelper = new MySqliteHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION);

        for (int i = 0; i < startTimeArray.length; i++) {

            UpClassTimeModel model = new UpClassTimeModel();

            model.setEndHour(endHourArray[i]);
            model.setEndMinute(endMinuteArray[i]);
            model.setStartHour(startHourArray[i]);
            model.setStartMinute(startMinuteArray[i]);
            model.setClassNum(classNumArray[i]);


            if(i == 0 || i == 1)
            {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MOR_READ);
            }else if(i > 1 && i < 6)
            {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MORNING);
            }else if(i > 5 && i < 9)
            {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_AFTERNOON);
            }else if(i > 8 && i < 11)
            {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_EVENING);
            }

            mySqliteHelper.addUpClassTime(model);
        }

        mySqliteHelper.close();
    }


}

