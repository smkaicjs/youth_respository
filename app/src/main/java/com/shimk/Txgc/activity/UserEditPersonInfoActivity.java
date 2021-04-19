package com.shimk.Txgc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.shimk.Txgc.R;
import com.shimk.Txgc.base.BaseActivity;
import com.shimk.Txgc.bean.CurrentUser;
import com.shimk.Txgc.customUI.EditInputCustomEditText;
import com.shimk.Txgc.utils.Constant;
import com.shimk.Txgc.utils.ShimkLog;
import com.shimk.Txgc.utils.TextUtils;

import java.util.zip.Inflater;

/*
 * The Class for user change person info，And what does it show
 *
 *
 * */
public class UserEditPersonInfoActivity extends BaseActivity implements View.OnTouchListener {



    private final int HANDLER_REFRESH_GRADUATE_TIME = 0x10;
    private UserEditPersonInfoActivityHolder mholder = null;
    private AlertDialog choosePhotoSourceAlertdialog = null;

    private TextView mPhotograph,mLocalPhoto,mCancelButton;

    @Override
    protected void init() {
        setContentView(R.layout.activity_user_change_name_password);
        if (null == mholder){
            mholder = new UserEditPersonInfoActivityHolder(this);
        }

        mHandler.sendEmptyMessage(HANDLER_REFRESH_GRADUATE_TIME);
    }
    /*
    * click event dealwith regist
    *
    *
    * */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ShimkLog.logd("deal UserEditPersonInfo Click");
        switch (v.getId()){
            case R.id.comfirm_button:
                if (event.getAction()== KeyEvent.ACTION_UP){
                    ShimkLog.showToast("处理后端密码校验等问题，现在预先处理旧密码",this);
                    doModify();
                }

                break;
            case R.id.user_change_page_user_healder:

                showChoosePhotoDialog();
                break;
            case R.id.local_button:
                //todo
                break;
            case R.id.photograph_button:
                //todo
                break;
            case R.id.cancel_button:
                //todo
                break;
        }
        return false;
    }



    private void showChoosePhotoDialog() {

        TextView localbutton,cancelbutton,protographbutton;
        if (choosePhotoSourceAlertdialog==null){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_dialog_layout,null);
            localbutton = view.findViewById(R.id.local_button);
            cancelbutton = view.findViewById(R.id.cancel_button);
            protographbutton = view.findViewById(R.id.photograph_button);
            localbutton.setOnTouchListener(this);
            cancelbutton.setOnTouchListener(this::onTouch);
            protographbutton.setOnTouchListener(this);
            builder.setView(view);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    choosePhotoSourceAlertdialog = null;
                }
            });
            choosePhotoSourceAlertdialog = builder.create();
            Window window = choosePhotoSourceAlertdialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.choosedialogstyle);
            choosePhotoSourceAlertdialog.show();
        }
    }

    /*
    * Modify user password
    *Read the current user that exists on the current system,
    * verify that the old password is correct,
    * and submit it to the back end.
    *Otherwise do not deal with.
    *
    **/
    private void doModify(){
        try {
            CurrentUser currentUser = CurrentUser.getCurrentUser();
            Boolean res = verifyCurrentUser(currentUser);
            if (res){
                //todo
            }else{
                //todo
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShimkLog.logd(getString(R.string.obtian_currentuser_failed)
            );
        }
    }

    private Boolean verifyCurrentUser(CurrentUser currentUser) {
        String id = mholder.mNameEdit.getText();
        String oldpassword = mholder.mOldPasswordEdit.getText();
        String newpassword = mholder.mNewPasswordEdit.getText();
        if (id.equals(currentUser.getId())){
            String oldpasswordmd5 = TextUtils.decodeMD5(oldpassword);
            if (oldpasswordmd5.equals(currentUser.getPassword())){
                //todo
                return true;
            }else{
                mholder.mOldPasswordEdit.setText("");
                ShimkLog.showToast("旧密码错误",this);

                return false;
            }
        }
        ShimkLog.showToast("学号错误",this);
        mholder.mNameEdit.setText("");
        return false;

    }

    /*
    * handler
    *
    * */
    private Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case HANDLER_REFRESH_GRADUATE_TIME:
                    mholder.timeCustomTextView.setText(countTime());
                    mHandler.sendEmptyMessageDelayed(HANDLER_REFRESH_GRADUATE_TIME,1000);
                    break;

            }
            return false;
        }
    });

    private String countTime() {
        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "countTime: "+currentTime);
        Long sec = (currentTime- Constant.graduateTime)/1000L;
        int years = (int) (sec/(60*60*24*365));
        int mounth = (int) (sec%(60*60*24*365)/(30*24*60*60));
        int day = (int) (sec%(60*60*24*365)%(30*24*60*60)/(60*60*24));
        int hour = (int) (sec%(60*60*24*365)%(30*24*60*60)%(60*60*24)/(60*60));
        int minute = (int) (sec%(60*60*24*365)%(30*24*60*60)%(60*60*24)%(60*60)/60);
        int secs = (int) (sec%(60*60*24*365)%(30*24*60*60)%(60*60*24)%(60*60)%60);


        return years+"年"+ " " + mounth+"个月"+day+"天"+hour+"个小时"+"零"+minute+"分钟"+secs+"秒";
    }

    @Override
    protected void onDestroy() {

        mHandler.removeCallbacksAndMessages(null);
        mHandler=null;
        super.onDestroy();
    }
}