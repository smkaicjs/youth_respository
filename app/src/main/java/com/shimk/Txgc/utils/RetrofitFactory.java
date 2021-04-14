package com.shimk.Txgc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shimk.Txgc.networkModel.ApiStore;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory ourInstance;

    public synchronized static RetrofitFactory getInstance() {
        if (null == ourInstance) {
            synchronized (RetrofitFactory.class) {
                if (ourInstance == null) {
                    ourInstance = new RetrofitFactory();
                }
            }
        }
        return ourInstance;
    }


    private static Retrofit retrofit=null;
    public Retrofit getRetrofit(String baseurl,int jsonOrString){
        if (null ==retrofit){
            retrofit = null;
        }
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogOkhttp())
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client)
                .addConverterFactory(jsonOrString==Constant.JSON_FORMAT? GsonConverterFactory.create(): ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    public ApiStore getNetworkData(String baseurl, int isjson){
        return getRetrofit(baseurl,isjson).create(ApiStore.class);

    }


}
