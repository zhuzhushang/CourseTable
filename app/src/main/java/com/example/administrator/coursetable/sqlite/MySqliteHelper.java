package com.example.administrator.coursetable.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.CourseTableModel;
import com.example.administrator.coursetable.model.NoteModel;
import com.example.administrator.coursetable.model.UpClassTimeModel;
import com.example.administrator.coursetable.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaoQuanwei on 2017/1/12.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    private final String TABLE_NAME_UP_CLASS = "table_up_class_time";


    private final String VALUE_ID = "_id";
    private final String VALUE_START_TIME = "start_time";
    private final String VALUE_END_TIME = "end_time";
    private final String VALUE_TIME_TYPE = "time_type";
    private final String VALUE_START_HOUR = "start_hour";
    private final String VALUE_START_MINUTE = "start_minute";
    private final String VALUE_END_HOUR = "end_hour";
    private final String VALUE_END_MINUTE = "end_minute";
    /**
     * 课程的数量
     */
    private final String VALUE_CLASS_NUM = "class_num";

    /* 判断是第几节课*/
    private final String VALUE_CLASS_INDEX = "class_index";


//    private final String VALUE_start_time = "start_time";


    private final String CREATE_UP_CLASS_TIME = "create table " + TABLE_NAME_UP_CLASS + "(" + VALUE_ID + " integer primary key," +
            VALUE_START_TIME + " text," +
            VALUE_END_TIME + " text," +
            VALUE_TIME_TYPE + " integer," +
            VALUE_START_HOUR + " integer," +
            VALUE_START_MINUTE + " integer," +
            VALUE_END_HOUR + " integer," +
            VALUE_END_MINUTE + " integer," +
            VALUE_CLASS_NUM + " integer," +
            VALUE_CLASS_INDEX + " integer" +
            ")";


    //=======================定义课时===========================================================================

    private final String TABLE_NAME_COURSE_TABLE = "course_table";
    private final String VALUE_CLASS_NAME = "class_name";
    private final String VALUE_ADDRESS = "address";
    private final String VALUE_NOTE = "note";

    private final String VALUE_CLASS_NUM_ = "class_num";
    private final String VALUE_BG_COLOR = "bg_color";
    private final String VALUE_DAY_OF_WEEK = "day_of_week";
    private final String VALUE_GROUP_POSITION = "group_position";
    private final String VALUE_CHILD_POSITION = "child_position";


    //定义课程表的一些信息
    private final String CREATE_COURSE_TABLE = "create table " + TABLE_NAME_COURSE_TABLE + "(" +
            VALUE_ID + " integer primary key," +
            VALUE_CLASS_NAME + " text," +
            VALUE_ADDRESS + " text," +
            VALUE_NOTE + " text," +
            VALUE_CLASS_INDEX + " integer," +
            VALUE_CLASS_NUM_ + " integer," +
            VALUE_BG_COLOR + " integer," +
            VALUE_TIME_TYPE + " integer," +
            VALUE_DAY_OF_WEEK + " integer," +
            VALUE_GROUP_POSITION + " integer," +
            VALUE_CHILD_POSITION + " integer" +
            ")";

    //==================定义笔记=====================================================================================
    private final String TABLE_NAME_NOTE = "table_name_note";
    private final String VALUE_TIME = "time";
//    private final String VALUE_NOTE = "note";

    private final String CREATE_NOTE = "create table " + TABLE_NAME_NOTE + "(" +
            VALUE_ID + " integer primary key," +
            VALUE_TIME + " integer," +
            VALUE_NOTE + " text" +
            ")";

    //====================定义考试时间==============================================================================================
    private final String TABLE_NAME_EXAM_TIME = "table_name_exam_time";
    private final String VALUE_YEAR = "year";
    private final String VALUE_MONTH = "month";
    private final String VALUE_DAY = "day";
    private final String VALUE_HOUR_START = "hour_start";
    private final String VALUE_MINUTE_START = "minute_start";
    private final String VALUE_HOUR_END = "hour_end";
    private final String VALUE_MINUTE_END = "minute_end";


    private final String CREATE_EXAM_TIME = "create table " + TABLE_NAME_EXAM_TIME + "(" +
            VALUE_ID + " ingeter primary key," +
            VALUE_YEAR + " integer ," +
            VALUE_MONTH + " integer ," +
            VALUE_DAY + " integer," +
            VALUE_HOUR_START + " integer," +
            VALUE_MINUTE_START + " integer," +
            VALUE_HOUR_END + " integer," +
            VALUE_MINUTE_END + " integer," +

            ")";


    //--------------构造方法----------------------------------------------------------------------------------------

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_UP_CLASS_TIME);
        sqLiteDatabase.execSQL(CREATE_COURSE_TABLE);
        sqLiteDatabase.execSQL(CREATE_NOTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //==============================笔记设置====================================================================================

    /**
     * @param model 添加笔记
     */
    public void addNote(NoteModel model) {
        ContentValues value = new ContentValues();
        value.put(VALUE_TIME, model.getTime());
        value.put(VALUE_NOTE, model.getNote());

        getWritableDatabase().insert(TABLE_NAME_NOTE, null, value);
    }

    /**
     * 修改笔记
     */
    public void updateNote(NoteModel model) {
        ContentValues value = new ContentValues();
        value.put(VALUE_TIME, model.getTime());
        value.put(VALUE_NOTE, model.getNote());

        getWritableDatabase().update(TABLE_NAME_NOTE, value, VALUE_ID + "=?", new String[]{"" + model.getId()});

    }

    /**
     * 删除笔记
     */
    public void deleteNote(NoteModel model) {

        getWritableDatabase().delete(TABLE_NAME_NOTE, VALUE_ID + "=?", new String[]{"" + model.getId()});

    }

    /**
     * 查询所有笔记
     */
    public List<NoteModel> queryNoteAllData() {

        Cursor cursor = getWritableDatabase().query(TABLE_NAME_NOTE, null, null, null, null, null, VALUE_ID + " desc");
        List<NoteModel> list = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {

            NoteModel model = new NoteModel();
            model.setId(cursor.getInt(cursor.getColumnIndex(VALUE_ID)));
            model.setNote(cursor.getString(cursor.getColumnIndex(VALUE_NOTE)));
            model.setTime(cursor.getLong(cursor.getColumnIndex(VALUE_TIME)));

            list.add(model);
            cursor.moveToNext();
        }

        releaseCursor(cursor);

        return list;
    }


    //===========================课程表设置==============================================================================

    /**
     * @param model 添加课程表数据
     */
    public void addCourseTable(CourseTableModel model) {
        ContentValues values = new ContentValues();
        values.put(VALUE_CLASS_NAME, model.getClassName());
        values.put(VALUE_ADDRESS, model.getAddress());
        values.put(VALUE_NOTE, model.getNote());
        values.put(VALUE_CLASS_INDEX, model.getClassIndex());
        values.put(VALUE_CLASS_NUM_, model.getClassNum());
        values.put(VALUE_BG_COLOR, model.getBgColor());
        values.put(VALUE_TIME_TYPE, model.getTimeType());
        values.put(VALUE_DAY_OF_WEEK, model.getDayOfWeek());
        values.put(VALUE_GROUP_POSITION, model.getGroupPosition());
        values.put(VALUE_CHILD_POSITION, model.getChildPosition());


        getWritableDatabase().insert(TABLE_NAME_COURSE_TABLE, null, values);

    }


    /**
     * @param queryId 查询数据的id
     * @return id所对应的数据
     */
    public CourseTableModel queryCourseTableOneData(int queryId) {

        Cursor cursor = getWritableDatabase().query(TABLE_NAME_COURSE_TABLE, new String[]{VALUE_ID, VALUE_CLASS_NAME, VALUE_ADDRESS, VALUE_NOTE, VALUE_CLASS_INDEX, VALUE_CLASS_NUM_, VALUE_BG_COLOR, VALUE_TIME_TYPE, VALUE_DAY_OF_WEEK, VALUE_GROUP_POSITION, VALUE_CHILD_POSITION}, VALUE_ID + "=?", new String[]{"" + queryId}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
            String className = cursor.getString(cursor.getColumnIndex(VALUE_CLASS_NAME));
            String address = cursor.getString(cursor.getColumnIndex(VALUE_ADDRESS));
            String note = cursor.getString(cursor.getColumnIndex(VALUE_NOTE));
            int classIndex = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));
            int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM_));
            int bgColor = cursor.getInt(cursor.getColumnIndex(VALUE_BG_COLOR));
            int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
            int dayOfWeek = cursor.getInt(cursor.getColumnIndex(VALUE_DAY_OF_WEEK));
            int groupPosition = cursor.getInt(cursor.getColumnIndex(VALUE_GROUP_POSITION));
            int childPosition = cursor.getInt(cursor.getColumnIndex(VALUE_CHILD_POSITION));

            CourseTableModel model = new CourseTableModel();
            if (className == null || "0".equals(className) || "".equals(className)) {
                model.setClassName("");
            } else {
                model.setClassName(className);
            }

            model.setId(id);
            model.setAddress(address);
            model.setNote(note);
            model.setClassNum(classNum);
            model.setClassIndex(classIndex);
            model.setBgColor(bgColor);
            model.setTimeType(timeType);
            model.setDayOfWeek(dayOfWeek);
            model.setGroupPosition(groupPosition);
            model.setChildPosition(childPosition);

            releaseCursor(cursor);
            return model;
        }

        releaseCursor(cursor);
        return null;

    }


    public List<CourseTableModel> queryCourseTableAllData() {

        List<CourseTableModel> list = new ArrayList<>();
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_COURSE_TABLE, null, null, null, null, null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {

            int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
            String className = cursor.getString(cursor.getColumnIndex(VALUE_CLASS_NAME));
            String address = cursor.getString(cursor.getColumnIndex(VALUE_ADDRESS));
            String note = cursor.getString(cursor.getColumnIndex(VALUE_NOTE));
            int classIndex = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));
            int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM_));
            int bgColor = cursor.getInt(cursor.getColumnIndex(VALUE_BG_COLOR));
            int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
            int dayOfWeek = cursor.getInt(cursor.getColumnIndex(VALUE_DAY_OF_WEEK));
            int groupPosition = cursor.getInt(cursor.getColumnIndex(VALUE_GROUP_POSITION));
            int childPosition = cursor.getInt(cursor.getColumnIndex(VALUE_CHILD_POSITION));

            CourseTableModel model = new CourseTableModel();
            if (className == null || "0".equals(className) || "".equals(className)) {
                model.setClassName("");
            } else {
                model.setClassName(className);
            }

            model.setId(id);
            model.setAddress(address);
            model.setNote(note);
            model.setClassNum(classNum);
            model.setClassIndex(classIndex);
            model.setBgColor(bgColor);
            model.setTimeType(timeType);
            model.setDayOfWeek(dayOfWeek);
            model.setGroupPosition(groupPosition);
            model.setChildPosition(childPosition);

            list.add(model);
            cursor.moveToNext();
        }

        releaseCursor(cursor);

        return list;
    }

    public void chageCourseTableData(CourseTableModel model) {

        ContentValues values = new ContentValues();
        values.put(VALUE_CLASS_NAME, model.getClassName());
        values.put(VALUE_ADDRESS, model.getAddress());
        values.put(VALUE_NOTE, model.getNote());
        values.put(VALUE_CLASS_INDEX, model.getClassIndex());
        values.put(VALUE_CLASS_NUM_, model.getClassNum());
        values.put(VALUE_BG_COLOR, model.getBgColor());
        values.put(VALUE_TIME_TYPE, model.getTimeType());
        values.put(VALUE_DAY_OF_WEEK, model.getDayOfWeek());
        values.put(VALUE_GROUP_POSITION, model.getGroupPosition());
        values.put(VALUE_CHILD_POSITION, model.getChildPosition());


        getWritableDatabase().update(TABLE_NAME_COURSE_TABLE, values, VALUE_ID + "=?", new String[]{"" + model.getId()});

    }


    /**
     * 添加课程表所有默认数据
     */
    public void addCourseTableAllData() {

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < Constants.TOTAL_COURSE; j++) {
                CourseTableModel model = new CourseTableModel();
                model.setGroupPosition(i);
                model.setChildPosition(j);
                model.setClassIndex(j);
                model.setDayOfWeek(i + 1);
                model.setBgColor(-1);
                model.setClassNum(1);
                model.setClassName("");
                model.setAddress("");
                model.setNote("");

                if (j == 1||j == 2 || j == 14|| j == 15) {
                    model.setClassNum(0);
                }

                if (j == 0 || j == 13) {
                    model.setClassNum(3);
                }


                if(i >= 0 || i <= 2)
                {
                    model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MOR_READ);
                }else if(i > 2 && i < 8)
                {
                    model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MORNING);
                }else if(i > 7 && i < 13)
                {
                    model.setTimeType(Constants.UP_CLASS_TIME_TYPE_AFTERNOON);
                }else if(i > 12 && i < 16)
                {
                    model.setTimeType(Constants.UP_CLASS_TIME_TYPE_EVENING);
                }

                addCourseTable(model);

            }

        }
    }

    /**
     * 重置某一条数据
     */
    public void resetCourseTableOneData(CourseTableModel resetModel) {

        resetModel.setBgColor(-1);
//        resetModel.setClassNum(1);
        resetModel.setClassName("");
        resetModel.setAddress("");
        resetModel.setNote("");

        chageCourseTableData(resetModel);

    }

    /**
     * @param dayOfWeekOn
     */
    public List<CourseTableModel> queryCurrentDayData(int dayOfWeekOn) {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_COURSE_TABLE, null, VALUE_DAY_OF_WEEK + "=?", new String[]{"" + dayOfWeekOn}, null, null, null);
        List<CourseTableModel> list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {


                int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
                String className = cursor.getString(cursor.getColumnIndex(VALUE_CLASS_NAME));
                String address = cursor.getString(cursor.getColumnIndex(VALUE_ADDRESS));
                String note = cursor.getString(cursor.getColumnIndex(VALUE_NOTE));
                int classIndex = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));
                int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM_));
                int bgColor = cursor.getInt(cursor.getColumnIndex(VALUE_BG_COLOR));
                int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
                int dayOfWeek = cursor.getInt(cursor.getColumnIndex(VALUE_DAY_OF_WEEK));
                int groupPosition = cursor.getInt(cursor.getColumnIndex(VALUE_GROUP_POSITION));
                int childPosition = cursor.getInt(cursor.getColumnIndex(VALUE_CHILD_POSITION));


                if (!StringUtils.isEmpty(className)) {
                    CourseTableModel model = new CourseTableModel();
                    model.setClassName(className);
                    model.setId(id);
                    model.setAddress(address);
                    model.setNote(note);
                    model.setClassNum(classNum);
                    model.setClassIndex(classIndex);
                    model.setBgColor(bgColor);
                    model.setTimeType(timeType);
                    model.setDayOfWeek(dayOfWeek);
                    model.setGroupPosition(groupPosition);
                    model.setChildPosition(childPosition);
                    list.add(model);

                }

                cursor.moveToNext();
            }
        }

        releaseCursor(cursor);

        return list;
    }


    /**
     * 删除所有数据
     */
    public void deleteCourseTableAllData() {

        if (IsTableExist()) {
            getWritableDatabase().delete(TABLE_NAME_COURSE_TABLE, null, null);
        }

    }

    //判断表是否存在
    private boolean IsTableExist() {
        boolean isTableExist = true;

        Cursor c = getWritableDatabase().rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='course_table'", null);
        if (c.getInt(0) == 0) {
            isTableExist = false;
        }
        releaseCursor(c);
        return isTableExist;
    }


    //===========================上课时间设置========================================================================

    public void addUpClassTime(String startTime, String endTime) {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME, startTime);
        values.put(VALUE_END_TIME, endTime);

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS, null, values);
    }

    public void addUpClassTime(UpClassTimeModel model) {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME, model.getStartTime());
        values.put(VALUE_END_TIME, model.getEndTime());
        values.put(VALUE_START_HOUR, model.getStartHour());
        values.put(VALUE_START_MINUTE, model.getStartMinute());
        values.put(VALUE_END_HOUR, model.getEndHour());
        values.put(VALUE_END_MINUTE, model.getEndMinute());
        values.put(VALUE_CLASS_NUM, model.getClassNum());

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS, null, values);
    }

    public void updateUpClassTime(String startTime, String endTime) {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME, startTime);
        values.put(VALUE_END_TIME, endTime);

        getWritableDatabase().insert(TABLE_NAME_UP_CLASS, null, values);
    }

    /**
     * 修改数据
     *
     * @param model
     */
    public void updateUpClassTime(UpClassTimeModel model) {

        ContentValues values = new ContentValues();
        values.put(VALUE_START_TIME, model.getStartTime());
        values.put(VALUE_END_TIME, model.getEndTime());
        values.put(VALUE_START_HOUR, model.getStartHour());
        values.put(VALUE_START_MINUTE, model.getStartMinute());
        values.put(VALUE_END_HOUR, model.getEndHour());
        values.put(VALUE_END_MINUTE, model.getEndMinute());
        values.put(VALUE_CLASS_NUM, model.getClassNum());

        getWritableDatabase().update(TABLE_NAME_UP_CLASS, values, VALUE_ID + "=?", new String[]{"" + model.getId()});

    }

    /**
     * @return 查询所有上课时间数据
     */
    public List<UpClassTimeModel> queryUpClassTimeAllData() {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_UP_CLASS, null, null, null, null, null, null);

        cursor.moveToFirst();
        List<UpClassTimeModel> list = new ArrayList<>();

        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {

                UpClassTimeModel model = new UpClassTimeModel();
                String startTime = cursor.getString(cursor.getColumnIndex(VALUE_START_TIME));
                String endTime = cursor.getString(cursor.getColumnIndex(VALUE_END_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
                int startHour = cursor.getInt(cursor.getColumnIndex(VALUE_START_HOUR));
                int startMinute = cursor.getInt(cursor.getColumnIndex(VALUE_START_MINUTE));
                int endHour = cursor.getInt(cursor.getColumnIndex(VALUE_END_HOUR));
                int endMinute = cursor.getInt(cursor.getColumnIndex(VALUE_END_MINUTE));
                int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM));
                int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
                int classIndex = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));

                model.setId(id);
                model.setStartTime(startTime);
                model.setEndTime(endTime);
                model.setStartHour(startHour);
                model.setStartMinute(startMinute);
                model.setEndHour(endHour);
                model.setEndMinute(endMinute);
                model.setClassNum(classNum);
                model.setTimeType(timeType);
                model.setClassIndex(classIndex);
                list.add(model);
                cursor.moveToNext();
            }
        }
        releaseCursor(cursor);

        return list;
    }


    /**
     * @return 查询所有上课时间数据  按id排序
     */
    public List<UpClassTimeModel> queryUpClassTimeAllDataById() {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_UP_CLASS, null, null, null, null, null, VALUE_ID + " asc");

        cursor.moveToFirst();
        List<UpClassTimeModel> list = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {

            UpClassTimeModel model = new UpClassTimeModel();
            String startTime = cursor.getString(cursor.getColumnIndex(VALUE_START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(VALUE_END_TIME));
            int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
            int startHour = cursor.getInt(cursor.getColumnIndex(VALUE_START_HOUR));
            int startMinute = cursor.getInt(cursor.getColumnIndex(VALUE_START_MINUTE));
            int endHour = cursor.getInt(cursor.getColumnIndex(VALUE_END_HOUR));
            int endMinute = cursor.getInt(cursor.getColumnIndex(VALUE_END_MINUTE));
            int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM));
            int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
            int classIndex = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));

            model.setId(id);
            model.setStartTime(startTime);
            model.setEndTime(endTime);
            model.setStartHour(startHour);
            model.setStartMinute(startMinute);
            model.setEndHour(endHour);
            model.setEndMinute(endMinute);
            model.setClassNum(classNum);
            model.setTimeType(timeType);
            model.setClassIndex(classIndex);
            list.add(model);
            cursor.moveToNext();
        }

        releaseCursor(cursor);

        return list;
    }


    public UpClassTimeModel queryClassIndexData(int classIndex) {
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_UP_CLASS, null, VALUE_CLASS_INDEX + "=?", new String[]{"" + classIndex}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            UpClassTimeModel model = new UpClassTimeModel();
            String startTime = cursor.getString(cursor.getColumnIndex(VALUE_START_TIME));
            String endTime = cursor.getString(cursor.getColumnIndex(VALUE_END_TIME));
            int id = cursor.getInt(cursor.getColumnIndex(VALUE_ID));
            int startHour = cursor.getInt(cursor.getColumnIndex(VALUE_START_HOUR));
            int startMinute = cursor.getInt(cursor.getColumnIndex(VALUE_START_MINUTE));
            int endHour = cursor.getInt(cursor.getColumnIndex(VALUE_END_HOUR));
            int endMinute = cursor.getInt(cursor.getColumnIndex(VALUE_END_MINUTE));
            int classNum = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_NUM));
            int timeType = cursor.getInt(cursor.getColumnIndex(VALUE_TIME_TYPE));
            int classIndex_ = cursor.getInt(cursor.getColumnIndex(VALUE_CLASS_INDEX));

            model.setId(id);
            model.setStartTime(startTime);
            model.setEndTime(endTime);
            model.setStartHour(startHour);
            model.setStartMinute(startMinute);
            model.setEndHour(endHour);
            model.setEndMinute(endMinute);
            model.setClassNum(classNum);
            model.setTimeType(timeType);
            model.setClassIndex(classIndex_);

            return model;
        }

        return null;
    }


    public void deleteUpClassTimeAllData() {

        getWritableDatabase().delete(TABLE_NAME_UP_CLASS, null, null);
    }

    private int startHourArray[] = {7, -1, 8, 9, 10, 11, 14, 15, 16, 17, -1};
    private int startMinuteArray[] = {30, -1, 10, 5, 15, 10, 30, 25, 20, 25, -1};
    private int endHourArray[] = {8, -1, 8, 9, 11, 11, 15, 16, 17, 18, -1};
    private int endMinuteArray[] = {0, -1, 55, 50, 00, 55, 15, 10, 5, 10, -1};
    /**
     * 课程数量
     */
    private int classNumArray[] = {2, 0, 1, 1, 1, 1, 1, 1, 1, 2, 0};

    public void addDefaultUpClassTimeData() {
        for (int i = 0; i < startHourArray.length; i++) {

            UpClassTimeModel model = new UpClassTimeModel();

            model.setEndHour(endHourArray[i]);
            model.setEndMinute(endMinuteArray[i]);
            model.setStartHour(startHourArray[i]);
            model.setStartMinute(startMinuteArray[i]);
            model.setClassNum(classNumArray[i]);


            if (i == 0 || i == 1) {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MOR_READ);
            } else if (i > 1 && i < 6) {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_MORNING);
            } else if (i > 5 && i < 9) {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_AFTERNOON);
            } else if (i > 8 && i < 11) {
                model.setTimeType(Constants.UP_CLASS_TIME_TYPE_EVENING);
            }

            addUpClassTime(model);
        }

    }


    private void releaseCursor(Cursor cursor) {
        cursor.close();
        cursor = null;
    }

}
