package com.shimk.Txgc.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.shimk.Txgc.utils.ShimkLog;

import java.io.IOException;

public class MediaService extends Service {

    private MediaPlayer mMediaPlayer;
    private Binder mybinder = new MediaBinder();
    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mMediaPlayer==null){

            initMediaPlayer();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return mybinder;
    }
    public class MediaBinder extends Binder{

        public void startPlayBackMusic(){
            if (null!=mMediaPlayer){
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                }else{
                    mMediaPlayer.start();
                }
            }else {
                initMediaPlayer();
            }
        }

        public void changeBackgroundMusic(String name){
            if (mMediaPlayer!=null) {
                mMediaPlayer.reset();
                try {
                    mMediaPlayer.setDataSource(getAssets().openFd(name));
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                    ShimkLog.logd("open music error");
                }

            }
        }
    }

    private void initMediaPlayer(){
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.reset();
        AssetManager assetManager = getAssets();

        try {
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd("music1.flac");
            mMediaPlayer.setDataSource(assetFileDescriptor);
            mMediaPlayer.setLooping(true);
            ShimkLog.logd("准备了");
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    ShimkLog.logd("开始播放了");
                    mMediaPlayer.start();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
