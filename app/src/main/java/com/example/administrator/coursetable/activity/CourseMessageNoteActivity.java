package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.coursetable.R;

/**
 * Created by Administrator on 2017/1/2.
 */

public class CourseMessageNoteActivity extends BaseActivity implements View.OnClickListener {

    private ImageView appbar_right_img;
    private TextView detail,note;
    private LinearLayout note_ll,detail_ll;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_course_message_note);
        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        appbar_right_img = (ImageView) findViewById(R.id.appbar_right_img);

        detail = (TextView) findViewById(R.id.detail);
        note = (TextView) findViewById(R.id.note);

        note_ll = (LinearLayout) findViewById(R.id.note_ll);
        detail_ll = (LinearLayout) findViewById(R.id.detail_ll);


    }

    private void dataInit() {

        detail.setCompoundDrawablesWithIntrinsicBounds(null,null,null, ContextCompat.getDrawable(context,R.mipmap.detail_line));


    }

    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        detail.setOnClickListener(this);
        note.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();
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

        }
    }

}