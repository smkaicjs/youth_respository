package com.shimk.Txgc.frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.shimk.Txgc.R;
import com.shimk.Txgc.activity.ContentActivity;
import com.shimk.Txgc.activity.UserEditPersonInfoActivity;
import com.shimk.Txgc.adapter.Recyclerview_adapter_liuyance;
import com.shimk.Txgc.bean.ClassmateContent;
import com.shimk.Txgc.customUI.CircleImageView;
import com.shimk.Txgc.presenter.PresenterInterface;
import com.shimk.Txgc.utils.Litepaldealwith;
import com.shimk.Txgc.utils.ShimkLog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.shimk.Txgc.MainActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the
 * create an instance of this fragment.
 */
public class GuestbookFragment extends Fragment implements PresenterInterface.View,View.OnTouchListener{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CircleImageView userHeaderPhoto;
    private TextView userHeaderText;
    private RecyclerView mRecyclerView;
    private List<ClassmateContent> recycleradapterDataList = new ArrayList<>();
    private Recyclerview_adapter_liuyance recyclerAdapter;
    private FloatingActionButton floatingActionButton;

    private TextView textView;
    private ContentActivity mActivity;
    private View headView;

    private PresenterInterface.presenter presenter;

    private AlertDialog mEverydayDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guestbook, container, false);

        findview(view);
        viewinit();
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.toolbar_menu,menu);
    }

    private void findview(View view){

        mActivity = (ContentActivity) getActivity();
        headView = LayoutInflater.from((Context) mActivity).inflate(R.layout.nav_header,null);
        textView = view.findViewById(R.id.textsss);

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_nav_button);
        navigationView = view.findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);



        drawerLayout = view.findViewById(R.id.draw_layout);
        mRecyclerView = view.findViewById(R.id.liuyance_recyclerview);
        userHeaderPhoto = headView.findViewById(R.id.user_photo_header);

        userHeaderPhoto.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.userheaderphoto));
        userHeaderText = headView.findViewById(R.id.current_username_nav_text);

        navigationView.addHeaderView(headView);
        floatingActionButton = view.findViewById(R.id.refesh_button);


    }


    private void viewinit(){


        textView.setOnTouchListener(this::onTouch);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        //加载本地所有记录
        recycleradapterDataList = Litepaldealwith.getAllClassmatecontent();
        recyclerAdapter = new Recyclerview_adapter_liuyance(recycleradapterDataList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClick(onRecyclerItemClick);
        userHeaderText.setOnClickListener(headItemClickListener);
        userHeaderPhoto.setOnClickListener(headItemClickListener);
        floatingActionButton.setOnClickListener(v -> {
            //刷新内容 暂时为关闭音乐
            mActivity.closeBackMusic();

        });



    }

    private View.OnClickListener headItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.user_photo_header:
                    //todo
                    ShimkLog.showToast("头部点击",mActivity);
                    Intent intent = new Intent(mActivity, UserEditPersonInfoActivity.class);
                    startActivity(intent);

                    break;
                case R.id.current_username_nav_text:
                    ShimkLog.showToast("头部点击",mActivity);
                    break;
            }
        }
    };

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()){
            case R.id.home_user:
                ShimkLog.showToast("wd",mActivity);
                break;
            case R.id.current_username_nav_text:
                ShimkLog.showToast("username",mActivity);

                break;
            case R.id.content_note:

                ShimkLog.showToast("liuyan",mActivity);

                break;
            case R.id.we_position:
                ShimkLog.showToast("我们在哪里",mActivity);

                break;
        }
        return false;
    };

    private Recyclerview_adapter_liuyance.onItemClick onRecyclerItemClick = new Recyclerview_adapter_liuyance.onItemClick() {
        @Override
        public void onClick(View view, int postion) {
            //Todo 每日寄语点击处理
            String string=recycleradapterDataList.get(postion).getContent();
            ShimkLog.showToast(string,mActivity);
        }
    };



    private void dothing(){
        Observable<String> observable= (Observable<String>) Observable.create((ObservableOnSubscribe<String>) emitter -> {

        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                }
        );
    }


    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: 99999");
        if (item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(Gravity.START);
            Log.d(TAG, "onOptionsItemSelected: ");
            return true;
        }
        if (item.getItemId()==R.id.toolbar_share){
            Toast.makeText(getContext(),"处理中",Toast.LENGTH_SHORT).show();
            Litepaldealwith.addClassmatecontent("test","1685954525","ceshi");
            recycleradapterDataList.clear();
            recycleradapterDataList.addAll(Litepaldealwith.getAllClassmatecontent());
            recyclerAdapter.notifyDataSetChanged();

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void responseFromNetPresenterSucc(ClassmateContent content) {

        String name = content.getName();
        String posttime = content.getPosttime();

        boolean issave = Litepaldealwith.isExistClassmateContent(name,posttime);
        if (issave){
            //存在于当前本地数据库
        }else{
            recycleradapterDataList.add(content);
            recyclerAdapter.notifyDataSetChanged();
            Litepaldealwith.addClassmatecontent(content);
        }

//        String userstr = content.getContent();
//        textView.setText(userstr);
//        Recycleradapter_data data = new Recycleradapter_data();
//        data.setContent(userstr);
//        data.setPostTime(userstr);
//        if (recycleradapterDataList.isEmpty()){
//            recycleradapterDataList.add(data);
//            recyclerAdapter.notifyDataSetChanged();
//        }else {
//            recycleradapterDataList.clear();
//            recycleradapterDataList.add(data);
//            recyclerAdapter.notifyDataSetChanged();
//
//
//        }
    }

    @Override
    public void responseFromNetPresenterFailed(String failedStr) {
        ShimkLog.logd("请求错误");
    }

    @Override
    public void responseFromNetEveryDayPreserterFailed(String string,String source) {
        //todo 弹出每日一句弹窗

        ShimkLog.logd("获取到的字符串为："+string);
        showEveryDayDialog(string,source);
    }

    private TextView everydayTextView,everydayTextviewSource;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    private void showEveryDayDialog(String string,String source) {

        if (mEverydayDialog==null||!mEverydayDialog.isShowing()){
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            View view = LayoutInflater.from(mActivity).inflate(R.layout.everyday_alertdialog_layout,null);
            everydayTextView = view.findViewById(R.id.everyday_text);
            everydayTextviewSource = view.findViewById(R.id.everyday_text_source);
            everydayTextView.setText(string);
            everydayTextviewSource.setText("来自："+source);
            Button button = view.findViewById(R.id.everyday_readed);
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (mEverydayDialog.isShowing()){
                        mEverydayDialog.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            DisplayMetrics dm = new DisplayMetrics();
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            layoutParams.height = (int) (dm.heightPixels*0.5);
            layoutParams.width = (int) (dm.widthPixels*0.3);
            view.setLayoutParams(layoutParams);

            builder.setView(view);
            mEverydayDialog = builder.create();
            mEverydayDialog.show();
        }else
            {
            everydayTextView.setText(string);
        }
    }


    @Override
    public void setPresenter(PresenterInterface.presenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.textsss:
                presenter.getEverydayData();
                ShimkLog.logd("dianji");
                break;
        }
        return false;
    }
}