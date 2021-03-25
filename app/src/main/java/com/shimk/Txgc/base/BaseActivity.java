package com.shimk.Txgc.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shimk.Txgc.services.MediaService;

import org.litepal.LitePal;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "BaseActivity";
    private MediaService.MediaBinder backMusicBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            backMusicBinder = (MediaService.MediaBinder) service;
            Log.d(TAG, "onServiceConnected: 当前活动绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActManager.addActivity(this);
        init();
        LitePal.initialize(this);
        bindMediaService();
    }

    private void bindMediaService(){
        Intent intent = new Intent(this,MediaService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        if (null!=backMusicBinder){

            backMusicBinder.startPlayBackMusic();
        }
    }
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActManager.removeActivity(this);
        if (null!=connection){
            unbindService(connection);
            connection = null;
            Log.d(TAG, "onDestroy: 当前活动解除绑定");
        }
    }

    protected void remveAllActivity(){
        ActManager.closeApp();
    }

    public void closeBackMusic(){
        backMusicBinder.startPlayBackMusic();
    }
}
