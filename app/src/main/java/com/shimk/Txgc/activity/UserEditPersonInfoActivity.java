package com.shimk.Txgc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shimk.Txgc.R;
import com.shimk.Txgc.base.BaseActivity;
import com.shimk.Txgc.customUI.EditInputCustomEditText;
/*
 * The Class for user change person info，And what does it show
 *
 *
 * */
public class UserEditPersonInfoActivity extends BaseActivity {



    private UserEditPersonInfoActivityHolder mholder = null;

    @Override
    protected void init() {
        setContentView(R.layout.activity_user_change_name_password);
        if (null == mholder){
            mholder = new UserEditPersonInfoActivityHolder(this);
        }
    }
}