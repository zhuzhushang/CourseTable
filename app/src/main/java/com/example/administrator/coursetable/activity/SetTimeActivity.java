package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.UpClassTimeModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */

public class SetTimeActivity extends BaseActivity implements View.OnClickListener {

    private List<UpClassTimeModel> mlist;

    private MySqliteHelper mySqliteHelper;

    /*课时时段*/
    private TextView mor_read,morning,afternoon,evening;
    /*加减imageview*/
    private ImageView mor_read_reduce_img,mor_read_plus_img,mor_reduce_img,mor_plus_img,afternoon_reduce_img,afternoon_plus_img,evening_reduce_img,evening_plus_img;
    /*需要加减的数*/
    private TextView mor_read_num_plus_reduce_tv,mor_num_plus_reduce_tv,afternoon_num_plus_reduce_tv,evening_num_plus_reduce_tv;
    /*课时的时间*/
    private TextView class_mor_read_1,class_mor_read_2,class_mor_1,class_mor_2,class_mor_3,class_mor_4,class_afternoon_1,class_afternoon_2,class_afternoon_3,class_evening_1,class_evening_2;

    /*默认时间*/
    private String startTimeArray[] = {"07:30","","08:10","09:05","10:15","11:10","14:30","15:25","16:20","17:25",""};
    private String endTimeArray[] = {"08:00","","08:55","09:50","11:00","11:55","15:15","16:10","17:05","18:10",""};

    private final int MOR_READ_MAX = 2;
    private final int MOR_MAX = 4;
    private final int AFTERNOON_MAX = 3;
    private final int EVENING_MAX = 2;

    private  int mor_read_count = 1;
    private  int mor_count = 4;
    private  int afternoon_count = 3;
    private  int evening_count = 1;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_set_time);
        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        mor_read_reduce_img = (ImageView) findViewById(R.id.mor_read_reduce_img);
        mor_read_plus_img = (ImageView) findViewById(R.id.mor_read_plus_img);
        mor_reduce_img = (ImageView) findViewById(R.id.mor_reduce_img);
        mor_plus_img = (ImageView) findViewById(R.id.mor_plus_img);
        afternoon_reduce_img = (ImageView) findViewById(R.id.afternoon_reduce_img);
        afternoon_plus_img = (ImageView) findViewById(R.id.afternoon_plus_img);
        evening_reduce_img = (ImageView) findViewById(R.id.evening_reduce_img);
        evening_plus_img = (ImageView) findViewById(R.id.evening_plus_img);


        mor_read_num_plus_reduce_tv = (TextView) findViewById(R.id.mor_read_num_plus_reduce_tv);
        mor_num_plus_reduce_tv = (TextView) findViewById(R.id.mor_num_plus_reduce_tv);
        afternoon_num_plus_reduce_tv = (TextView) findViewById(R.id.afternoon_num_plus_reduce_tv);
        evening_num_plus_reduce_tv = (TextView) findViewById(R.id.evening_num_plus_reduce_tv);



        class_mor_read_1 = (TextView) findViewById(R.id.class_mor_read_1);
        class_mor_read_2 = (TextView) findViewById(R.id.class_mor_read_2);
        class_mor_1 = (TextView) findViewById(R.id.class_mor_1);
        class_mor_2 = (TextView) findViewById(R.id.class_mor_2);
        class_mor_3 = (TextView) findViewById(R.id.class_mor_3);
        class_mor_4 = (TextView) findViewById(R.id.class_mor_4);
        class_afternoon_1 = (TextView) findViewById(R.id.class_afternoon_1);
        class_afternoon_2 = (TextView) findViewById(R.id.class_afternoon_2);
        class_afternoon_3 = (TextView) findViewById(R.id.class_afternoon_3);
        class_evening_1 = (TextView) findViewById(R.id.class_evening_1);
        class_evening_2 = (TextView) findViewById(R.id.class_evening_2);


        mor_read = (TextView) findViewById(R.id.mor_read);
        morning = (TextView) findViewById(R.id.morning);
        afternoon = (TextView) findViewById(R.id.afternoon);
        evening = (TextView) findViewById(R.id.evening);



    }

    private void dataInit() {

        mlist = new ArrayList<>();
        mySqliteHelper = new MySqliteHelper(context, Constants.DB_NAME,null,Constants.DB_VERSION);


    }

    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        mor_read_plus_img.setOnClickListener(this);
        mor_read_reduce_img.setOnClickListener(this);
        mor_plus_img.setOnClickListener(this);
        mor_reduce_img.setOnClickListener(this);
        afternoon_plus_img.setOnClickListener(this);
        afternoon_reduce_img.setOnClickListener(this);
        evening_plus_img.setOnClickListener(this);
        evening_reduce_img.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
            case R.id.mor_read_plus_img:

                if(mor_read_count < MOR_READ_MAX)
                {
                    mor_read_count++;
                    mor_read_num_plus_reduce_tv.setText(""+mor_read_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_max));
                }


                break;
            case R.id.mor_read_reduce_img:

                if(mor_read_count > 1 )
                {
                    mor_read_count--;
                    mor_read_num_plus_reduce_tv.setText(""+mor_read_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_min));
                }


                break;
            case R.id.mor_plus_img:

                if(mor_count < MOR_MAX)
                {
                    mor_count++;
                    mor_num_plus_reduce_tv.setText(""+mor_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_max));
                }

                break;
            case R.id.mor_reduce_img:

                if(mor_count > 1 )
                {
                    mor_count--;
                    mor_num_plus_reduce_tv.setText(""+mor_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_min));
                }

                break;
            case R.id.afternoon_plus_img:

                if(afternoon_count < AFTERNOON_MAX)
                {
                    afternoon_count++;
                    afternoon_num_plus_reduce_tv.setText(""+afternoon_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_max));
                }

                break;
            case R.id.afternoon_reduce_img:

                if(afternoon_count > 1 )
                {
                    afternoon_count--;
                    afternoon_num_plus_reduce_tv.setText(""+afternoon_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_min));
                }

                break;
            case R.id.evening_plus_img:

                if(evening_count < EVENING_MAX)
                {
                    evening_count++;
                    evening_num_plus_reduce_tv.setText(""+evening_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_max));
                }


                break;
            case R.id.evening_reduce_img:

                if(evening_count > 1 )
                {
                    evening_count--;
                    evening_num_plus_reduce_tv.setText(""+evening_count);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_min));
                }

                break;

        }
    }

    /**设置时间和课时*/
    private void setTimeData()
    {
        mlist = mySqliteHelper.queryUpClassTimeAllData();








    }



}
