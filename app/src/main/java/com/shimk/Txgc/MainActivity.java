package com.shimk.Txgc;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.shimk.Txgc.activity.contentActivity_note;
import com.shimk.Txgc.base.BaseActivity;

import com.shimk.Txgc.bean.BackCodeJavaBean;
import com.shimk.Txgc.customUI.AnimCustonImage;
import com.shimk.Txgc.network.ApiStore;
import com.shimk.Txgc.services.NetWorkSrevices;
import com.shimk.Txgc.utils.Constant;
import com.shimk.Txgc.utils.Shareprefener;
import com.shimk.Txgc.utils.ShimkLog;

import java.util.HashMap;

import okhttp3.OkHttp;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends BaseActivity {


    private EditText username,userpassword;
    private CoordinatorLayout coordinatorLayout;
    private TextInputLayout inputEditTextname,inputEditTextpassword;
    private AnimCustonImage mLoadingIcon;
    private TextView registerText;
    private Button loginButton;
    private boolean loginState = false;
    public static final String sharefilename = "MAINSHAREPREFERENCES";
    public static final String logintime = "LOGINTIME";
    public static final String logincerity = "LOGIN_verification";

    private NetWorkSrevices.mybinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (NetWorkSrevices.mybinder) service;
            ShimkLog.logd("Service aleady connect");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    public static final String TAG = "ShiMingKun";
    @Override
    protected void init() {
        setContentView(R.layout.activity_main);
        skipLogin();
        findview();
        Intent intent = new Intent(MainActivity.this,NetWorkSrevices.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        Log.d(TAG, "init: 服务步骤执行");

        //test




    }
    private void findview(){
        username = findViewById(R.id.username);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        username.addTextChangedListener(nameWatcher);
        userpassword = findViewById(R.id.userpassword);
        loginButton = findViewById(R.id.userlogin_button);
        mLoadingIcon = findViewById(R.id.login_loading);
        mLoadingIcon.setVisibility(View.GONE);
        loginButton.setOnClickListener(buttonclicklistener);
        inputEditTextname = findViewById(R.id.textinput_name);
        inputEditTextpassword = findViewById(R.id.textinput_passworld);
        userpassword.addTextChangedListener(passworldWatcher);
        registerText = findViewById(R.id.user_register);
        registerText.setOnClickListener(textonClicklistener);


    }

    /*用户注册dialog组件 start*/


    private EditText mNameEditView,mStudentIDEditview,mPhoneEditview,mEmailEditview,mPasswordEditview;
    private TextInputLayout mInputNmae,mInputID,mInputPhone,mInputEmail,mInputPassword;
    private Button mRegisterButton;
    private AlertDialog mRegisterAlertDialog;
    private TextView.OnClickListener textonClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.register_dialog,null);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ShimkLog.logd("screen width："+dm.widthPixels+";screen heigth:"+dm.heightPixels);
            layoutParams.height = (int) (dm.heightPixels*0.8);
            layoutParams.width = (int) (dm.widthPixels*0.8);
            view.setLayoutParams(layoutParams);
            findRegisterView(view);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(view);
            mRegisterAlertDialog = builder.create();
            mRegisterAlertDialog.show();
        }
    };

    private void findRegisterView(View view){

        mNameEditView = view.findViewById(R.id.register_name);
        mStudentIDEditview = view.findViewById(R.id.register_studentID);
        mPasswordEditview = view.findViewById(R.id.register_password);
        mEmailEditview = view.findViewById(R.id.register_email);
        mPhoneEditview = view.findViewById(R.id.register_phone);
        mRegisterButton = view.findViewById(R.id.register_button);

        mInputPhone =view.findViewById(R.id.register_textinput_phone);
        mInputPassword =view.findViewById(R.id.register_textinput_password);
        mInputEmail =view.findViewById(R.id.register_textinput_email);
        mInputID =view.findViewById(R.id.register_textinput_studentID);
        mInputNmae =view.findViewById(R.id.register_textinput_name);

        mNameEditView.addTextChangedListener(textWatchername);
        mPasswordEditview.addTextChangedListener(textWatcherpassword);
        mStudentIDEditview.addTextChangedListener(textWatcherstudentid);
        mPhoneEditview.addTextChangedListener(textWatcherphone);
        mEmailEditview.addTextChangedListener(textWatcheremail);
        mRegisterButton.setOnClickListener(buttonclicklistener);
    }

    private TextWatcher textWatchername = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int len = s.toString().length();
            if (len>10||len<2){
                mInputNmae.setErrorEnabled(true);
                mInputNmae.setError("限制长度为2~10位");
            }else {
                mInputNmae.setErrorEnabled(false);
            }

        }
    };

    private TextWatcher textWatcherstudentid = new TextWatcher() {
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
                mInputID.setErrorEnabled(true);
                mInputID.setError("学号长度为12位");
            }else{
                mInputID.setErrorEnabled(false);
            }

        }
    };

    private TextWatcher textWatcherpassword = new TextWatcher() {
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
                mInputPassword.setErrorEnabled(true);
                mInputPassword.setError("密码长度限制为6~10位");
            }else{
                mInputPassword.setErrorEnabled(false);
            }
        }
    };

    private TextWatcher textWatcherphone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            int len = s.toString().length();
            if (len!=11){
                mInputPhone.setErrorEnabled(true);
                mInputPhone.setError("请输入11位手机号码");

            }else
            {
                mInputPhone.setErrorEnabled(false);
            }

        }
    };

    private TextWatcher textWatcheremail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            mInputEmail.setErrorEnabled(true);
            mInputEmail.setError("可用邮箱地址");

        }
    };


    /*注册dialog end*/

    private TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public static final int nameLenthLimit = 12;
        @Override
        public void afterTextChanged(Editable s) {


            String s1 = s.toString();
            int lenth = s1.length();
            if (lenth>nameLenthLimit){
                inputEditTextname.setErrorEnabled(true);

                inputEditTextname.setError("学号长度为12位");
                return;
            }else {
                inputEditTextname.setErrorEnabled(false);

            }
            try {
                String ss = s.toString();
                if (ss.equals("")){
                    return;
                }
                long a= Long.parseLong(ss);
                inputEditTextname.setErrorEnabled(false);
            } catch (NumberFormatException e) {
                inputEditTextname.setError("学号输入类型错误");
                inputEditTextname.setErrorEnabled(true);
                e.printStackTrace();


            }
        }
    };

    private TextWatcher passworldWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {

            String ss = String.valueOf(s);
            int count = ss.length();
            if (count>=6&&count<=10||count==0){
                inputEditTextpassword.setErrorEnabled(false);
                return;
            }
            else {
                inputEditTextpassword.setErrorEnabled(true);
                inputEditTextpassword.setError("密码位数错误，密码长度应为6-10位");
            }
//            try {
//                String ss = s.toString();
//                if (ss.equals("")){
//                    return;
//                }
//                long a= Long.parseLong(ss);
//                inputEditTextname.setErrorEnabled(false);
//            } catch (NumberFormatException e) {
//                inputEditTextname.setError("学号输入类型错误");
//                inputEditTextname.setErrorEnabled(true);
//                e.printStackTrace();
//
//
//            }
        }
    };

    private Button.OnClickListener buttonclicklistener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.userlogin_button:
                    try {
                        Long name = Long.valueOf(String.valueOf(username.getText()));
                        String password = String.valueOf(userpassword.getText());
                        mLoadingIcon.setVisibility(View.VISIBLE);
                        mLoadingIcon.startAnim();
                        dealLogin(name, password);

//                        if (loginres) {
//                            Intent intent = new Intent(MainActivity.this, contentActivity_note.class);
//                            intent.putExtra("login", sharefilename);
//                            startActivity(intent);
//                            Shareprefener.dealshareprefenerce(MainActivity.this, true, sharefilename, logincerity, true, false);
//                            long currentTime = System.currentTimeMillis();
//                            Shareprefener.dealshareprefenerce(MainActivity.this, true, sharefilename, logintime, currentTime, 0);
//                        }
                    } catch (NumberFormatException e) {
                        showSnackBar("账户或密码错误 ", coordinatorLayout);
                        mLoadingIcon.stopAnim();
                        e.printStackTrace();
                    }
                    break;
                case R.id.register_button:
                    try {
                        String name = String.valueOf(mNameEditView.getText());
                        if (name.length()>10||name.length()<2){
                            showToast("用户名格式错误");
                            break;
                        }
                        String password = String.valueOf(mPasswordEditview.getText());
                        if (password.length()>10||password.length()<6){
                            showToast("密码格式错误");
                            break;
                        }
                        String phone = String.valueOf(mPhoneEditview.getText());
                        if (phone.length()!=11){
                            showToast("手机号格式错误");
                            break;
                        }
                        String email = String.valueOf(mEmailEditview.getText());
                        String studentID = String.valueOf(mStudentIDEditview.getText());
                        String[] studentidlist = getResources().getStringArray(R.array.studentnumber);
                        for (String id:studentidlist){
                            if (id.equals(studentID)){
                                HashMap<String, String> map = new HashMap<>();
                                map.put("email", email);
                                map.put("username", name);
                                map.put("phone", phone);
                                map.put("password", password);
                                map.put("studentid", studentID);
                                Gson json = new Gson();
                                String strJson = json.toJson(map);

                                ShimkLog.logd(strJson);
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Constant.BaseUrl)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                ApiStore api = retrofit.create(ApiStore.class);
                                Call<BackCodeJavaBean> cas = api.registerpost(name, password, email, phone, studentID);
                                cas.enqueue(new Callback<BackCodeJavaBean>() {
                                    @Override
                                    public void onResponse(Call<BackCodeJavaBean> call, Response<BackCodeJavaBean> response) {
                                        assert response.body() != null;
                                        if(response.body().getCode()==200){
                                            showToast("注册成功，请登录");
                                            mRegisterAlertDialog.dismiss();
                                        }else {
                                            showToast("注册失败"+response.body().getMessage());
                                        }
                                        ShimkLog.logd(String.valueOf(response.body().getCode()));

                                    }

                                    @Override
                                    public void onFailure(Call<BackCodeJavaBean> call, Throwable t) {

                                        showToast("注册失败");
                                        ShimkLog.logd("请求失败"+t);
                                    }
                                });
                                return;
                            }
                        }
                        showToast("学号错误，无此学号");

//                        Call<String> call = api.registerpost(RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strJson));
//                        call.enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                ShimkLog.logd("市民卡"+response.body().toString());
//                                mRegisterAlertDialog.dismiss();
//                            }
//
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//
//                                ShimkLog.logd("请求post失败");
//                                ShimkLog.logd(call.toString());
//
//                            }
//                        });
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
//            if(!loginState){
//                Intent intent = new Intent(MainActivity.this,NetWorkSrevices.class);
//                startService(intent);
//                bindService(intent,connection,0);
//                return;
//            }



        }
    };

    private void showSnackBar(String s,View view){
        Snackbar.make(view,s, BaseTransientBottomBar.LENGTH_INDEFINITE).setAction("点击重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userpassword.setText("");
            }
        }).show();
    }

    private void skipLogin(){
        long lastLoginTime = Shareprefener.dealshareprefenerce(MainActivity.this,
                false,sharefilename,logintime,0, (long) 0f);
        long currentTimes = System.currentTimeMillis();
        if (currentTimes-lastLoginTime>1000*60*60*24*3){
            ///////////登录间隔大于三天将重新登录
            return;
        }
        Intent intent = new Intent(MainActivity.this, contentActivity_note.class);
        intent.putExtra("login",sharefilename);
        startActivity(intent);
        Shareprefener.dealshareprefenerce(MainActivity.this,true,sharefilename,logincerity,true,false);
        long currentTime = System.currentTimeMillis();
        Shareprefener.dealshareprefenerce(MainActivity.this,true,sharefilename,logintime,currentTime,0);
    }

    private boolean dealLogin(Long name,String password){


        String[] mateList = getResources().getStringArray(R.array.studentnumber);
        String strname = String.valueOf(name);
        for (int i = 0;i<mateList.length;i++){
            if (strname.equals(mateList[i])){
                //密码验证后端
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constant.BaseUrl)
                        .build();
                ApiStore apiStore = retrofit.create(ApiStore.class);
                HashMap<String,String> map = new HashMap<>();
                map.put("student_id", String.valueOf(name));
                map.put("password",password);
                Call<BackCodeJavaBean> call = apiStore.loginpost(map);
                call.enqueue(new Callback<BackCodeJavaBean>() {
                    @Override
                    public void onResponse(Call<BackCodeJavaBean> call, Response<BackCodeJavaBean> response) {
                        if (response.body().getCode()==200){
                            mLoadingIcon.stopAnim();
                            Intent intent = new Intent(MainActivity.this, contentActivity_note.class);
                            intent.putExtra("login", sharefilename);
                            startActivity(intent);
                            Shareprefener.dealshareprefenerce(MainActivity.this, true, sharefilename, logincerity, true, false);
                            long currentTime = System.currentTimeMillis();
                            Shareprefener.dealshareprefenerce(MainActivity.this, true, sharefilename, logintime, currentTime, 0);
                        }else {
                            showToast("登录错误："+response.body().getMessage()+"  Code:"+response.body().getCode());
                        }
                    }

                    @Override
                    public void onFailure(Call<BackCodeJavaBean> call, Throwable t) {

                        mLoadingIcon.stopAnim();
                        showToast("登录失败"+t);
                        ShimkLog.logd(String.valueOf(t));
                    }
                });
//                binder.requestService("");
                Shareprefener.dealshareprefenerce(this,true
                ,sharefilename
                ,"LOGIN_STATE",true,false);


                return true;


            }
        }
        ShimkLog.logd("密码或学号错误，登录失败");
        showSnackBar("密码或学号错误，登录失败111",coordinatorLayout);
        mLoadingIcon.stopAnim();
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}