package com.example.administrator.coursetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.interfaces.OnUpClassTimeSetListener;
import com.example.administrator.coursetable.utils.ScreenUtils;
import com.example.administrator.coursetable.utils.ToastUtils;

/**
 * Created by ShaoQuanwei on 2017/1/16.
 */

public class SetUpClassTimeDialog extends Dialog implements View.OnClickListener,TimePicker.OnTimeChangedListener{

    private Context context;
    private int tvIndex;
    private OnUpClassTimeSetListener onUpClassTimeSetListener;
    private TimePicker up_class_time_start,up_class_time_end;
    private TextView tv_left,tv_right;
    private View view;
    private String startTime,endTime;

    private int startHour,endHour,startMinute,endMinute;

    public SetUpClassTimeDialog(Context context) {
        super(context);



    }

    public SetUpClassTimeDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    public SetUpClassTimeDialog(Context context,OnUpClassTimeSetListener onUpClassTimeSetListener,int tvIndex,int startHour,int startMinute,int endHour,int endMinute) {
        super(context,R.style.MyDialogStyle);

        this.context = context;
        this.onUpClassTimeSetListener = onUpClassTimeSetListener;
        this.tvIndex = tvIndex;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;

        init(context);

    }

    private void init(Context context) {


        viewInit(context);
        dataInit();
        eventInit();
        dialogInit();


    }



    private void viewInit(Context context) {

         view = LayoutInflater.from(context).inflate(R.layout.dialog_set_up_class_time,null);
        up_class_time_start = (TimePicker) view.findViewById(R.id.up_class_time_start);
        up_class_time_end = (TimePicker) view.findViewById(R.id.up_class_time_end);
        tv_left = (TextView) view.findViewById(R.id.tv_left);
        tv_right = (TextView) view.findViewById(R.id.tv_right);

    }

    private void dataInit() {

        up_class_time_end.setIs24HourView(true);
        up_class_time_start.setIs24HourView(true);

        up_class_time_start.setCurrentHour(startHour);
        up_class_time_start.setCurrentMinute(startMinute);
        up_class_time_end.setCurrentHour(endHour);
        up_class_time_end.setCurrentMinute(endMinute);


        startTime = formatTime(startHour)+":"+formatTime(startMinute);
        endTime = formatTime(endHour)+":"+formatTime(endMinute);

    }

    private void eventInit() {

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);

        up_class_time_end.setOnTimeChangedListener(this);
        up_class_time_start.setOnTimeChangedListener(this);

    }

    private void dialogInit() {


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context);
        params.height = ScreenUtils.getScreenHeight(context);
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);
        setContentView(view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.tv_left:

                dismiss();

                break;
            case R.id.tv_right:

                if(checkTime())
                {
                    onUpClassTimeSetListener.OnUpClassTimeSet(tvIndex,startHour,startMinute,endHour,endMinute);
                    dismiss();
                }

                break;

        }


    }

    private boolean checkTime() {

        if(endHour < startHour)
        {
            ToastUtils.show(context,"开始时间应小于结束时间");
            return false;
        }else if(endHour == startHour)
        {
            if(endMinute < startMinute)
            {
                ToastUtils.show(context,"开始时间应小于结束时间");
                return false;
            }
        }

        return true;
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {

        switch (timePicker.getId())
        {
            case R.id.up_class_time_start:

                startHour = i;
                startMinute = i1;
                startTime = formatTime(i)+":"+formatTime(i1);

                break;
            case R.id.up_class_time_end:

                endHour = i;
                endMinute = i1;
                endTime = formatTime(i)+":"+formatTime(i1);
                break;
        }
    }

    /**
     * @param i
     * @return  格式化时间，不足两位补0
     */
    private String formatTime(int i)
    {

        if(i < 10)
        {
            return "0"+i;
        }

        return ""+i;
    }


}
