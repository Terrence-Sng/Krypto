package com.project.krypto.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.krypto.R;


/**
 * Created by z_x_9 on 17/7/2017.
 */

public class prologue extends AppCompatActivity {
    SharedPreferences sharedPref;
    int high;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final MediaPlayer typing= MediaPlayer.create(this, R.raw.typewriter);
        final MediaPlayer ring= MediaPlayer.create(this,R.raw.background);
        ring.start();
        ring.setLooping(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.prologue);



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
                ring.stop();
                Intent i = new Intent(prologue.this, GameActivity.class);
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
                tw.animateText("Mission" + "\n" + "\n" + "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah ");
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
        Handler h3 = new Handler();
        h3.postDelayed(r3, 14000);

    }

    public boolean testForCont ()
    {
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        int highScore = getResources().getInteger(R.integer.default_high_score);
        high = sharedPref.getInt(getString(R.string.high_score), highScore);

        Toast.makeText(getApplicationContext(), high + "", Toast.LENGTH_SHORT).show();

        if(high > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
