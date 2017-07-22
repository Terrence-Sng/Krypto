package com.project.krypto.Game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.krypto.Activities.MainActivity;
import com.project.krypto.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    boolean fabOpen = false;
    int [] fabID = {R.id.freqFAB,R.id.iocFAB,R.id.periodFAB,R.id.labelSub,R.id.vigFAB,R.id.transpoFAB};
    int [] labelID = {R.id.labelFreqCounter, R.id.labelIOC, R.id.labelPeriod,R.id.subFAB, R.id.labelVig, R.id.labelTranspo};
    static ArrayList<TextView> labels = new ArrayList <> ();
    static ArrayList <FloatingActionButton> fabs = new ArrayList <> ();
    final Handler handler = new Handler();
    fabVisible fabVis = new fabVisible();
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

        final ImageButton btnTest =(ImageButton) findViewById(R.id.door);
        final ImageButton paperball =(ImageButton) findViewById(R.id.imageButton6);
        final ImageButton paint =(ImageButton) findViewById(R.id.pictureFront);
        final ImageButton drawer =(ImageButton) findViewById(R.id.imageButton4);
        final Button Enter = (Button) findViewById(R.id.submitButton);
        final Button ok = (Button) findViewById(R.id.okbutt);
        final Button ok1 = (Button) findViewById(R.id.okbutt1);
        final Button ok2 = (Button) findViewById(R.id.okbutt2);
        final EditText Password = (EditText) findViewById(R.id.editText);
        final ImageView Command = (ImageView) findViewById(R.id.cmdprompt);
        final TextView paperhint = (TextView) findViewById(R.id.crushedpaper);
        final RelativeLayout paper = (RelativeLayout) findViewById(R.id.paperLayout);
        final RelativeLayout picture = (RelativeLayout) findViewById(R.id.pictureBackLayout);
        final RelativeLayout openDrawer = (RelativeLayout) findViewById(R.id.opendrawer);
        final ImageView note = (ImageView) findViewById(R.id.noteImage);
        final EditText hint2 = (EditText) findViewById(R.id.pictback);
        final TextView hint31 = (TextView) findViewById(R.id.hintTextDrawer);
        final EditText cipher = (EditText) findViewById(R.id.cipherlvl1);

        /*FAB*/
        initFABS();
        //mainFAB
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabOpen == false) {
                    fabOpen = true;
                    fab.setImageResource(R.drawable.ic_close);
                    for (int i = 0; i < fabs.size(); i++)
                    {
                        fabVis.counter = i;
                        fabVis.run();
                    }


                }
                else
                {
                    fabOpen = false;
                    fab.setImageResource(R.drawable.ic_menu);
                    for(int i = 0; i < fabs.size(); i++)
                    {
                        fabs.get(i).setVisibility(View.INVISIBLE);
                        labels.get(i).setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

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
                    ok.setVisibility(View.INVISIBLE);
                    paper.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
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
                    paper.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
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
                    hint31.setVisibility(View.VISIBLE);
                    openDrawer.setVisibility(View.VISIBLE);
                    note.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    paper.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
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
                    picture.setVisibility(View.VISIBLE);
                    paintremove.start();
                    ring.start();
                    ring.setLooping(true);
                    hint2.setEnabled(false);
                    hint2.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    paper.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
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
                    paper.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
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
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ok.isPressed()) {
                    paper.setVisibility(View.INVISIBLE);
                }
            }
        });
        ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ok1.isPressed()) {
                    picture.setVisibility(View.INVISIBLE);
                }
            }
        });
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ok2.isPressed()) {
                    openDrawer.setVisibility(View.INVISIBLE);
                }
            }
        });
        /*
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Close.isPressed()) {
                    btnTest.setImageResource(R.drawable.door2);
                    Close.setVisibility(View.INVISIBLE);
                }
            }
        });*/
    }

     public void onBackPressed()
     {
         Intent i = new Intent (getApplicationContext(),MainActivity.class);
         startActivity(i);
         finish();
         super.onBackPressed();
     }
     public void initFABS ()
     {
     }
    class fabVisible implements Runnable
    {
        int counter = 0;
        @Override
        public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fabs.get(counter).setVisibility(View.VISIBLE);
                    labels.get(counter).setVisibility(View.VISIBLE);
                }
            }, 50+(counter*25));
        }
    }
}
