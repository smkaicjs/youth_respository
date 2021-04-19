package com.shimk.Txgc.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shimk.Txgc.R;
import com.shimk.Txgc.utils.ShimkLog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CurrentUser {
    private static CurrentUser user=null;
    private String Id;
    private String password;
    private String userHeaderPath;
    private CurrentUser(String StudentId,String Password,String userHeaderPath){
        this.Id = StudentId;
        this.password = Password;
        this.userHeaderPath = userHeaderPath;
    }



    public String getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public static CurrentUser getCurrentUser(){
        if (user==null){
            return null;
        }
        return user;
    }

    public static void createCurrentUser(String id,String password,String userHeaderPath){
        if (user==null){
            user = new CurrentUser(id,password,userHeaderPath);
        }
    }
    public Bitmap getCurrentUserHeaderPhoto(Context context){
        if (userHeaderPath==null){
            return BitmapFactory.decodeResource(context.getResources(),R.drawable.userheaderphoto);
        }else{
            try {
                FileInputStream inputStream = new FileInputStream(userHeaderPath);
                return BitmapFactory.decodeStream(inputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ShimkLog.logd("解析本地圖片錯誤");
                return BitmapFactory.decodeResource(context.getResources(),R.drawable.userheaderphoto);
            }
        }
    }

    public void setUserHeaderPath(String userHeaderPath){
        this.userHeaderPath = userHeaderPath;
        //todo 还需要提交至网络存储
    }
}
