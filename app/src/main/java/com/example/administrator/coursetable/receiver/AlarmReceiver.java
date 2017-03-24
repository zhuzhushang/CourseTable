package com.example.administrator.coursetable.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.activity.MainActivity;

/**
 * Created by Administrator on 2017/3/12.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String course = intent.getStringExtra("course");
        String time = intent.getStringExtra("time");
        Log.e("AlarmReceiver", "onReceive: "+time+"  "+course );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("您有一条上课消息");
        builder.setContentTitle(course);
        builder.setContentText("上课时间："+time);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(R.mipmap.ic_launcher,notification);


    }
}
