package com.example.administrator.coursetable.model;

/**
 * Created by ShaoQuanwei on 2017/2/13.
 */

public class HideWeekModel {

    private String whatDay;
    /*1是选中  0是未选中*/
    private String isCheck = "1";


    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getWhatDay() {
        return whatDay;
    }

    public void setWhatDay(String whatDay) {
        this.whatDay = whatDay;
    }
}
