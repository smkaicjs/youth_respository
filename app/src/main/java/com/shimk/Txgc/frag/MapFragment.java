package com.shimk.Txgc.frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shimk.Txgc.R;
import com.shimk.Txgc.activity.ContentActivity;
import com.shimk.Txgc.utils.LogOkhttp;
import com.shimk.Txgc.utils.ShimkLog;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

import static com.shimk.Txgc.utils.ShimkLog.TAG;
import static com.shimk.Txgc.utils.ShimkLog.logd;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private TextView textView;

    private MapView baiduMapView;
    private BaiduMap map;
    private ContentActivity mActivity;
    private LocationClient mLocationClient;
    private FloatingActionButton floatingActionButtonChangeMapType;

    public static int maptype = 1;


    public MapFragment() {
        // Required empty public constructor
    }


    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (ContentActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_baidumap, container, false);
        textView = view.findViewById(R.id.rejava_text);
        baiduMapView = view.findViewById(R.id.baidu_mao_view);
        floatingActionButtonChangeMapType = view.findViewById(R.id.change_map_type);

        map = baiduMapView.getMap();
        baiduMapView.setLogoPosition(LogoPosition.logoPostionRightBottom);



        initData();

        //显示交通情况


        
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textRxjava textRxjava = new textRxjava();
                textRxjava.testModel();
            }
        });
        floatingActionButtonChangeMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (maptype==1){
                    map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    maptype = 2;

                }else if (maptype==2){
                    map.setMapType(BaiduMap.MAP_TYPE_NONE);
                    maptype = 3;
                }else{
                    map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    maptype = 1;
                }
            }
        });

        return view;
    }

    private void initData() {

        map.setTrafficEnabled(true);
        //开启定位
        map.setMyLocationEnabled(true);

        //开启指南针
        map.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                ShimkLog.logd("地图加载完成");
                map.setCompassEnable(true);
                map.setViewPadding(1000,10,0,50);

            }
        });


        mLocationClient = new LocationClient(this.getContext());

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(500);


//设置locationClientOption
        mLocationClient.setLocOption(option);

//注册LocationListener监听器
        BaiduLocationData myLocationListener = new BaiduLocationData();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();
        View view = baiduMapView.getChildAt(2);
        baiduMapView.removeView(view);




    }


    private View.OnTouchListener baiduOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction()==KeyEvent.ACTION_DOWN){
                mActivity.viewPager2.requestDisallowInterceptTouchEvent(true);
                logd("取消拦截");

            }
            return false;
        }
    };

    @Override
    public void onPause() {
        baiduMapView.onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        baiduMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLocationClient.stop();
        map.setMyLocationEnabled(false);
        baiduMapView.onDestroy();
        baiduMapView = null;
    }

    public static <T> ObservableTransformer<T, T> observableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    class BaiduLocationData extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation == null || baiduMapView == null){
                Log.d(TAG, "onReceiveLocation: 空地");
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            map.setMyLocationData(locData);

            //自定义配置位置图标
            ShimkLog.logd(baiduMapView.getChildCount()+"个子视图");
            map.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true
                    ,BitmapDescriptorFactory.fromResource(R.drawable.icon_index),
                    0xfEEfffff,
                    0x9fffffff));

        }
    }

    class textRxjava{
        public void testModel(){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LogOkhttp()).build();
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .baseUrl("http://www.baidu.com");
            Retrofit retrofit1 = retrofit.build();
            Apiservices api = retrofit1.create(Apiservices.class);
            api.getData().compose(observableToMain())
                    .flatMap(new Function<String, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(String s) {
                            return Observable.just(s);
                        }
                    }).subscribe(new Consumer<String>() {
                @SuppressLint("CheckResult")
                @Override
                public void accept(String s) {
                    textView.setText(s);
                }
            });




           /*
            Flowable.create(new FlowableOnSubscribe<Object>() {
                @Override
                public void subscribe(@NonNull FlowableEmitter<Object> emitter) throws Throwable {

                }
            },BackpressureStrategy.LATEST)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Object>() {
                @Override
                public void onSubscribe(Subscription s) {


                }

                @Override
                public void onNext(Object o) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });

            */




        }
    }
    public interface Apiservices{
        @GET("/")
        Observable<String> getData();
    }
}