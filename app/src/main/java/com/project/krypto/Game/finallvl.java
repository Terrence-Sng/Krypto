package com.project.krypto.Game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

/**
 * Created by yanna on 14/7/2017.
 */

public class finallvl extends Activity {

    RelativeLayout tabletgroup,papergroup,answergroup;
    ImageView dooropen,screen;
    ImageButton doorclose,tablet,safe;
    Button submit, close,safeclose;
    EditText answer;
    TextView cipher;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor ;
    static ArrayList<FloatingActionButton> fabs = new ArrayList <> ();
    int [] fabID = {R.id.freqFAB,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB, R.id.exitFAB};
    String level = "4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final MediaPlayer ring= MediaPlayer.create(this,R.raw.background);
        final MediaPlayer opendoor= MediaPlayer.create(this,R.raw.vault);
        final MediaPlayer ipad= MediaPlayer.create(this,R.raw.beep);
        final MediaPlayer safedoor= MediaPlayer.create(this,R.raw.click);
        final MediaPlayer wrong= MediaPlayer.create(this,R.raw.wrong);
        ring.start();
        ring.setLooping(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.finallvl);

        initFABS();
        tabletgroup = (RelativeLayout) findViewById(R.id.tabletgroupfinal);
        papergroup = (RelativeLayout) findViewById(R.id.papergroupFinal);
        answergroup = (RelativeLayout) findViewById(R.id.answerGroupfinal);
        doorclose = (ImageButton) findViewById(R.id.vaultdoor);
        dooropen = (ImageView) findViewById(R.id.door2);
        screen = (ImageView) findViewById(R.id.screen);
        tablet = (ImageButton) findViewById(R.id.tablet);
        answer = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.btnsub);
        close = (Button) findViewById(R.id.close);
        safe = (ImageButton) findViewById(R.id.safe);
        safeclose = (Button) findViewById(R.id.safeclose);
        cipher = (TextView) findViewById(R.id.cipherlvl4);

        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        doorclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answergroup.setVisibility(View.VISIBLE);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                close.setVisibility(View.INVISIBLE);
                safeclose.setVisibility(View.INVISIBLE);
                papergroup.setVisibility(View.INVISIBLE);
                tabletgroup.setVisibility(View.INVISIBLE);
                safe.setImageResource(R.drawable.safeclosed);
            }
        });

        tablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipad.start();
                safeclose.setVisibility(View.VISIBLE);
                tabletgroup.setVisibility(View.VISIBLE);
            }
        });


        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                safedoor.start();
                safeclose.setVisibility(View.VISIBLE);
                tabletgroup.setVisibility(View.INVISIBLE);
                papergroup.setVisibility(View.VISIBLE);
                safe.setImageResource(R.drawable.safeopened);
            }
        });

        safeclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                safeclose.setVisibility(View.INVISIBLE);
                papergroup.setVisibility(View.INVISIBLE);
                tabletgroup.setVisibility(View.INVISIBLE);
                safe.setImageResource(R.drawable.safeclosed);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Plaintext = answer.getText().toString();
                if (Plaintext.matches("open") && submit.isPressed()) {
                    //opendoor.start();
                    //btnTest.setImageResource(R.drawable.door1);
                    editor.putInt(getString(R.string.high_score), 4);
                    editor.commit();
                    //once correct start the 'stageclear' activity
                    Intent i = new Intent(finallvl.this, stageclear.class);
                    startActivity(i);

                    //after 'stageclear' closes the door will auto open after another 2 seconds
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            // if you are redirecting from a fragment then use getActivity() as the context.
                            //opendoor.start();
                            //btnTest.setImageResource(R.drawable.door1);
                            opendoor.start();
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
                            ring.stop();
                            Intent i = new Intent(finallvl.this, ending.class);
                            startActivity(i);
                        }
                    };
                    Handler h2 = new Handler();
                    h2.postDelayed(r2, 9000); // will be delayed for 2 seconds

                }
                else {
                    //wrong.start();
                    //once wrong start the 'stagewrong' activity
                    wrong.start();
                    Intent i = new Intent(finallvl.this, stagewrong.class);
                    startActivity(i);
                }
            }
        });


    }
    public void initFABS () {
        for (int i = 0; i < fabID.length; i++) {
            FloatingActionButton temp = (FloatingActionButton) findViewById(fabID[i]);
            //,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB};
            switch (temp.getId()) {
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
}
