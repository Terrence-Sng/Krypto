package com.project.krypto.Game;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.project.krypto.R;

/**
 * Created by z_x_9 on 12/7/2017.
 */

public class stageclear extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stageclear);

        //set delay for the page to auto close
        Runnable r = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 5000); // will be delayed for 5 seconds
    }
}
