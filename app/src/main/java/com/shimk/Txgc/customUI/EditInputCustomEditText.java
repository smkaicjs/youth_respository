package com.shimk.Txgc.customUI;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.shimk.Txgc.R;



public class EditInputCustomEditText extends LinearLayout {

    private Context mContext;
    private TextInputLayout textInputLayout;
    private EditText editText;
    public static final String TAG = "EditUserCustom";
    public EditInputCustomEditText(Context context) {
        super(context);
    }

    public EditInputCustomEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();

    }

    public EditInputCustomEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){

        LayoutInflater.from(mContext).inflate(R.layout.custom_edittext,this);

        editText = this.findViewById(R.id.edit_text);
        textInputLayout = this.findViewById(R.id.edit_textlayout);
        textInputLayout.setHintAnimationEnabled(true);


    }

    public void setHint(String hintstr){
        textInputLayout.setHint(hintstr);
    }

    public void setTextChangeLisener(TextWatcher watcher){
        editText.addTextChangedListener(watcher);
    }

    public void setErrorInfo(boolean show,String string){

        if (show){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(string);


            return;
        }
        textInputLayout.setErrorEnabled(false);

    }
    public void setErrorInfo(boolean show,int strres){

        if (show){
            textInputLayout.setErrorEnabled(show);
            textInputLayout.setError(this.getResources().getString(strres));
            Log.d(TAG, "setErrorInfo: "+this.getResources().getString(strres));
            return;
        }
        textInputLayout.setErrorEnabled(show);

    }

    public String getText(){
        try {
            return String.valueOf(editText.getText());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setText(String string){
        editText.setText(string);
    }



}
