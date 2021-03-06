package com.shimk.Txgc.customUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class CircleImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Paint mPaint;
    private Paint paintB;
    private PorterDuffXfermode mPorterDuffXfermode;
    private int mSize;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);

        paintB = new Paint();
        paintB.setDither(true);
        paintB.setAntiAlias(true);

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mSize = Math.min(width,height);  //取宽高的最小值
        setMeasuredDimension(mSize,mSize);    //设置CircleImageView为等宽高
    }

    public static final String TAG = "zhidingyiyuan";
    @Override
    protected void onDraw(Canvas canvas) {

        //获取sourceBitmap，即通过xml或者java设置进来的图片
        Drawable drawable = getDrawable();

        if (drawable == null) {

            super.onDraw(canvas);
            return;

        }
        Bitmap sourceBitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        if (sourceBitmap != null){
            //对图片进行缩放，以适应控件的大小
            Bitmap bitmap = resizeBitmap(sourceBitmap,getWidth(),getHeight());
            drawCircleBitmapByXfermode(canvas,bitmap);    //(1)利用PorterDuffXfermode实现

            return;
        }
        super.onDraw(canvas);
    }

    private Bitmap resizeBitmap(Bitmap sourceBitmap,int dstWidth,int dstHeight){
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();

        float widthScale = ((float)dstWidth) / width;
        float heightScale = ((float)dstHeight) / height;

        //取最大缩放比
        float scale = Math.max(widthScale,heightScale);
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);

        return Bitmap.createBitmap(sourceBitmap,0,0,width,height,matrix,true);
    }

    private Canvas drawCircleBitmapByXfermode(Canvas canvas,Bitmap bitmap){
        int sc = canvas.saveLayer(0,0,getWidth(),getHeight(),paintB);
//        int sc = canvas.save();
        //绘制dst层
        canvas.drawCircle(mSize/2,mSize/2,mSize/2,paintB);
        //设置图层混合模式为SRC_IN
        mPaint.setXfermode(mPorterDuffXfermode);
        //绘制src层
        canvas.drawBitmap(bitmap,0,0,mPaint);
        canvas.restoreToCount(sc);

        return canvas;
    }


}
