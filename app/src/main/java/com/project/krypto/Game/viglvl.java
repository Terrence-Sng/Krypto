package com.project.krypto.Game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.project.krypto.R;
/**
 * Created by z_x_9 on 14/7/2017.
 */

public class viglvl extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viglvl);

        final ImageButton doorclose = (ImageButton) findViewById(R.id.door1);
        final ImageView dooropen = (ImageView) findViewById(R.id.door2);
        final ImageView scroll = (ImageView) findViewById(R.id.scroll);
        final ImageButton books = (ImageButton) findViewById(R.id.book);
        final EditText answer = (EditText) findViewById(R.id.answer);
        final Button submit = (Button) findViewById(R.id.btnsub);
        final ImageView bookbig = (ImageView) findViewById(R.id.bookimg);
        final ImageButton bookclose = (ImageButton) findViewById(R.id.bookclose);

        doorclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookbig.setVisibility(View.VISIBLE);
                bookclose.setVisibility(View.VISIBLE);
            }
        });

        bookclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookbig.setVisibility(View.INVISIBLE);
                bookclose.setVisibility(View.INVISIBLE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Plaintext = answer.getText().toString();
                if (Plaintext.matches("open") && submit.isPressed()) {
                    //opendoor.start();
                    //btnTest.setImageResource(R.drawable.door1);
                    submit.setEnabled(false);
                    //once correct start the 'stageclear' activity
                    Intent i = new Intent(viglvl.this, stageclear.class);
                    startActivity(i);

                    //after 'stageclear' closes the door will auto open after another 2 seconds
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            // if you are redirecting from a fragment then use getActivity() as the context.
                            //opendoor.start();
                            //btnTest.setImageResource(R.drawable.door1);

                            doorclose.setVisibility(View.INVISIBLE);
                            dooropen.setVisibility(View.VISIBLE);
                        }
                    };
                    Handler h = new Handler();
                    h.postDelayed(r, 6000); // will be delayed for 2 seconds

                    //startZoomInAnimation(btnTest);
                    //after door opens it will start the next activity after .. seconds
                    Runnable r2 = new Runnable() {
                        @Override
                        public void run() {
                            // if you are redirecting from a fragment then use getActivity() as the context.
                            Intent i = new Intent(viglvl.this, GameActivity.class);
                            startActivity(i);
                        }
                    };
                    Handler h2 = new Handler();
                    h2.postDelayed(r2, 9000); // will be delayed for 2 seconds

                }
                else {
                    //wrong.start();
                    //once wrong start the 'stagewrong' activity
                    Intent i = new Intent(viglvl.this, stagewrong.class);
                    startActivity(i);
                }
            }
        });



    }

}
