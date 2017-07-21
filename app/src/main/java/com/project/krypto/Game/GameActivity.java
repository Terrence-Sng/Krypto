package com.project.krypto.Game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.project.krypto.Activities.MainActivity;
import com.project.krypto.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        final MediaPlayer ring= MediaPlayer.create(this,R.raw.background);
        final MediaPlayer opendoor= MediaPlayer.create(this,R.raw.opendoor);
        final MediaPlayer papercrumple= MediaPlayer.create(this,R.raw.papercrumple);
        final MediaPlayer paintremove= MediaPlayer.create(this,R.raw.paintremove);
        final MediaPlayer draweropen= MediaPlayer.create(this,R.raw.draweropen);
        final MediaPlayer wrong= MediaPlayer.create(this,R.raw.wrong);
        ring.start();
        ring.setLooping(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        final ImageButton btnTest =(ImageButton) findViewById(R.id.imageButton);
        final ImageButton paperball =(ImageButton) findViewById(R.id.imageButton6);
        final ImageButton paint =(ImageButton) findViewById(R.id.imageButton5);
        final ImageButton drawer =(ImageButton) findViewById(R.id.imageButton4);
        final Button Enter = (Button) findViewById(R.id.button2);
        final Button Close = (Button) findViewById(R.id.button4);
        final Button Reset = (Button) findViewById(R.id.button3);
        final EditText Password = (EditText) findViewById(R.id.editText);
        final EditText hint1 = (EditText) findViewById(R.id.editText2);
        final ImageView Command = (ImageView) findViewById(R.id.imageView3);
        final ImageView paperhint = (ImageView) findViewById(R.id.imageView5);
        final ImageView painthint = (ImageView) findViewById(R.id.imageView);
        final ImageView opendrawer = (ImageView) findViewById(R.id.imageView6);
        final ImageView note = (ImageView) findViewById(R.id.imageView7);
        final EditText hint2 = (EditText) findViewById(R.id.editText3);
        final EditText hint31 = (EditText) findViewById(R.id.editText4);
        final EditText hint32 = (EditText) findViewById(R.id.editText7);
        final EditText hint33 = (EditText) findViewById(R.id.editText8);
        final EditText cipher = (EditText) findViewById(R.id.editText10);



        cipher.setEnabled(false);
        Password.setEnabled(true);
        /*Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    Password.setHint("");
                else
                    Password.setHint("Enter Password Here");
            }
        });*/
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnTest.setSelected(!btnextra.isPressed());
                if (btnTest.isPressed()) {
                    /*Password.setText("");
                    Password.setFocusableInTouchMode(true);
                    Password.setFocusable(true);
                    Password.requestFocus();
                    Password.setActivated(true);
                    Password.setPressed(true);*/
                    Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus)
                                Password.setText("");
                            else
                                Password.setText("Enter Password Here");
                        }
                    });
                    //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    Enter.setVisibility(View.VISIBLE);
                    Password.setVisibility(View.VISIBLE);
                    Command.setVisibility(View.VISIBLE);
                    Reset.setVisibility(View.INVISIBLE);
                    hint1.setVisibility(View.INVISIBLE);
                    paperhint.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    painthint.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    hint32.setVisibility(View.INVISIBLE);
                    hint33.setVisibility(View.INVISIBLE);
                    opendrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                }
                else {
                    btnTest.setImageResource(R.drawable.door2);
                }
            }
        });
        paperball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnTest.setSelected(!btnextra.isPressed());

                if (paperball.isPressed()) {
                    papercrumple.start();
                    ring.start();
                    ring.setLooping(true);
                    hint1.setEnabled(false);
                    hint1.setVisibility(View.VISIBLE);
                    paperhint.setVisibility(View.VISIBLE);
                    Reset.setVisibility(View.VISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    painthint.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    hint32.setVisibility(View.INVISIBLE);
                    hint33.setVisibility(View.INVISIBLE);
                    opendrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                }
            }
        });
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnTest.setSelected(!btnextra.isPressed());

                if (drawer.isPressed()) {
                    draweropen.start();
                    ring.start();
                    ring.setLooping(true);
                    hint31.setEnabled(false);
                    hint32.setEnabled(false);
                    hint33.setEnabled(false);
                    hint31.setVisibility(View.VISIBLE);
                    hint32.setVisibility(View.VISIBLE);
                    hint33.setVisibility(View.VISIBLE);
                    opendrawer.setVisibility(View.VISIBLE);
                    note.setVisibility(View.VISIBLE);
                    Reset.setVisibility(View.VISIBLE);
                    hint1.setVisibility(View.INVISIBLE);
                    paperhint.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    painthint.setVisibility(View.INVISIBLE);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                }
            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnTest.setSelected(!btnextra.isPressed());

                if (paint.isPressed()) {
                    paintremove.start();
                    ring.start();
                    ring.setLooping(true);
                    hint2.setEnabled(false);
                    hint2.setVisibility(View.VISIBLE);
                    painthint.setVisibility(View.VISIBLE);
                    Reset.setVisibility(View.VISIBLE);
                    hint1.setVisibility(View.INVISIBLE);
                    paperhint.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    hint32.setVisibility(View.INVISIBLE);
                    hint33.setVisibility(View.INVISIBLE);
                    opendrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                }
            }
        });
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Plaintext = Password.getText().toString();
                if (Plaintext.matches("open") && Enter.isPressed()) {
                    //opendoor.start();
                    //btnTest.setImageResource(R.drawable.door1);
                    Enter.setEnabled(false);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                    //Reset.setVisibility(View.VISIBLE);
                    hint1.setVisibility(View.INVISIBLE);
                    paperhint.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    painthint.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    hint32.setVisibility(View.INVISIBLE);
                    hint33.setVisibility(View.INVISIBLE);
                    opendrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);

                    //once correct start the 'stageclear' activity
                    Intent i = new Intent(GameActivity.this, stageclear.class);
                    startActivity(i);

                    //after 'stageclear' closes the door will auto open after another 2 seconds
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            // if you are redirecting from a fragment then use getActivity() as the context.
                            opendoor.start();
                            btnTest.setImageResource(R.drawable.door1);
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
                            Intent i = new Intent(GameActivity.this, viglvl.class);
                            startActivity(i);
                        }
                    };
                    Handler h2 = new Handler();
                    h2.postDelayed(r2, 9000); // will be delayed for 2 seconds




                }
                else {
                    btnTest.setImageResource(R.drawable.door2);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                    /*Close.setVisibility(View.VISIBLE);
                    Close.postDelayed(new Runnable() {
                       public void run() {
                            Close.setVisibility(View.INVISIBLE);
                             }
                    }, 1000);*/

                    wrong.start();
                    //once wrong start the 'stagewrong' activity
                    Intent i = new Intent(GameActivity.this, stagewrong.class);
                    startActivity(i);

                }
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Reset.isPressed()) {
                    btnTest.setImageResource(R.drawable.door2);
                    Reset.setVisibility(View.INVISIBLE);
                    hint1.setVisibility(View.INVISIBLE);
                    paperhint.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    painthint.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    hint32.setVisibility(View.INVISIBLE);
                    hint33.setVisibility(View.INVISIBLE);
                    opendrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                }
            }
        });
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Close.isPressed()) {
                    btnTest.setImageResource(R.drawable.door2);
                    Close.setVisibility(View.INVISIBLE);
                }
            }
        });
    }



    public void startZoomInAnimation(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.imageButton);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.zoom_in_animation);
        imageView.startAnimation(animation);
    }
     public void onBackPressed()
     {
         Intent i = new Intent (getApplicationContext(),MainActivity.class);
         startActivity(i);
         finish();
         super.onBackPressed();
     }
}
