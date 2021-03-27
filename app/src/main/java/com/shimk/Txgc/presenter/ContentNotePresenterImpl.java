package com.shimk.Txgc.presenter;

import com.shimk.Txgc.bean.BackCodeJavaBean;
import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;
import com.shimk.Txgc.networkModel.NetworkLiuyanceContentImpl;
import com.shimk.Txgc.utils.Constant;
import com.shimk.Txgc.utils.ToMainThread;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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


}
