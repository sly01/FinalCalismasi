package com.a.erkoc.finalcalismasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by erkoc on 06/01/15.
 */
public class showBitmap extends Activity {

    Bundle basket;
    Intent intent;
    Bitmap bmp;
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showbitmap);

        //intent = getIntent();
        bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
        //basket = getIntent().getExtras();
        //bmp = basket.get
        //String value = basket.getString("image");
        //System.out.println(value);

        imgView = (ImageView) findViewById(R.id.imageView);



        imgView.setImageBitmap(null);
        imgView.setImageBitmap(bmp);

    }
}
