package com.project.krypto.Game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.project.krypto.R;
/**
 * Created by z_x_9 on 13/7/2017.
 */

public class transit extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transit);

        //set delay for the page to auto close
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(transit.this, viglvl.class);
                startActivity(i);
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 2000); // will be delayed for 5 seconds*/


    }




    /*public void startZoomInAnimation(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.wall);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.zoom_in_animation);
        imageView.startAnimation(animation);
    }*/



}
