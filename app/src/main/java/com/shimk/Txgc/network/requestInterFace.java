package com.shimk.Txgc.network;

import com.shimk.Txgc.bean.ClassmateContent;

public interface requestInterFace {
        void requestSuccStringUser(ClassmateContent userstr);

        void requestSuccImage();
        void requestFailed(String error);

}
