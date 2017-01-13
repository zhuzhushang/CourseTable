package com.example.administrator.coursetable.model;

/**
 * Created by ShaoQuanwei on 2017/1/12.
 */

public class UpClassTimeModel {


    private int id;
    private String startTime;
    private String endTime;

    /*所属类型  晨读、上午、下午、晚上*/
    private int timeType;


    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
