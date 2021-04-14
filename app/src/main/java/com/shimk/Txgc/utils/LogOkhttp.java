package com.shimk.Txgc.utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogOkhttp implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        ShimkLog.logd(String.format("请求地址：%s请求参数headers：%s", request.url(),request.headers()));
        ShimkLog.logd("请求头："+request.headers().toString());
        Response response = chain.proceed(request);
        ShimkLog.logd(String.format("返回码：%d",response.code()));
        return response;
    }
}
