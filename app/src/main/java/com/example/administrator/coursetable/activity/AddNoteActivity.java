package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.NoteModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.StringUtils;
import com.example.administrator.coursetable.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ShaoQuanwei on 2017/1/26.
 */

public class AddNoteActivity extends BaseActivity implements View.OnClickListener{

    private ImageView appbar_right_img;

    private TextView note_time;
    private EditText note_record;

    private MySqliteHelper mySqliteHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_note);
        appbarInit();
        viewInit();
        dataInit();
        eventInit();

    }

    private void appbarInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        appbar_right_img = (ImageView) findViewById(R.id.appbar_right_img);
        appbar_right_img.setOnClickListener(this);


    }

    private void viewInit() {

        note_time = (TextView) findViewById(R.id.note_time);
        note_record = (EditText) findViewById(R.id.note_record);


    }

    private void dataInit() {

        mySqliteHelper = new MySqliteHelper(context, Constants.DB_NAME,null,Constants.DB_VERSION);

        note_time.setText(getYMD()+"  星期"+getDayOfWeek());

    }

    private void eventInit() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
            case R.id.appbar_right_img:

                if(checkData())
                {
                    NoteModel model = new NoteModel();
                    model.setTime(System.currentTimeMillis());
                    model.setNote(note);

                    mySqliteHelper.addNote(model);
                    finish();
                }



                break;
        }
    }

    private String note;

    private boolean checkData() {

        note = note_record.getText().toString();

        if(StringUtils.isEmpty(note))
        {
            ToastUtils.show(context,"笔记不能为空");
            return false;
        }


        return true;
    }


    private String getYMD()
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());

    }


    /**
     * @return  得到星期几
     */
    private String getDayOfWeek()
    {
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);


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

        return wText;
    }


}
