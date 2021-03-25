package com.shimk.Txgc.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShimkLog{
    public static final String TAG = "2016txgc--log：";
    public static void showToast(String str, Context context){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
    public static void logd(String str){
        Log.d(TAG, str);
    }
    public static void logd(int resid, AppCompatActivity activity){
        Log.d(TAG,activity.getResources().getString(resid));
    }
}
