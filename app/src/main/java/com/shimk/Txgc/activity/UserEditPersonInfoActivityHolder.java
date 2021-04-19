package com.shimk.Txgc.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.shimk.Txgc.R;
import com.shimk.Txgc.bean.CurrentUser;
import com.shimk.Txgc.customUI.CircleImageView;
import com.shimk.Txgc.customUI.EditInputCustomEditText;
import com.shimk.Txgc.customUI.GraduateTimeCustomTextView;

public class UserEditPersonInfoActivityHolder {
    private UserEditPersonInfoActivity mActivity;
    protected EditInputCustomEditText mNameEdit,mOldPasswordEdit,mNewPasswordEdit;
    protected Button mComfirmModifyButton;

    protected CircleImageView userHeaderPhoto;

    protected GraduateTimeCustomTextView timeCustomTextView;

    public UserEditPersonInfoActivityHolder(UserEditPersonInfoActivity mActivity) {
        this.mActivity = mActivity;
        findViews();
        registTextChangeListener();
        registOnTouchListener();

    }

    private void registOnTouchListener() {
        mComfirmModifyButton.setOnTouchListener(mActivity);
        userHeaderPhoto.setOnTouchListener(mActivity);
    }

    private void findViews(){
        mNameEdit = mActivity.findViewById(R.id.User_change_name);
        mOldPasswordEdit = mActivity.findViewById(R.id.User_change_password_old);
        mNewPasswordEdit = mActivity.findViewById(R.id.User_change_password_new);
        mComfirmModifyButton = mActivity.findViewById(R.id.comfirm_button);
        timeCustomTextView = mActivity.findViewById(R.id.custom_textview_distance_time);
        userHeaderPhoto = mActivity.findViewById(R.id.user_change_page_user_healder);
        userHeaderPhoto.setImageBitmap(CurrentUser.getCurrentUser().getCurrentUserHeaderPhoto(mActivity));
        mNameEdit.setHint("学号");
        mOldPasswordEdit.setHint("旧密码");
        mNewPasswordEdit.setHint("新密码");

    }
    private void registTextChangeListener(){
        mNameEdit.setTextChangeLisener(nameTextWatcher);
        mOldPasswordEdit.setTextChangeLisener(oldPasswordTextWatcher);
        mNewPasswordEdit.setTextChangeLisener(newPasswordTextWatcher);
    }
    TextWatcher nameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int len = s.toString().length();
            if (len!= 12){
                mNameEdit.setErrorInfo(true,R.string.text_id_length);
            }else{
                mNameEdit.setErrorInfo(false,"");
            }
        }
    };
    TextWatcher oldPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int len = s.toString().length();
            if (len>10||len<6){
                mOldPasswordEdit.setErrorInfo(true,R.string.text_password_length);
            }else{
                mOldPasswordEdit.setErrorInfo(false,"");
            }
        }
    };
    TextWatcher newPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int len = s.toString().length();
            if (len>10||len<6){
                mNewPasswordEdit.setErrorInfo(true,R.string.text_password_length);

            }else{
                mNewPasswordEdit.setErrorInfo(false,"");
            }
        }
    };
}
