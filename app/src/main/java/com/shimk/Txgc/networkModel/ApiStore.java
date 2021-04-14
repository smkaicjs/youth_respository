package com.shimk.Txgc.networkModel;

import com.shimk.Txgc.bean.BackCodeJavaBean;
import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.EverydayJavaBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
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
    @GET("/")
    Observable<List<BackCodeJavaBean>> getPostData();




    //注册接口
    @POST("community/api/sysuser/register")
    @FormUrlEncoded
    Call<BackCodeJavaBean> registerpost(@Field("username")String name, @Field("password")String password, @Field("email")String mail
    , @Field("phone")String phone, @Field("student_id")String studentID);

    //登录接口
    @POST("community/api/sysuser/login")
    @FormUrlEncoded
    Call<BackCodeJavaBean> loginpost(@FieldMap HashMap<String,String> map);

    //每日一句接口
    @Headers({
        "authority: api.xygeng.cn",
    "method: GET",
    "path: /one",
    "scheme: https",
    "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "accept-encoding: gzip, deflate, br",
    "accept-language: zh-CN,zh;q=0.9",
    "cache-control: max-age=0",
    "sec-ch-ua-mobile: ?0",
    "sec-fetch-dest: document",
    "sec-fetch-mode: navigate",
    "sec-fetch-site: none",
    "sec-fetch-user: ?1",
    "upgrade-insecure-requests: 1",
    "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36",
    })
    @GET("/one")
    Observable<EverydayJavaBean> getEveryDayData();



    @GET("/")
    Call<String> getday();
}
