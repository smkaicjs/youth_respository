package com.shimk.Txgc.bean;

public class CurrentUser {
    private static CurrentUser user=null;
    private String Id;
    private String password;
    private CurrentUser(String StudentId,String Password){
        this.Id = StudentId;
        this.password = Password;
    }
    public static CurrentUser getCurrentUser(){
        if (user==null){
            return null;
        }
        return user;
    }

    public static void createCurrentUser(String id,String password){
        if (user==null){
            user = new CurrentUser(id,password);
        }
    }
}
