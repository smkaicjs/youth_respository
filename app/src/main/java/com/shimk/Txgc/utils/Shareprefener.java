package com.shimk.Txgc.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Shareprefener {
    public static boolean dealshareprefenerce(Context context, boolean issave, String preferencesFile, String key, boolean value, boolean defaultvalue){
        if (issave){
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key,value);
            editor.apply();
            return false;

        }else {
            return context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE).getBoolean(key,defaultvalue);
        }

    }
    public static String dealshareprefenerce(Context context, boolean issave, String preferencesFile, String key, String value, String defaultvalue){
        if (issave){
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key,value);
            editor.apply();
            return "";

        }else {
            return context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE).getString(key,defaultvalue);
        }

    }

    public static int dealshareprefenerce(Context context, boolean issave, String preferencesFile, String key, int value, int defaultvalue){
        if (issave){
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(key,value);
            editor.apply();
            return 0;

        }else {
            return context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE).getInt(key,defaultvalue);
        }

    }

    public static long dealshareprefenerce(Context context, boolean issave, String preferencesFile, String key, long value, long defaultvalue){
        if (issave){
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(key,value);
            editor.apply();
            return 0;

        }else {
            return context.getSharedPreferences(preferencesFile,Context.MODE_PRIVATE).getLong(key,defaultvalue);
        }

    }

}
