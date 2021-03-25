package com.shimk.Txgc.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public abstract class BaseNetwork {
    public static final String mBaseUrl = "http://api.qb-api.com";

    public abstract void requestGet(String url,requestInterFace requestInterFace);
    public abstract void requestPost(String url);
    protected abstract void downloadImage(String url);


}
