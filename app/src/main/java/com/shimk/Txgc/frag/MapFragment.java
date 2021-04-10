package com.shimk.Txgc.frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.MapView;
import com.shimk.Txgc.R;
import com.shimk.Txgc.activity.ContentActivity;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

import static com.shimk.Txgc.utils.ShimkLog.logd;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    private TextView textView;

    private MapView baiduMap;
    private ContentActivity mActivity;


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
        View view = inflater.inflate(R.layout.fragment_baidumap, container, false);
        textView = view.findViewById(R.id.rejava_text);
        baiduMap = view.findViewById(R.id.baidu_mao_view);

        
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textRxjava textRxjava = new textRxjava();
                textRxjava.testModel();
            }
        });
        mActivity = (ContentActivity) getActivity();
        return view;
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
    public void onResume() {
        super.onResume();
        baiduMap.onResume();
    }

    public static <T> ObservableTransformer<T, T> observableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    class textRxjava{
        public void testModel(){
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
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