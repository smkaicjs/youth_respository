package com.shimk.Txgc.utils;

import com.shimk.Txgc.networkModel.ApiStore;

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
        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(jsonOrString==Constant.JSON_FORMAT? GsonConverterFactory.create(): ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    public ApiStore getNetworkData(String baseurl, int isjson){
        return getRetrofit(baseurl,isjson).create(ApiStore.class);

    }


}
