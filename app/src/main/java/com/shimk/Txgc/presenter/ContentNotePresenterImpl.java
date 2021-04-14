package com.shimk.Txgc.presenter;

import com.shimk.Txgc.bean.BackCodeJavaBean;
import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;
import com.shimk.Txgc.bean.EverydayJavaBean;
import com.shimk.Txgc.networkModel.ApiStore;
import com.shimk.Txgc.networkModel.NetworkLiuyanceContentImpl;
import com.shimk.Txgc.utils.Constant;
import com.shimk.Txgc.utils.ShimkLog;
import com.shimk.Txgc.utils.ToMainThread;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ContentNotePresenterImpl implements PresenterInterface.presenter {

    private NetworkLiuyanceContentImpl netTask;
    private PresenterInterface.View viewCallback;

    public ContentNotePresenterImpl(NetworkLiuyanceContentImpl netTask, PresenterInterface.View viewCallback) {
        this.netTask = netTask;
        this.viewCallback = viewCallback;
    }

    @Override
    public void getContent(ClassmateJavabean user, String contentUrl) {

        netTask.getNetWorkData(contentUrl, Constant.JSON_FORMAT).getPostData()
        .compose(ToMainThread.observableToMain())
        .flatMap(new Function<List<BackCodeJavaBean>, ObservableSource<List<BackCodeJavaBean>>>() {
            @Override
            public ObservableSource<List<BackCodeJavaBean>> apply(@NonNull List<BackCodeJavaBean> backCodeJavaBeans) throws Exception {
                for (BackCodeJavaBean backCodeJavaBean:backCodeJavaBeans){
                    if (backCodeJavaBean.getCode()==200){
                        break;
                    }
                    else {
                        return null;
                    }
                }
                return Observable.just(backCodeJavaBeans);
            }
        }).subscribe(new Consumer<List<BackCodeJavaBean>>() {
            @Override
            public void accept(List<BackCodeJavaBean> backCodeJavaBeans) throws Exception {

                for (BackCodeJavaBean backcontent:backCodeJavaBeans){
                    ClassmateContent content = new ClassmateContent();
                    content.setName(backcontent.getName());
                    content.setContent(backcontent.getMessage());
                    content.setPosttime(backcontent.getPosttime());
                    viewCallback.responseFromNetPresenterSucc(content);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                viewCallback.responseFromNetPresenterFailed("Network Error");
            }
        });
    }

    @Override
    public void getEverydayData() {
//        Retrofit builder = new Retrofit.Builder()
//                .baseUrl("https://api.xygeng.cn/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//        ApiStore api = builder.create(ApiStore.class);
//        api.getday().enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                ShimkLog.logd("获取的："+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                ShimkLog.logd("retrofit失败"+t.getMessage());
//            }
//
//
//
//        });



















        netTask.getNetWorkData("https://api.xygeng.cn/one/",Constant.JSON_FORMAT)
        .getEveryDayData()
        .compose(ToMainThread.observableToMain())
        .flatMap(new Function<EverydayJavaBean, ObservableSource<EverydayJavaBean>>() {
            @Override
            public ObservableSource<EverydayJavaBean> apply(@NonNull EverydayJavaBean everydayJavaBean) throws Exception {
                ShimkLog.logd("成功");


                return Observable.just(everydayJavaBean);
            }
        })
        .subscribe(new Consumer<EverydayJavaBean>() {
            @Override
            public void accept(EverydayJavaBean everydayJavaBean) throws Exception {
                ShimkLog.logd(everydayJavaBean.getData().getContent());
                viewCallback.responseFromNetEveryDayPreserterFailed(everydayJavaBean.getData().getContent(),everydayJavaBean.getData().getOrigin());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                ShimkLog.logd(throwable.getMessage());
            }
        });
    }


}
