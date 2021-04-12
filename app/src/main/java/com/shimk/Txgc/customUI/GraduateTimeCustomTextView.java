package com.shimk.Txgc.customUI;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shimk.Txgc.R;

import static com.shimk.Txgc.utils.ShimkLog.TAG;

public class GraduateTimeCustomTextView extends LinearLayout {

    private Context mContext;
    private TextView textView;
    public GraduateTimeCustomTextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public GraduateTimeCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }



    public GraduateTimeCustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.graduatetime_layout,this);
        textView = this.findViewById(R.id.distance_time);
    }

    public void setText(String timetext){
        if (textView == null){
            Log.d(TAG, "setText: 88888888888888888888888");
            return;
        }
        textView.setText(timetext);
    }




}
