package com.project.krypto.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.project.krypto.R;


/**
 * Created by z_x_9 on 17/7/2017.
 */

public class prologue extends AppCompatActivity {
    SharedPreferences sharedPref;
    int high;
    Handler h,h2,h3;
    Runnable r, r2,r3;

    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final MediaPlayer typing= MediaPlayer.create(this, R.raw.typewriter);
        final MediaPlayer ring= MediaPlayer.create(this,R.raw.background);
       // ring.start();
       // ring.setLooping(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prologue);

        setStatusBar();

        final Button skip = (Button) findViewById(R.id.skippbuttonprolouge);

        final Button start = (Button) findViewById(R.id.btnstart);
        final Button cont = (Button) findViewById(R.id.btncontinue);
        cont.setOnClickListener(new View.OnClickListener() {
            Intent i;
            @Override
            public void onClick(View v) {
                switch(high)
                {
                    case 1: i = new Intent (prologue.this, GameActivity.class);
                        startActivity(i);
                        break;
                    case 2: i = new Intent (prologue.this, viglvl.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent (prologue.this, transpolvl.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent (prologue.this, finallvl.class);
                        startActivity(i);
                        break;
                }
            }
        });

        final TypeWriter tw = (TypeWriter) findViewById(R.id.tv);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ring.stop();
                sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.putInt(getString(R.string.high_score), 1);
                editor.commit();
                Intent i = new Intent(prologue.this, GameActivity.class);
                startActivity(i);
            }
        });

        //run mission text
        r = new Runnable() {
            @Override
            public void run() {
                skip.setVisibility(View.INVISIBLE);
                typing.start();
                tw.setText("");
                tw.setCharacterDelay(50);
                tw.animateText("Mission" + "\n" + "\n" + "You are tasked to recover a treasure from a vault. However, the vault in deep within many levels of rooms. Each door is locked and you will have to solve a cryptography cipher in order to unlock all the doors and get to the treasure. Put what you have learnt to use! Good Luck! ");
            }
        };
      h = new Handler();
      h.postDelayed(r, 2000); // will be delayed for 2 seconds

        r2 = new Runnable() {
            @Override
            public void run() {
                typing.stop();
            }
        };
        h2 = new Handler();
        h2.postDelayed(r2, 10000);
        //stop the tying after msg is done -> need to time manually

        //after mission is complete then show button
        r3 = new Runnable() {
            @Override
            public void run() {
                start.setVisibility(View.VISIBLE);
                if(!testForCont()){
                    cont.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cont.setVisibility(View.VISIBLE);
                }
            }
        };
        h3 = new Handler();
        h3.postDelayed(r3, 11000);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.removeCallbacksAndMessages(null);
                h2.removeCallbacksAndMessages(null);
                h3.removeCallbacksAndMessages(null);

                tw.setText("Mission" + "\n" + "\n" + "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah ");
                start.setVisibility(View.VISIBLE);
                if(!testForCont()){
                    cont.setVisibility(View.INVISIBLE);
                }
                else
                {
                    cont.setVisibility(View.VISIBLE);
                }
                skip.setVisibility(View.INVISIBLE);
            }
        });
    }

    public boolean testForCont ()
    {
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        int highScore = getResources().getInteger(R.integer.default_high_score);
        high = sharedPref.getInt(getString(R.string.high_score), highScore);

      //  Toast.makeText(getApplicationContext(), high + "", Toast.LENGTH_SHORT).show();

        if(high > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
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
