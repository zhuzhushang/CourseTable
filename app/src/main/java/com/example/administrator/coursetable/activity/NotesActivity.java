package com.example.administrator.coursetable.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.abstracts.OnActionDown;
import com.example.administrator.coursetable.adapter.NotesAdapter;
import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.dialog.ExitStyleDialog;
import com.example.administrator.coursetable.model.NoteModel;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NotesActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{


    private ListView note_listview;
    private NotesAdapter mAdapter;
    private List<NoteModel> mList;

    private Random random;
    private MySqliteHelper mySqliteHelper;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_course_list);
        appbarInit();
        ViewInit();
        dataInit();
        eventInit();

    }

    private void appbarInit() {

        findViewById(R.id.appbar_right_img).setOnClickListener(this);

    }

    private void ViewInit() {

        note_listview = (ListView) findViewById(R.id.note_listview);

    }

    private void dataInit() {

        random = new Random();

        mList = new ArrayList<>();

        mAdapter = new NotesAdapter(context,mList,R.layout.item_note);
        note_listview.setAdapter(mAdapter);

        mySqliteHelper = new MySqliteHelper(context, Constants.DB_NAME,null,Constants.DB_VERSION);

    }



    private void eventInit() {

        findViewById(R.id.appbar_left_tv_arrow).setOnClickListener(this);
        note_listview.setOnItemClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

        mList =  mySqliteHelper.queryNoteAllData();
        mAdapter.setList(mList);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.appbar_left_tv_arrow:

                finish();

                break;
            case R.id.appbar_right_img:

                startActivity(new Intent(context,AddNoteActivity.class));

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ExitStyleDialog dialog = new ExitStyleDialog(context, new OnActionDown() {
            @Override
            public void onDelete(int index) {
                super.onDelete(index);
            }
        },Constants.EXIT_STYLE_DELETE,position);

        dialog.show();

    }
}
