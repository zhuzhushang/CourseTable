package com.example.administrator.coursetable.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.CourseTableModel;
import com.example.administrator.coursetable.model.NoteModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.SharedPreferencesUtils;
import com.example.administrator.coursetable.utils.StringUtils;
import com.example.administrator.coursetable.utils.ToastUtils;

import java.util.List;

import static com.example.administrator.coursetable.constants.Constants.CLASS_NAME_NOTE_CHILD_POSITION;
import static com.example.administrator.coursetable.constants.Constants.CLASS_NAME_NOTE_GROUP_POSITION;
import static com.example.administrator.coursetable.constants.Constants.DB_VERSION;

/**
 * Created by Administrator on 2017/1/2.
 */

public class CourseMessageNoteActivity extends BaseActivity implements View.OnClickListener {

    private ImageView appbar_right_img;
    private TextView detail,note;
    private LinearLayout note_ll,detail_ll;

    private ImageView img_choice_change;
    private ImageView colorImg[];

    private EditText class_name;
    private EditText class_place;
    private TextView num_plus_reduce_tv;
    private int currentClassNum;
    private int maxClassNum = 1;

    private TextView note_time;
    private TextView note_record;

    private ImageView reduce_img;
    private ImageView plus_img;

    private int colorImgID[] = {R.id.color_1_1_random,R.id.color_1_2,R.id.color_1_3,R.id.color_1_4,R.id.color_1_5,R.id.color_2_1,R.id.color_2_2,R.id.color_2_3,R.id.color_2_4,R.id.color_2_5};
    private int colorMipmap[] = {R.mipmap.color_1_1_random,R.mipmap.color_1_2,R.mipmap.color_1_3,R.mipmap.color_1_4,R.mipmap.color_1_5,R.mipmap.color_2_1,R.mipmap.color_2_2,R.mipmap.color_2_3,R.mipmap.color_2_4,R.mipmap.color_2_5};


    /**选择颜色*/
    private int choiceColor = Constants.COLOR_RANDOM;
    private int choiceColorTag[] = {Constants.COLOR_RANDOM,Constants.COLOR_2,Constants.COLOR_3,Constants.COLOR_4,Constants.COLOR_5,Constants.COLOR_6,Constants.COLOR_7,Constants.COLOR_8,Constants.COLOR_9,Constants.COLOR_10};

    private ImageView cancel,confirm;

    private int childPosition,groupPosition;

    private List<CourseTableModel> mListCourseTable;
    private CourseTableModel model;

    private MySqliteHelper mySqliteHelper;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_course_message_note);
        appbatInit();
        ViewInit();
        dataInit();
        eventInit();

    }

    private void appbatInit() {

        appbar_right_img = (ImageView) findViewById(R.id.appbar_right_img);
        appbar_right_img.setOnClickListener(this);

    }

    private void ViewInit() {

        reduce_img = (ImageView) findViewById(R.id.reduce_img);
        plus_img = (ImageView) findViewById(R.id.plus_img);

        class_name = (EditText) findViewById(R.id.class_name);
        class_place = (EditText) findViewById(R.id.class_place);
        num_plus_reduce_tv = (TextView) findViewById(R.id.num_plus_reduce_tv);

        note_time = (TextView) findViewById(R.id.note_time);
        note_record = (TextView) findViewById(R.id.note_record);

        appbar_right_img = (ImageView) findViewById(R.id.appbar_right_img);

        detail = (TextView) findViewById(R.id.detail);
        note = (TextView) findViewById(R.id.note);

        note_ll = (LinearLayout) findViewById(R.id.note_ll);
        detail_ll = (LinearLayout) findViewById(R.id.detail_ll);
        img_choice_change = (ImageView) findViewById(R.id.img_choice_change);
        cancel = (ImageView) findViewById(R.id.cancel);
        confirm = (ImageView) findViewById(R.id.confirm);

    }

    private void dataInit() {

        mySqliteHelper = new MySqliteHelper(context,Constants.DB_NAME,null,DB_VERSION);

        groupPosition = getIntent().getIntExtra(CLASS_NAME_NOTE_GROUP_POSITION,-1);
        childPosition = getIntent().getIntExtra(CLASS_NAME_NOTE_CHILD_POSITION,-1);

        setClassData();

        detail.setCompoundDrawablesWithIntrinsicBounds(null,null,null, ContextCompat.getDrawable(context,R.mipmap.detail_line));

        colorImg = new ImageView[colorImgID.length];

        for (int i = 0; i < colorImgID.length; i++) {

            colorImg[i] = (ImageView) findViewById(colorImgID[i]);
        }
    }

    private void setClassData() {

        if(groupPosition == -1 || childPosition == -1)
        {
            finish();
            return;
        }

        model = getGroupChildPositionData(groupPosition,childPosition);

        if(model != null)
        {

            note_time.setText(getDayOfWeekListener(model.getDayOfWeek()));
            note_record.setText(model.getNote());
            class_name.setText(model.getClassName());
            class_place.setText(model.getAddress());
            num_plus_reduce_tv.setText(""+model.getClassNum());
            currentClassNum = model.getClassNum();
            maxClassNum = getExactClassNum(model);

        }

    }


    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        detail.setOnClickListener(this);
        note.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        reduce_img.setOnClickListener(this);
        plus_img.setOnClickListener(this);

        for (int i = 0; i < colorImgID.length; i++) {

            colorImg[i].setOnClickListener(colorOnClickListener);
        }
    }


    /**
     * @return 得到最大课时
     */
    private int getTimeTypeMax(int timeType)
    {
        if(timeType == Constants.UP_CLASS_TIME_TYPE_MOR_READ)
        {
            return 2;
        }else if(timeType == Constants.UP_CLASS_TIME_TYPE_MORNING)
        {
            return 4;
        }else if(timeType == Constants.UP_CLASS_TIME_TYPE_AFTERNOON)
        {
            return 3;
        }else if(timeType == Constants.UP_CLASS_TIME_TYPE_AFTERNOON)
        {
            return 2;
        }

        return 0;
    }


    /**
     *   但下面还有课时时  不能覆盖下面的课时 返回最准确的课时
     *
     */
    private int getExactClassNum(CourseTableModel model){

        int num = 1;

        int maxP = getAvailableMax(model) - 1;

        for (int i = 1; i <= maxP; i++) {

            CourseTableModel model_4 = mySqliteHelper.queryCourseTableOneData(model.getId()+i);

            if(StringUtils.isEmpty(model_4.getClassName()))
            {
                num++;

            }else
            {
                break;
            }
        }

        return num;
    }




    /**
     * @return 得到可设置的最大课时
     */
    private int getAvailableMax(CourseTableModel model)
    {
        int whatPosition = model.getClassIndex();

        int num = 1;

        switch (whatPosition)
        {
            case 0:

                num = 2;

                break;
            case 1:
                num = 1;
                break;
            case 2:

                num = 4;

                break;
            case 3:
                num = 3;
                break;
            case 4:
                num = 2;
                break;
            case 5:
                num = 1;
                break;
            case 6:

                num = 3;
                break;
            case 7:
                num = 2;
                break;
            case 8:
                num = 1;
                break;
            case 9:
                num = 2;
                break;
            case 10:
                num = 1;
                break;
        }




        return num;
    }



    /**
     * @param dayOfWeek
     * @return  得到星期几
     */
    private String getDayOfWeekListener(int dayOfWeek)
    {
        String wText = "一";

        switch (dayOfWeek)
        {
            case 2:
                wText = "一";
                break;
            case 3:
                wText = "二";
                break;
            case 4:
                wText = "三";
                break;
            case 5:
                wText = "四";
                break;
            case 6:
                wText = "五";
                break;
            case 7:
                wText = "六";
                break;
            case 1:
                wText = "日";
                break;
        }

        return "星期"+wText;
    }

    private CourseTableModel getGroupChildPositionData(int groupPosition, int childPosotion)
    {
        mListCourseTable = mySqliteHelper.queryCourseTableAllData();

        if (mListCourseTable == null || mListCourseTable.size() == 0)
        {
            addCourseTableData();
            mListCourseTable = mySqliteHelper.queryCourseTableAllData();
        }

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

        MySqliteHelper mySqliteHelper = new MySqliteHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION);

        boolean isAdd = (boolean) SharedPreferencesUtils.getParam(context,Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,false);
        if(!isAdd)
        {
            mySqliteHelper.deleteCourseTableAllData();
            mySqliteHelper.addCourseTableAllData();
            SharedPreferencesUtils.putParam(context,Constants.IS_ONCE_ADD_COURSE_TABLE_DATA,true);
        }
    }


    View.OnClickListener colorOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            for (int i = 0; i < colorImgID.length; i++) {

                if(colorImgID[i] == v.getId())
                {

                    img_choice_change.setImageResource(colorMipmap[i]);
                    choiceColor = choiceColorTag[i];
                    model.setBgColor(choiceColorTag[i]);
                    break;
                }
            }
        }
    };


    /**
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();
                break;
            case R.id.appbar_right_img:

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("你确定删除这些信息吗？");
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                   }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mySqliteHelper.resetCourseTableOneData(model);
                        dialogInterface.dismiss();
                        CourseMessageNoteActivity.this.finish();
                    }
                });


                builder.show();


                break;
            case R.id.detail:
                if(detail_ll.getVisibility() != View.VISIBLE)
                {
                    detail.setCompoundDrawablesWithIntrinsicBounds(null,null,null, ContextCompat.getDrawable(context,R.mipmap.detail_line));
                    note.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    detail_ll.setVisibility(View.VISIBLE);
                    note_ll.setVisibility(View.GONE);
                }

                break;
            case R.id.note:

                if(detail_ll.getVisibility() == View.VISIBLE)
                {
                    detail.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
                    note.setCompoundDrawablesWithIntrinsicBounds(null,null,null, ContextCompat.getDrawable(context,R.mipmap.detail_line));
                    detail_ll.setVisibility(View.INVISIBLE);
                    note_ll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.cancel:

                finish();

                break;
            case R.id.confirm:


                String noteRecord = note_record.getText().toString();
                String className = class_name.getText().toString().trim();
                String address = class_place.getText().toString().trim();

                if(StringUtils.isEmpty(className))
                {
                    ToastUtils.show(context,"课程名不能为空");
                    return;
                }

                if(StringUtils.isEmpty(address))
                {
                    ToastUtils.show(context,"上课地址不能为空");
                    return;
                }

                model.setNote(noteRecord);
                model.setClassName(className);
                model.setAddress(address);

                //保存笔记
                if(!StringUtils.isEmpty(noteRecord))
                {
                    NoteModel noteModel = new NoteModel();
                    noteModel.setNote(noteRecord);
                    noteModel.setTime(System.currentTimeMillis());
                    mySqliteHelper.addNote(noteModel);
                }


                mySqliteHelper.chageCourseTableData(model);

                //先计算出 有几节课需要置为0
                int needReset = model.getClassNum() - 1;

                for (int i = 0; i < maxClassNum - 1; i++) {
                    //把需要置为0的课程置为0
                    if(i < needReset)
                    {
                        CourseTableModel tempModel = mySqliteHelper.queryCourseTableOneData(model.getId()+i+1);
                        if(tempModel != null)
                        {
                            tempModel.setClassNum(0);
                            mySqliteHelper.chageCourseTableData(tempModel);
                        }
                    }else
                    {
                        CourseTableModel tempModel = mySqliteHelper.queryCourseTableOneData(model.getId()+i+1);
                        if(tempModel != null)
                        {
                            if(tempModel.getClassNum() > 1)
                            {
                                break;

                            }else
                            {
                                tempModel.setClassNum(1);
                                mySqliteHelper.chageCourseTableData(tempModel);
                            }

                        }

                    }

                }


                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra(Constants.CLASS_NAME_NOTE_CHILD_POSITION, childPosition);
                intent.putExtra(Constants.CLASS_NAME_NOTE_GROUP_POSITION, groupPosition);

                setResult(RESULT_OK,intent);
                finish();

                break;
            case R.id.reduce_img:

                if(currentClassNum > 1 )
                {
                    currentClassNum--;
                    num_plus_reduce_tv.setText(""+currentClassNum);
                    model.setClassNum(currentClassNum);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_min));
                }

                break;
            case R.id.plus_img:

                if(currentClassNum < maxClassNum)
                {
                    currentClassNum++;
                    num_plus_reduce_tv.setText(""+currentClassNum);
                    model.setClassNum(currentClassNum);
                }else
                {
                    ToastUtils.show(context,getString(R.string.already_to_max));
                }

                break;

        }
    }

}