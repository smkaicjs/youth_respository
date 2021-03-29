package com.shimk.Txgc.activity;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.shimk.Txgc.R;
import com.shimk.Txgc.adapter.ViewPagerAdapter;
import com.shimk.Txgc.base.ActManager;
import com.shimk.Txgc.base.BaseActivity;
import com.shimk.Txgc.frag.GuestbookFragment;
import com.shimk.Txgc.frag.MapFragment;
import com.shimk.Txgc.networkModel.NetworkLiuyanceContentImpl;
import com.shimk.Txgc.presenter.ContentNotePresenterImpl;
import com.shimk.Txgc.presenter.PresenterInterface;

import com.shimk.Txgc.utils.ShimkLog;

public class contentActivity_note extends BaseActivity {



    public String sharefilename;
    public ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;



    private PresenterInterface.presenter presenter;
    private MapFragment mapFragment;


    @SuppressLint("RestrictedApi")
    @Override
    protected void init() {
        setContentView(R.layout.activity_content_note);
        sharefilename = getIntent().getStringExtra("login");
        ShimkLog.logd(sharefilename);
        findview();
        //设置toolbar组件为actionbar
        viewinit();


    }

    private void findview(){
        viewPager2 = findViewById(R.id.main_viewpager);


    }



    private void viewinit(){
        //Guestbook fragment
        viewPagerAdapter = new ViewPagerAdapter(this);
        NetworkLiuyanceContentImpl netTask = NetworkLiuyanceContentImpl.getInstance();
        GuestbookFragment fragment = new GuestbookFragment();
        mapFragment = new MapFragment();
        ContentNotePresenterImpl presenter = new ContentNotePresenterImpl(netTask,fragment);
        fragment.setPresenter(presenter);
        viewPagerAdapter.addFragment(0,fragment);
        viewPagerAdapter.addFragment(1, mapFragment);
        //other fragment




        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setCurrentItem(0);


    }

    private int getWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    private int getHeigth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (viewPager2.getCurrentItem()==1){

            if (ev.getAction()==KeyEvent.ACTION_DOWN){

                if ((int)ev.getX()>getWidth()*0.2&&(int)ev.getX()<getWidth()*0.8){
                    viewPager2.setUserInputEnabled(false);
                    Log.d(TAG, "kkkkk1");
                }else {
                    viewPager2.setUserInputEnabled(true);
                    Log.d(TAG, "kkkkk2");
                }
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    private long mLastBackTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {

        if ((System.currentTimeMillis() - mLastBackTime) > 2000) {
            Toast.makeText(this, "再次点击退出应用", Toast.LENGTH_SHORT).show();

            mLastBackTime = System.currentTimeMillis();
        } else {

//            Intent intent = new Intent();
//            setResult(4,intent);
            ActManager.closeApp();
        }

    }

    public static final String TAG = "88888888";

//    private void getcontent(){
//        Retrofit retrofit = new Retrofit.Builder()
//        .baseUrl("https://www.jianshu.com/p/05bbf3aa2269/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//        .build();
//        retr api = retrofit.create(retr.class);
//        Call<String> call = api.getbaidu();
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                ShimkLog.logd(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                ShimkLog.logd("失败");
//            }
//        });
//
//    }


    private android.os.Handler mhandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            return false;
        }
    });



}