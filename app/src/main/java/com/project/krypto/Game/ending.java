package com.project.krypto.Game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.project.krypto.Activities.MainActivity;
import com.project.krypto.R;

/**
 * Created by yanna on 7/24/2017.
 */

public class ending extends Activity {
    MediaPlayer typing, ring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        typing= MediaPlayer.create(this, R.raw.typewriter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending);

        setStatusBar();

        final Button exit = (Button) findViewById(R.id.btnexit);
        final TypeWriter tw = (TypeWriter) findViewById(R.id.tvending);
        tw.setTextColor(Color.parseColor("#00ff2b"));
        exit.setVisibility(View.INVISIBLE);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    typing.reset();
                    typing.stop();
                    typing.release();
                    typing = null;
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
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
                tw.animateText("Congratulations!!!" + "\n" + "\n" + " You have finished the game successfully. Thank you for playing. ");
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
        h2.postDelayed(r2, 10000);

        //after mission is complete then show button
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                exit.setVisibility(View.VISIBLE);
            }
        };
        Handler h3 = new Handler();
        h3.postDelayed(r3, 11000);

    }

    public void setStatusBar()
    {
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Black));
    }

}
