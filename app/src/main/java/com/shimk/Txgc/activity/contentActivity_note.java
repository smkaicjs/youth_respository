package com.shimk.Txgc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.shimk.Txgc.R;
import com.shimk.Txgc.adapter.Recyclerview_adapter_liuyance;
import com.shimk.Txgc.adapter.ViewPagerAdapter;
import com.shimk.Txgc.base.ActManager;
import com.shimk.Txgc.base.BaseActivity;
import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.frag.GuestbookFragment;
import com.shimk.Txgc.network.NetworkLiuyanceContent;
import com.shimk.Txgc.presenter.ContentNotePresenter;
import com.shimk.Txgc.presenter.PresenterInterface;
import com.shimk.Txgc.services.retr;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.shimk.Txgc.utils.Litepaldealwith;
import com.shimk.Txgc.utils.ShimkLog;

public class contentActivity_note extends BaseActivity {



    public String sharefilename;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;



    private PresenterInterface.presenter presenter;



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
        NetworkLiuyanceContent netTask = NetworkLiuyanceContent.getInstance();
        GuestbookFragment fragment = new GuestbookFragment();
        ContentNotePresenter presenter = new ContentNotePresenter(netTask,fragment);
        fragment.setPresenter(presenter);
        viewPagerAdapter.addFragment(0,fragment);
        //other fragment




        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setCurrentItem(0);


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