package com.example.administrator.coursetable.model;

/**
 * Created by Administrator on 2017/1/1.
 */

public class CourseListDataModel {

    private String YTD;
    private int weekPosition;
    private String notepad;

    public String getYTD() {
        return YTD;
    }

    public void setYTD(String YTD) {
        this.YTD = YTD;
    }

    public String getNotepad() {
        return notepad;
    }

    public void setNotepad(String notepad) {
        this.notepad = notepad;
    }

    public int getWeekPosition() {
        return weekPosition;
    }

    public void setWeekPosition(int weekPosition) {
        this.weekPosition = weekPosition;
    }
}
