package com.example.administrator.coursetable.constants;

/**
 * Created by Administrator on 2017/1/2.
 */

public class Constants {

    /**每一天的课程总数  就是一天上多少节课*/
    public final static int TOTAL_COURSE = 16;



    /**跳转到课程信息/笔记界面传递位置*/
    public final static String CLASS_NAME_NOTE_CHILD_POSITION = "class_name_note_child_position";
    /**跳转到课程信息/笔记界面传递数组*/
    public final static String CLASS_NAME_NOTE_GROUP_POSITION = "class_name_note_group_position";
    /**跳转到课程信息/笔记界面  */
    public final static int CLASS_NAME_NOTE_REQUEST_CODE = 0x1001;

    /*跳转到上课时间界面*/
    public final static String UP_CLASS_TIME_ARRAY_POSITION = "up_class_time_array_position";
    /**跳转到设置上课时间界面 */
    public final static int UP_CLASS_TIME_POSITION = 0x1002;


    /*数据库的名称*/
    public final static String DB_NAME = "course_table";
    /*数据库的版本号*/
    public final static int DB_VERSION = 0x01;




    /*上课类型  晨读*/
    public final static int UP_CLASS_TIME_TYPE_MOR_READ = 0x1001;
    /*上课类型  上午*/
    public final static int UP_CLASS_TIME_TYPE_MORNING = 0x1002;
    /*上课类型  下午*/
    public final static int UP_CLASS_TIME_TYPE_AFTERNOON = 0x1003;
    /*上课类型  晚上*/
    public final static int UP_CLASS_TIME_TYPE_EVENING = 0x1004;


    /*颜色定义*/
    public final static int COLOR_DEFAULT = -1;
    public final static int COLOR_RANDOM = 1;
    public final static int COLOR_2 = 2;
    public final static int COLOR_3 = 3;
    public final static int COLOR_4 = 4;
    public final static int COLOR_5 = 5;
    public final static int COLOR_6 = 6;
    public final static int COLOR_7 = 7;
    public final static int COLOR_8 = 8;
    public final static int COLOR_9 = 9;
    public final static int COLOR_10 = 10;



    //perfence
    /*是否是第一次添加数据*/
    public final static String IS_ONCE_ADD_UP_CLASS_TIME_DATA = "is_once_add_up_class_time_data";
    public final static String IS_ONCE_ADD_COURSE_TABLE_DATA = "is_once_add_course_table_data";

    //exitStyle
    /*exitStyle  退出时*/
    public final static int EXIT_STYLE_EXIT = 0x1001;
    /*exitStyle  删除时*/
    public final static int EXIT_STYLE_DELETE = 0x1002;

    /*修改笔记int*/
    public final static int EDIT_NOTE = 0x1001;
    /*修改笔记TAG*/
    public final static String EDIT_NOTE_TAG = "edit_note_tag";
    /*修改笔记数据TAG*/
    public final static String EDIT_NOTE_DATA = "edit_note_data";


    /*隐藏周TAG*/
    public static final String HIDE_WEEK_DATA = "hide_week_data";
    /*上课提醒*/
    public static final String UP_CLASS_REMIND = "up_class_remind";
    /*考试提醒*/
    public static final String EXAM_REMIND = "exam_remind";
    /*自动添加背景色*/
    public static final String AUTO_ADD_BG_COLOR = "auto_add_bg_color";


    /**晨读课程类型的最大课时*/
    public static final int MAX_COURESE_MORNING_READ = 3;
    /**上午课程类型的最大课时*/
    public static final int MAX_COURESE_MORNING = 5;
    /**下午课程类型的最大课时*/
    public static final int MAX__COURESE_AFTERNONG = 5;
    /*晚上课程类型的最大课时*/
    public static final int MAX_COURESE_EVENING_READ = 3;



}
