package com.example.administrator.coursetable.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.coursetable.model.UpClassTimeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaoQuanwei on 2017/1/12.
 */

public class MySqliteHelper extends SQLiteOpenHelper{

    private final String TABLE_NAME_UP_CLASS = "table_up_class_time";


    private final String VALUE_ID = "_id";
    private final String VALUE_START_TIME = "start_time";
    private final String VALUE_END_TIME = "end_time";
    private final String VALUE_TIME_TYPE = "time_type";
//    private final String VALUE_start_time = "start_time";



    private final String CREATE_UP_CLASS_TIME = "create table "+TABLE_NAME_UP_CLASS+"("+VALUE_ID+" integer primary key,"+
            VALUE_START_TIME +  " text,"+
            VALUE_END_TIME + " text,"+
            VALUE_TIME_TYPE + " integer"+
            ")";




    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);



    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_UP_CLASS_TIME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addUpClassTime(String startTime ,String endTime)
    {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME,startTime);
        values.put(VALUE_END_TIME,endTime);

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS,null,values);
    }

    public void addUpClassTime(UpClassTimeModel model)
    {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME,model.getStartTime());
        values.put(VALUE_END_TIME,model.getEndTime());

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS,null,values);
    }

    public void updateUpClassTime(String startTime ,String endTime)
    {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME,startTime);
        values.put(VALUE_END_TIME,endTime);

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS,null,values);
    }

    /**
     * 修改数据
     * @param model
     */
    public void updateUpClassTime(UpClassTimeModel model)
    {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME,model.getStartTime());
        values.put(VALUE_END_TIME,model.getEndTime());

        getWritableDatabase().update(TABLE_NAME_UP_CLASS,values,VALUE_ID+"=?",new String[]{""+model.getId()});

    }

    public List<UpClassTimeModel> queryUpClassTimeAllData()
    {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_UP_CLASS,null,null,null,null,null,null);

        cursor.moveToFirst();
        List<UpClassTimeModel> list = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {

            UpClassTimeModel model = new UpClassTimeModel();
            String startTime = cursor.getString(cursor.getColumnIndex(VALUE_START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(VALUE_END_TIME));
            model.setStartTime(startTime);
            model.setEndTime(endTime);
            list.add(model);
        }

        return list;
    }


}
