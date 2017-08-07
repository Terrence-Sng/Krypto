package com.project.krypto.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.project.krypto.Activities.MainActivity;
import com.project.krypto.R;
import com.project.krypto.act_tools.Sub.SubCipher2;
import com.project.krypto.act_tools.ioc.ioc;
import com.project.krypto.act_tools.nGram.nGramCounter;
import com.project.krypto.act_tools.period.period;
import com.project.krypto.act_tools.transpo.transpo;
import com.project.krypto.act_tools.vingere.vigenere;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    ImageButton btnTest, paperball, paint, drawer;
    Button Enter,ok,ok1 ,ok2;
    EditText Password,hint2;
    ImageView Command, note;
    TextView paperhint,hint31, cipher;
    RelativeLayout paper,picture, openDrawer;

    String level = "1";
    int [] fabID = {R.id.freqFAB,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB, R.id.exitFAB};
    static ArrayList <FloatingActionButton> fabs = new ArrayList <> ();
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor ;
    MediaPlayer ring, opendoor, papercrumple, paintremove,draweropen, wrong;
    //final Handler handler = new Handler();
    //fabVisible fabVis = new fabVisible();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        setStatusBar();
        opendoor= MediaPlayer.create(this,R.raw.opendoor);
        papercrumple= MediaPlayer.create(this,R.raw.papercrumple);
        paintremove= MediaPlayer.create(this,R.raw.paintremove);
        draweropen= MediaPlayer.create(this,R.raw.draweropen);
        wrong= MediaPlayer.create(this,R.raw.wrong);

        btnTest =(ImageButton) findViewById(R.id.door);
        paperball =(ImageButton) findViewById(R.id.imageButton6);
        paint =(ImageButton) findViewById(R.id.pictureFront);
        drawer =(ImageButton) findViewById(R.id.imageButton4);
        Enter = (Button) findViewById(R.id.submitButton);
        ok = (Button) findViewById(R.id.okbutt);
        ok1 = (Button) findViewById(R.id.okbutt1);
        ok2 = (Button) findViewById(R.id.okbutt2);
        Password = (EditText) findViewById(R.id.editText);
        hint2 = (EditText) findViewById(R.id.pictback);
        cipher = (TextView) findViewById(R.id.cipherlvl1);
        Command = (ImageView) findViewById(R.id.cmdprompt);
        note = (ImageView) findViewById(R.id.noteImage);
        paperhint = (TextView) findViewById(R.id.crushedpaper);
        hint31 = (TextView) findViewById(R.id.hintTextDrawer);
        paper = (RelativeLayout) findViewById(R.id.paperLayout);
        picture = (RelativeLayout) findViewById(R.id.pictureBackLayout);
        openDrawer = (RelativeLayout) findViewById(R.id.opendrawer);

        /*FAB*/
       initFABS();


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
                   // ring.start();
                   // ring.setLooping(true);
                    paper.setVisibility(View.VISIBLE);
                    ok.setVisibility(View.VISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    Enter.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.INVISIBLE);
                    Command.setVisibility(View.INVISIBLE);
                    ok1.setVisibility(View.INVISIBLE);
                }
            }
        });
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnTest.setSelected(!btnextra.isPressed());

                if (drawer.isPressed()) {
                    draweropen.start();
                   // ring.start();
                   // ring.setLooping(true);
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
                    ok1.setVisibility(View.INVISIBLE);
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
                    //ring.start();
                    //ring.setLooping(true);
                    hint2.setEnabled(false);
                    hint2.setVisibility(View.VISIBLE);
                    ok1.setVisibility(View.VISIBLE);
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
                    ok1.setVisibility(View.INVISIBLE);
                    //Reset.setVisibility(View.VISIBLE);
                    paper.setVisibility(View.INVISIBLE);
                    hint2.setVisibility(View.INVISIBLE);
                    hint31.setVisibility(View.INVISIBLE);
                    openDrawer.setVisibility(View.INVISIBLE);
                    note.setVisibility(View.INVISIBLE);
                    editor.putInt(getString(R.string.high_score), 2);
                    editor.commit();
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
                    h.postDelayed(r, 3000); // will be delayed for 2 seconds

                    //startZoomInAnimation(btnTest);
                    //after door opens it will start the next activity after .. seconds
                    Runnable r2 = new Runnable() {
                        @Override
                        public void run() {
                            // if you are redirecting from a fragment then use getActivity() as the context.

                            Intent i = new Intent(GameActivity.this, viglvl.class);
                            startActivity(i);
                            finish();
                        }
                    };
                    Handler h2 = new Handler();
                    h2.postDelayed(r2, 4500); // will be delayed for 2 seconds
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
         for (int i = 0; i < fabID.length; i ++)
         {
             FloatingActionButton temp = (FloatingActionButton) findViewById(fabID[i]);
             //,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB};
             switch(temp.getId())
             {
                 case R.id.freqFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getBaseContext(), nGramCounter.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);

                     }
                 });
                     break;
                 case R.id.iocFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), ioc.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);

                     }
                 });
                     break;
                 case R.id.periodFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), period.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);

                     }
                 });
                     break;
                 case R.id.subFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), SubCipher2.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);
                     }
                 });
                     break;
                 case R.id.vigFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), vigenere.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);

                     }
                 });
                     break;
                 case R.id.transpoFAB : temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), transpo.class);
                         start.putExtra("GAME", true); // 1 for freq
                         start.putExtra("GAMECIPHER", cipher.getText().toString());
                         start.putExtra("LEVEL", level);
                         startActivity(start);

                     }
                 });
                     break;
                 default: temp.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent start = new Intent (getApplicationContext(), MainActivity.class);
                         startActivity(start);
                         finish();
                     }
                 });

             }
             fabs.add(temp);
         }
     }

    @Override
    protected void onPause() {
//        ring.setLooping(false);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
