package com.project.krypto.Game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;

/**
 * Created by z_x_9 on 14/7/2017.
 */

public class viglvl extends AppCompatActivity {
    static ArrayList<FloatingActionButton> fabs = new ArrayList <> ();
    int [] fabID = {R.id.freqFAB,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB, R.id.exitFAB};

    TextView cipher, scroll;
    ImageView doorclose, bookclose,books, bookbig;
    Button submit;
    EditText answer;
    RelativeLayout answerGroup, hintGroup;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viglvl);

        initFABS();
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        doorclose = (ImageButton) findViewById(R.id.vaultdoor);
        scroll = (TextView) findViewById(R.id.scroll);
        books = (ImageButton) findViewById(R.id.book);
        answer = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.btnsub);
        bookbig = (ImageView) findViewById(R.id.bookimg);
        bookclose = (ImageButton) findViewById(R.id.bookclose);
        cipher = (TextView) findViewById(R.id.cipherlvl2);
        answerGroup = (RelativeLayout) findViewById(R.id.answerGroup);
        hintGroup = (RelativeLayout) findViewById(R.id.hintGroup);
        doorclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            answerGroup.setVisibility(View.VISIBLE);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            hintGroup.setVisibility(View.VISIBLE);
                if(answerGroup.getVisibility() == View.VISIBLE)
                {
                    answerGroup.setVisibility(View.INVISIBLE);
                }
            }
        });

        bookclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             hintGroup.setVisibility(View.INVISIBLE);
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
                    editor.putInt(getString(R.string.high_score), 2);
                    editor.commit();
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

                            doorclose.setImageResource(R.drawable.dbldoor2);
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
                            Intent i = new Intent(viglvl.this, transpolvl.class);
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
    public void initFABS () {
        for (int i = 0; i < fabID.length; i++) {
            FloatingActionButton temp = (FloatingActionButton) findViewById(fabID[i]);
            //,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB};
            switch (temp.getId()) {
                case R.id.freqFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "1"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);
                        }
                    });
                    break;
                case R.id.iocFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "2"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);
                        }
                    });
                    break;
                case R.id.periodFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "3"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);
                        }
                    });
                    break;
                case R.id.subFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "4"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);
                        }
                    });
                    break;
                case R.id.vigFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "5"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);
                        }
                    });
                    break;
                case R.id.transpoFAB:
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent start = new Intent(getApplicationContext(), MainActivity.class);
                            start.putExtra("tools", "6"); // 1 for freq
                            start.putExtra("GameCipher", cipher.getText().toString());
                            start.putExtra("level", "2");
                            startActivity(start);

                        }
                    });
                    break;
                default:
                    temp.setOnClickListener(new View.OnClickListener() {
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
