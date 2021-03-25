package com.shimk.Txgc.base;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.shimk.Txgc.utils.ShimkLog;

public class ActManager {
    public static List<AppCompatActivity> mList = new ArrayList<>();
    private ActManager actManager;
    public ActManager getIntence(){
        if (null==actManager){
            actManager = new ActManager();

        }
        return actManager;
    }
    public static void addActivity(AppCompatActivity activity){
        mList.add(activity);


    }
    public static void removeActivity(AppCompatActivity activity){
        mList.remove(activity);
    }

    public static void closeApp(){
        for (AppCompatActivity activity:mList){
            activity.finish();
            ShimkLog.logd("关闭所有activity");
        }
    }
}
