package com.example.administrator.coursetable.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.coursetable.constants.Constants;
import com.example.administrator.coursetable.model.CourseTableModel;
import com.example.administrator.coursetable.model.UpClassTimeModel;
import com.example.administrator.coursetable.receiver.AlarmReceiver;
import com.example.administrator.coursetable.sqlite.MySqliteHelper;
import com.example.administrator.coursetable.utils.StringUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */

public class AlarmService extends Service {

    private final String TAG = "AlarmService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();


    }


    /**
     * 设置闹钟
     * <p>
     * 因为有默认时间，所以我们根据课程开设置闹钟。
     * 具体做法是：
     * 1.先检索星期一到星期天的课程，
     * 2.获取课程上课时间
     * 3.根据时间设置闹钟
     */
    private void setAlarm() {

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        MySqliteHelper helper = new MySqliteHelper(getApplicationContext(), Constants.DB_NAME, null, Constants.DB_VERSION);
        List<CourseTableModel> list = helper.queryCourseTableAllData();

        for (int i = 0; i < list.size(); i++) {
            CourseTableModel model = list.get(i);
            if (!StringUtils.isEmpty(model.getClassName())) {
                //得到这是第几节课
                int index = model.getClassIndex();
                UpClassTimeModel upClassTimeModel = helper.queryClassIndexData(index);
                if (upClassTimeModel != null) {
                    long t = getTimeFormModel(upClassTimeModel, model.getDayOfWeek());

                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("course",model.getClassName());
                    intent.putExtra("time",upClassTimeModel.getStartHour()+":"+upClassTimeModel.getStartMinute());

                    Log.e(TAG, "setAlarm: "+model.getClassName() + "  "+upClassTimeModel.getStartHour()+":"+upClassTimeModel.getStartMinute());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), index, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                        alarm.setExact(AlarmManager.RTC_WAKEUP, t, pendingIntent);
                    } else {
                        alarm.set(AlarmManager.RTC_WAKEUP, t, pendingIntent);
                    }

                }

            }

        }


    }

    private long getTimeFormModel(UpClassTimeModel upClassTimeModel, int dayOfWeek) {

        long plusTime = 3600 * 24;
        //提前１５分钟通知
        long reduce = 60*15;
        int hour = upClassTimeModel.getStartHour();
        int minute = upClassTimeModel.getStartMinute();

        int dow = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        //得到今天星期几  因为不是当天 时间就得加  比如今天星期三  但是课程是星期四的  所以闹钟要加上24个小时
        int dayOfWeekToday = transformWeek(dow);
        //采用课程所在的星期 - 今天的星期  例如 今天星期三  但是课程是星期四的  4-3 = 1  就是加上一天的时间
        //但是 如果今天星期三  但是课程是星期一的   那么 1-3+7 = 5  那么就是加上5天
        int cha = dayOfWeek - dayOfWeekToday;
        if (cha >= 0) {
            Calendar calendar = Calendar.getInstance();
            //如果现在是12点，那么12点之前的课程就提醒了 仅限当天

            if (calendar.get(Calendar.HOUR_OF_DAY) > hour) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            } else {
                return -1;
            }
            calendar.set(Calendar.MINUTE, minute);
            if (cha != 0) {
                long p = plusTime * cha;
                long t = calendar.getTimeInMillis() + p - reduce;
                return t;
            }


        } else {
            //小于０就加上７

            Calendar calendar = Calendar.getInstance();
            //如果现在是12点，那么12点之前的课程就提醒了 仅限当天
            cha = cha + 7;
            if (calendar.get(Calendar.HOUR_OF_DAY) > hour) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            } else {
                return -1;
            }
            calendar.set(Calendar.MINUTE, minute);
            if (cha != 0) {
                long p = plusTime * cha;
                long t = calendar.getTimeInMillis() + p - reduce;
                return t;
            }

        }

        return -1;
    }

    /**
     * 这个方法是由于 我们这里第一天是星期一   手机的第一天是星期天
     * <p>
     * 这里转化一下
     * <p>
     * 把手机的转化成我们自己的
     */
    private int transformWeek(int phoneDayOfWeek) {
        int week = 1;

        switch (phoneDayOfWeek) {
            case 1:
                week = 7;
                break;
            case 2:
                week = 1;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 5;
                break;
            case 7:
                week = 6;
                break;
        }

        return week;
    }


}
