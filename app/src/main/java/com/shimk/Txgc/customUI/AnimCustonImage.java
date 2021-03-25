package com.shimk.Txgc.customUI;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.shimk.Txgc.utils.Constant;
import com.shimk.Txgc.utils.ShimkLog;

public class AnimCustonImage extends androidx.appcompat.widget.AppCompatImageView {
    private ObjectAnimator animator;
    private Context mContext;
    public AnimCustonImage(Context context) {
        super(context);

        init();
    }

    public AnimCustonImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public AnimCustonImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        init();
    }

    public void startAnim(){
        init();
    }

    public void stopAnim(){
        animator.cancel();
        clearAnimation();
        if (animator==null){
            ShimkLog.logd("当前anim为空");

        }else {
            animator.pause();
            if (animator.isRunning()){
                ShimkLog.logd("zairun");
            }else if (animator.isStarted()){
                ShimkLog.logd("isstart");
            }else {
                ShimkLog.logd("ok");
            }
        }
        setVisibility(GONE);

    }

    @SuppressLint("ObjectAnimatorBinding")
    private void init() {

        if (animator==null){
            animator = ObjectAnimator.ofFloat(AnimCustonImage.this,"rotation",0f,360f);
            animator.setDuration(1000);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setInterpolator(new LinearInterpolator());
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {


                    ShimkLog.logd("取消动画");

                    animation=null;

                    ShimkLog.logd(getVisibility()+"");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.addPauseListener(new Animator.AnimatorPauseListener() {
                @Override
                public void onAnimationPause(Animator animation) {

                }

                @Override
                public void onAnimationResume(Animator animation) {

                }
            });
            animator.start();
        }

    }

}
