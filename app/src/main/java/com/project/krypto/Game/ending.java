package com.project.krypto.Game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.project.krypto.Activities.MainActivity;
import com.project.krypto.R;

/**
 * Created by yanna on 7/24/2017.
 */

public class ending extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final MediaPlayer typing= MediaPlayer.create(this, R.raw.typewriter);
        final MediaPlayer ring= MediaPlayer.create(this,R.raw.background);
        ring.start();
        ring.setLooping(true);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending);

        final Button exit = (Button) findViewById(R.id.btnexit);
        final TypeWriter tw = (TypeWriter) findViewById(R.id.tvending);
        tw.setTextColor(Color.parseColor("#00ff2b"));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ending.this, MainActivity.class);
                startActivity(i);
            }
        });

        //run mission text
        Runnable r = new Runnable() {
            @Override
            public void run() {
                typing.start();
                typing.setLooping(true);
                tw.setText("");
                tw.setCharacterDelay(100);
                tw.animateText("Congratulation!!!" + "\n" + "\n" + " You have finished the game successfully. Thank you for playing. ");
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 2000); // will be delayed for 2 seconds

        //stop the tying after msg is done -> need to time manually
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                typing.stop();
            }
        };
        Handler h2 = new Handler();
        h2.postDelayed(r2, 13000);

        //after mission is complete then show button
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                exit.setVisibility(View.VISIBLE);
            }
        };
        Handler h3 = new Handler();
        h3.postDelayed(r3, 14000);

    }
}
