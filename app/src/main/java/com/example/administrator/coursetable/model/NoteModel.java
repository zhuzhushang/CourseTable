package com.example.administrator.coursetable.model;

/**
 * Created by ShaoQuanwei on 2017/1/26.
 */

public class NoteModel {

    private int id;
    private long time;
    private String note;
    //是否展开
    private boolean isExpan;

    public boolean isExpan() {
        return isExpan;
    }

    public void setExpan(boolean expan) {
        isExpan = expan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
