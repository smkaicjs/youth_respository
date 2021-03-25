package com.shimk.Txgc.presenter;

import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;

public interface PresenterInterface {
    interface presenter{
        void getContent(ClassmateJavabean user,String contentUrl);
    }
    interface View extends BaseViewInterface<presenter>{


        void requestSuccImage();

        void requestFailed(String error);

        void responseFromNetPresenterSucc(ClassmateContent content);
        void responseFromNetPresenterFailed(String failedStr);


    }
}
