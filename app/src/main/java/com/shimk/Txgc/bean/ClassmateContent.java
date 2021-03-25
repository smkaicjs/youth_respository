package com.shimk.Txgc.bean;

import org.litepal.crud.LitePalSupport;

public class ClassmateContent extends LitePalSupport {
    private String name,posttime,content;

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    private int backCode;

    public ClassmateContent() {
    }

    public ClassmateContent(String name, String posttime, String content) {
        this.name = name;
        this.posttime = posttime;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
