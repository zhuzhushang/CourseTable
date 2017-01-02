package com.example.administrator.coursetable.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.adapter.NotesAdapter;
import com.example.administrator.coursetable.model.NotesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NotesActivity extends BaseActivity implements View.OnClickListener{


    private ListView note_listview;
    private NotesAdapter mAdapter;
    private List<NotesModel> mList;

    private Random random;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_course_list);
        ViewInit();
        dataInit();
        eventInit();

    }

    private void ViewInit() {

        note_listview = (ListView) findViewById(R.id.note_listview);

    }

    private void dataInit() {

        random = new Random();

        mList = new ArrayList<>();
        listInit();
        mAdapter = new NotesAdapter(context,mList,R.layout.item_note);
        note_listview.setAdapter(mAdapter);



    }

    private void listInit() {

        for (int i = 0; i < 20; i++) {

            NotesModel model = new NotesModel();
            model.setExpan(false);
            model.setNotepad("上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容上课笔记内容");
            model.setWeekPosition(random.nextInt(7));
            model.setYTD("2016.09.10");
            mList.add(model);
        }

    }

    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
        }
    }
}
