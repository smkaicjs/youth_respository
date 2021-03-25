package com.shimk.Txgc.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface retr {
    @GET("api/rand.music")
    Call<String> getdata(@Query("sort") String id,@Query("format")String name);
    @GET("/")
    Call<String> getbaidu();
}
