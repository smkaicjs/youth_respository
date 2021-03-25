package com.shimk.Txgc.bean;

public class BackCodeJavaBean {
    private String message;
    private Integer code;

    public BackCodeJavaBean(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
