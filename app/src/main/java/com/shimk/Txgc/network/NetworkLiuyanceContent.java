package com.shimk.Txgc.network;

import android.util.Log;

import com.shimk.Txgc.bean.ClassmateContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.shimk.Txgc.MainActivity.TAG;

public class NetworkLiuyanceContent extends BaseNetwork {

//    private requestInterFace mcontenInterface;

    private static NetworkLiuyanceContent netTask = null;

    public NetworkLiuyanceContent(){

;
    }

    public static NetworkLiuyanceContent getInstance(){
        if (null==netTask){
            netTask = new NetworkLiuyanceContent();
        }
        return netTask;
    }

    @Override
    public void requestGet(String url,requestInterFace requestInterFace) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiStore api = retrofit.create(ApiStore.class);
        Call<List<ClassmateContent>> call = api.getAllData(url);
        call.enqueue(new Callback<List<ClassmateContent>>() {
            @Override
            public void onResponse(Call<List<ClassmateContent>> call, Response<List<ClassmateContent>> response) {

                //处理服务返回结果
                ClassmateContent content = new ClassmateContent();
                content.setContent(response.toString());
                requestInterFace.requestSuccStringUser(content);

            }

            @Override
            public void onFailure(Call<List<ClassmateContent>> call, Throwable t) {

                if (call==null){
                    requestInterFace.requestFailed("");

                }
                Log.d(TAG, "onFailure: ");
            }
        });





    }

    @Override
    public void requestPost(String url) {

    }

    @Override
    protected void downloadImage(String url) {

    }


}
