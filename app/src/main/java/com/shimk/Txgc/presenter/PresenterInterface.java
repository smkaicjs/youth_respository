package com.shimk.Txgc.presenter;

import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.bean.ClassmateJavabean;

public interface PresenterInterface {
    interface presenter{
        void getContent(ClassmateJavabean user,String contentUrl);
        void getEverydayData();
    }
    interface View extends BaseViewInterface<presenter>{




        void responseFromNetPresenterSucc(ClassmateContent content);
        void responseFromNetPresenterFailed(String failedStr);
        void responseFromNetEveryDayPreserterFailed(String string,String source);


    }
}
