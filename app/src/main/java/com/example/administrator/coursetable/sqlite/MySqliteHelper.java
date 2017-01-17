package com.example.administrator.coursetable.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.coursetable.constants.Constants;
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
    private final String VALUE_START_HOUR = "start_hour";
    private final String VALUE_START_MINUTE = "start_minute";
    private final String VALUE_END_HOUR = "end_hour";
    private final String VALUE_END_MINUTE = "end_minute";
    /**课程的数量*/
    private final String VALUE_CLASS_NUM = "class_num";



//    private final String VALUE_start_time = "start_time";



    private final String CREATE_UP_CLASS_TIME = "create table "+TABLE_NAME_UP_CLASS+"("+VALUE_ID+" integer primary key,"+
            VALUE_START_TIME +  " text,"+
            VALUE_END_TIME + " text,"+
            VALUE_TIME_TYPE + " integer,"+
            VALUE_START_HOUR + " integer,"+
            VALUE_START_MINUTE + " integer,"+
            VALUE_END_HOUR + " integer,"+
            VALUE_END_MINUTE + " integer,"+
            VALUE_CLASS_NUM + " integer"+
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
        values.put(VALUE_START_HOUR,model.getStartHour());
        values.put(VALUE_START_MINUTE,model.getStartMinute());
        values.put(VALUE_END_HOUR,model.getEndHour());
        values.put(VALUE_END_MINUTE,model.getEndMinute());
        values.put(VALUE_CLASS_NUM,model.getClassNum());

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
        values.put(VALUE_START_HOUR,model.getStartHour());
        values.put(VALUE_START_MINUTE,model.getStartMinute());
        values.put(VALUE_END_HOUR,model.getEndHour());
        values.put(VALUE_END_MINUTE,model.getEndMinute());
        values.put(VALUE_CLASS_NUM,model.getClassNum());

        getWritableDatabase().update(TABLE_NAME_UP_CLASS,values,VALUE_ID+"=?",new String[]{""+model.getId()});

    }

    /**
     * @return  查询所有数据
     */
    public List<UpClassTimeModel> queryUpClassTimeAllData()
    {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_UP_CLASS,null,null,null,null,null,null);

        cursor.moveToFirst();
        List<UpClassTimeModel> list = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {

            UpClassTimeModel model = new UpClassTimeModel();
            String startTime = cursor.getString(cursor.getColumnIndex(VALUE_START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(VALUE_END_TIME));
            int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
            int  startHour = cursor.getInt(cursor.getColumnIndex(VALUE_START_HOUR));
            int startMinute = cursor.getInt(cursor.getColumnIndex(VALUE_START_MINUTE));
            int endHour = cursor.getInt(cursor.getColumnIndex(VALUE_END_HOUR));
            int endMinute = cursor.getInt(cursor.getColumnIndex(VALUE_END_MINUTE));
            int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM));
            int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));

            model.setId(id);
            model.setStartTime(startTime);
            model.setEndTime(endTime);
            model.setStartHour(startHour);
            model.setStartMinute(startMinute);
            model.setEndHour(endHour);
            model.setEndMinute(endMinute);
            model.setClassNum(classNum);
            model.setTimeType(timeType);
            list.add(model);
            cursor.moveToNext();
        }

        return list;
    }

    public void deleteUpClassTimeAllData()
    {

        getWritableDatabase().delete(TABLE_NAME_UP_CLASS,null,null);
    }

    private int startHourArray[] = {7,-1,8,9,10,11,14,15,16,17,-1};
    private int startMinuteArray[] = {30,-1,10,5,15,10,30,25,20,25,-1};
    private int endHourArray[] = {8,-1,8,9,11,11,15,16,17,18,-1};
    private int endMinuteArray[] = {0,-1,55,50,00,55,15,10,5,10,-1};
    /**课程数量*/
    private int classNumArray[] = {2,0,1,1,1,1,1,1,1,2,0};

    public void addDefaultUpClassTimeData()
    {
        for (int i = 0; i < startHourArray.length; i++) {

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

            addUpClassTime(model);
        }
    }


}
