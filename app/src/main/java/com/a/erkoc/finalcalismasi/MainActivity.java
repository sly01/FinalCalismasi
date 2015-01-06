package com.a.erkoc.finalcalismasi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class MainActivity extends ActionBarActivity {

    Button btnStop;
    Button btnStart;
    Button btnFoto;
    Button btnFotoGoster;
    Intent intent;
    Intent cameraIntent;
    Bundle basket;
    Bitmap bitMap;
    Intent showIntent;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this,MyService.class);
        showIntent = new Intent(MainActivity.this, showBitmap.class);



        btnFoto = (Button) findViewById(R.id.button3);
        btnStart = (Button) findViewById(R.id.button2);
        btnStop = (Button) findViewById(R.id.button);
        btnFotoGoster = (Button) findViewById(R.id.button4);



        btnFotoGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(showIntent);
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
        IntentFilter intentFilter = new IntentFilter("test");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String durum = intent.getStringExtra("status");
                //System.out.println(durum);
                if(durum.equals("basladi")){
                    Toast.makeText(getBaseContext(), "Servisimis baslamis", Toast.LENGTH_LONG).show();
                }
            }
        };

        registerReceiver(mReceiver, intentFilter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            //basket =  data.getExtras();
            bitMap = (Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitMap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            showIntent.putExtra("image", bs.toByteArray());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /*
    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String durum = intent.getStringExtra("status");
            //System.out.println(durum);
            if(durum.equals("basladi")){
                Toast.makeText(getBaseContext(), "Servisimis baslamis", Toast.LENGTH_LONG).show();
            }

        }
    }
    */
}
