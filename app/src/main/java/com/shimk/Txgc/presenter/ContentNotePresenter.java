package com.shimk.Txgc.presenter;

import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;
import com.shimk.Txgc.network.NetworkLiuyanceContent;
import com.shimk.Txgc.network.requestInterFace;

public class ContentNotePresenter implements PresenterInterface.presenter, requestInterFace {

    private NetworkLiuyanceContent netTask;
    private PresenterInterface.View viewCallback;

    public ContentNotePresenter(NetworkLiuyanceContent netTask, PresenterInterface.View viewCallback) {
        this.netTask = netTask;
        this.viewCallback = viewCallback;
    }

    @Override
    public void getContent(ClassmateJavabean user, String contentUrl) {

        netTask.requestGet(contentUrl,this);
    }

    @Override
    public void requestSuccStringUser(ClassmateContent userstr) {
        viewCallback.responseFromNetPresenterSucc(userstr);
    }




    @Override
    public void requestSuccImage() {

    }

    @Override
    public void requestFailed(String error) {

        viewCallback.responseFromNetPresenterFailed(error);
    }
}
