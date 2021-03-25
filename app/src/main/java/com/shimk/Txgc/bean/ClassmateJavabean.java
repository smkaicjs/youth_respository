package com.shimk.Txgc.bean;

import org.litepal.crud.LitePalSupport;

public class ClassmateJavabean extends LitePalSupport {
    private int id;
    private String name;
    private int studentID;
    private String NumberPhone;
    private String Email;

    private long lastlogintime;

    public long getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(long lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
