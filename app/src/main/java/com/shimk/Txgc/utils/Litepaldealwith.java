package com.shimk.Txgc.utils;

import android.util.Log;

import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import static com.shimk.Txgc.utils.ShimkLog.TAG;

public class Litepaldealwith {
    public static ClassmateJavabean getClassmateJavabean(int id){

        ClassmateJavabean res =LitePal.find(ClassmateJavabean.class,id);
        return res;
    }
    public static void addClassmateJavabean(String name,int studentid,Long lastlogin,boolean isCreate){

        if (isCreate){//first create
            ClassmateJavabean classmateJavabean = new ClassmateJavabean();
            classmateJavabean.setName(name);
            classmateJavabean.setStudentID(studentid);
            classmateJavabean.setLastlogintime(lastlogin);
            classmateJavabean.save();
            return;
        }
        //update logintime
        ClassmateJavabean upclassmate = LitePal.find(ClassmateJavabean.class,0);
        upclassmate.setLastlogintime(lastlogin);
        upclassmate.save();

    }

    public static List<ClassmateContent> getAllClassmatecontent(){

        List<ClassmateContent> list = LitePal.findAll(ClassmateContent.class);
        return list;
    }
    public static void addClassmatecontent(String name,String posttiem,String contents){

        ClassmateContent content = new ClassmateContent();
        content.setName(name);
        content.setPosttime(posttiem);
        content.setContent(contents);
        Log.d(TAG, "addClassmatecontent: 保存调用");
        content.save();
        content.isSaved();

    }
    public static void addClassmatecontent(ClassmateContent content){
        content.save();
    }
    public static boolean isExistClassmateContent(String name,String posttime){
        if (name==null||posttime==null){
            return false;
        }
        List<ClassmateContent> list = LitePal.findAll(ClassmateContent.class);
        for (ClassmateContent content:list){
            if (content.getName().equals(name)&&content.getPosttime().equals(posttime)){
                return true;
            }
        }
        return false;

    }

    public static void delClassmateContent(String name,String posttime){
        LitePal.deleteAll(ClassmateContent.class,"name=? and posttime=?",name,posttime);
    }
}
