package com.shimk.Txgc.networkModel;

import android.media.MediaPlayer;
import android.util.Log;

import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.utils.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.shimk.Txgc.MainActivity.TAG;

public class NetworkLiuyanceContentImpl implements NetworkLiuyanceContent {

//    private requestInterFace mcontenInterface;

    private static NetworkLiuyanceContentImpl netTask = null;

    public NetworkLiuyanceContentImpl(){

;
    }

    public synchronized static NetworkLiuyanceContentImpl getInstance(){
        synchronized (NetworkLiuyanceContentImpl.class){
            if (netTask==null){
                netTask = new NetworkLiuyanceContentImpl();
            }
        }
        return netTask;
    }

//    @Override
//    public void requestGet(String url,requestInterFace requestInterFace) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(mBaseUrl)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiStore api = retrofit.create(ApiStore.class);
//        Call<List<ClassmateContent>> call = api.getAllData(url);
//        call.enqueue(new Callback<List<ClassmateContent>>() {
//            @Override
//            public void onResponse(Call<List<ClassmateContent>> call, Response<List<ClassmateContent>> response) {
//
//                //处理服务返回结果
//                ClassmateContent content = new ClassmateContent();
//                content.setContent(response.toString());
//                requestInterFace.requestSuccStringUser(content);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ClassmateContent>> call, Throwable t) {
//
//                if (call==null){
//                    requestInterFace.requestFailed("");
//
//                }
//                Log.d(TAG, "onFailure: ");
//            }
//        });
//
//
//
//
//
//    }
//
//    @Override
//    public void requestPost(String url) {
//
//    }
//
//    @Override
//    protected void downloadImage(String url) {
//
//    }


    @Override
    public ApiStore getNetWorkData(String baseurl, int isjson) {
        return RetrofitFactory.getInstance().getNetworkData(baseurl, isjson);
    }
}
