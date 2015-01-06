package com.a.erkoc.finalcalismasi;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by erkoc on 05/01/15.
 */
public class MyService extends Service {

    MediaPlayer myPlayer = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        myPlayer = new MediaPlayer().create(MyService.this,R.raw.sound);
        if(!myPlayer.isPlaying()) {
            myPlayer.start();
        }
        myPlayer.setLooping(true);


        Intent i = new Intent();
        i.setAction("test");
        i.putExtra("status", "basladi");
        sendBroadcast(i);

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlayer.stop();
        myPlayer.release();
    }


}
