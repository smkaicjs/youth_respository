package com.shimk.Txgc.network;

import com.shimk.Txgc.bean.BackCodeJavaBean;
import com.shimk.Txgc.bean.ClassmateContent;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiStore {
    @GET("/qbtxt-api.php/")
    Call<List<ClassmateContent>> getAllData(@Query("qq")String account);
    @POST("/")
    Call<String> getPostData();

    @POST("community/api/sysuser/register")
    Call<String> registerpost(@Body RequestBody body);


    @POST("community/api/sysuser/register")
    @FormUrlEncoded
    Call<BackCodeJavaBean> registerpost(@Field("username")String name, @Field("password")String password, @Field("email")String mail
    , @Field("phone")String phone, @Field("student_id")String studentID);

    @POST("community/api/sysuser/login")
    @FormUrlEncoded
    Call<BackCodeJavaBean> loginpost(@FieldMap HashMap<String,String> map);
}