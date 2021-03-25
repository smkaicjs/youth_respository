package com.shimk.Txgc.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.shimk.Txgc.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.shimk.Txgc.utils.ShimkLog;

public class NetWorkSrevices extends Service {

    public static final String TAG = "NetworkServices";
    private mybinder binder = new mybinder();
    @Override
    public void onCreate() {
        super.onCreate();
        ShimkLog.logd(getApplicationContext().getString(R.string.network_service_create));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getApplicationContext().getString(R.string.network_service_destory));
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public class mybinder extends Binder{


        public String[] requestService(String url){


            final String[] res = {null};
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d(TAG, "onFailure: request failed");
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    String resstr = response.body().string();
                    res[0] = resstr;
                    Log.d(TAG, "onResponse: 返回字符串：："+resstr);


                }
            });
            return res;
        }
    }
}
