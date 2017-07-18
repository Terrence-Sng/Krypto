package com.project.krypto.Game;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.project.krypto.R;
/**
 * Created by z_x_9 on 12/7/2017.
 */

public class stagewrong extends Activity {


    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stagewrong);

        image = (ImageView) findViewById(R.id.wrong);
        final Animation animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
        animation.setDuration(1000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter
        // animation
        // rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation
        // infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the
        // end so the button will
        // fade back in
        image.startAnimation(animation);




        //set delay for the page to auto close
        Runnable r = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 3000); // will be delayed for 5 seconds*/

    }
}