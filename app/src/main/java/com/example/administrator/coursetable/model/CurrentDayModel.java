package com.example.administrator.coursetable.model;

/**
 * Created by Administrator on 2017/1/2.
 */

public class CurrentDayModel {

    private String class_name;
    private String up_class_place;
    private String up_class_time;
    private String startTime;
    private String endTime;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getUp_class_place() {
        return up_class_place;
    }

    public void setUp_class_place(String up_class_place) {
        this.up_class_place = up_class_place;
    }

    public String getUp_class_time() {
        return up_class_time;
    }

    public void setUp_class_time(String up_class_time) {
        this.up_class_time = up_class_time;
    }
}
