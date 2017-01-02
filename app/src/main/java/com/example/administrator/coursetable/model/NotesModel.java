package com.example.administrator.coursetable.model;

/**
 * Created by Administrator on 2017/1/1.
 */

public class NotesModel {

    private String YTD;
    private int weekPosition;
    private String notepad;
    //是否展开
    private boolean isExpan;

    public boolean isExpan() {
        return isExpan;
    }

    public void setExpan(boolean expan) {
        isExpan = expan;
    }

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
