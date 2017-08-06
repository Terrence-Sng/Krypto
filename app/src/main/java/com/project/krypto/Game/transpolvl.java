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

public class transpolvl extends AppCompatActivity {

    private RelativeLayout cmdpromptgroup;
    ImageView doorBtn,clock7pm, tableHint,clock1900, crushedPaper;
    TextView decpHint1,decpHint2,cipher;
    Button resetBtn, submitBtn;
    EditText Password;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor ;
    static ArrayList<FloatingActionButton> fabs = new ArrayList <> ();
    int [] fabID = {R.id.freqFAB,R.id.iocFAB,R.id.periodFAB,R.id.subFAB,R.id.vigFAB,R.id.transpoFAB, R.id.exitFAB};
    String level = "3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transpolvl);

        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.high_score), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        cmdpromptgroup  = (RelativeLayout) findViewById(R.id.cmdpromptgrouptranspo);

        cipher = (TextView) findViewById(R.id.cipherlvl3);
        doorBtn = (ImageView) findViewById(R.id.doorButton);
        Password = (EditText) findViewById(R.id.password);
        clock7pm = (ImageView) findViewById(R.id.clock7pm);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        tableHint = (ImageView) findViewById(R.id.tableHint);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        final boolean[] isCorrect = {false};
        final int[] numTries = new int[1];
        clock1900 = (ImageView) findViewById(R.id.clock1900);
        crushedPaper = (ImageView) findViewById(R.id.crush_paper);
        decpHint1 = (TextView) findViewById(R.id.descpHint1);
        decpHint2 = (TextView) findViewById(R.id.descpHint2);

       initFABS();
            doorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (doorBtn.isPressed()) {
                        //showing the reset button
                        resetBtn.setVisibility(View.VISIBLE);
                        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (hasFocus)
                                    Password.setText("");
                                else
                                    Password.setText("Enter Password Here");
                            }
                        });
                        //shows cmd image, submit and reset button if true
                        cmdpromptgroup.setVisibility(View.VISIBLE);

                        //check if answer is correct/wrong
                        submitBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String passInput = Password.getText().toString();
                                if (submitBtn.isPressed()) {
                                    Password.getText();
                                    if (passInput.equals("7") || passInput.equals("seven")) {
                                        //show clear sign
                                        //tableHint.setVisibility(View.VISIBLE); //for testing
                                        isCorrect[0] = true;

                                        editor.putInt(getString(R.string.high_score), 3);
                                        editor.commit();

                                        Intent i = new Intent(transpolvl.this, stageclear.class);
                                        startActivity(i);

                                        Runnable r2 = new Runnable() {
                                            @Override
                                            public void run() {
                                                // if you are redirecting from a fragment then use getActivity() as the context.
                                                Intent i = new Intent(transpolvl.this, finallvl.class);
                                                startActivity(i);
                                            }
                                        };
                                        Handler h2 = new Handler();
                                        h2.postDelayed(r2, 9000); // will be delayed for 2 seconds
                                    } else {
                                        Intent i = new Intent(transpolvl.this, stagewrong.class);
                                        startActivity(i);
                                        //show fail sign
                                        numTries[0] += 1;
                                        clock7pm.setVisibility(View.VISIBLE); //for testing

                                        int tempCheck = numTries[0];

                                        //give 1 hint after 1 try
                                        if (tempCheck == 1)
                                        {
                                            clock1900.setVisibility(View.VISIBLE);
                                            decpHint1.setVisibility(View.VISIBLE);
                                        }
                                        //give 2nd hint if user gets it wrong again
                                        else if (tempCheck == 2)
                                        {
                                            crushedPaper.setVisibility(View.VISIBLE);
                                            decpHint2.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });

            //clicking the clock for a hint
            clock1900.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clock1900.isPressed()) {
                        clock7pm.setVisibility(View.VISIBLE);
                        resetBtn.setVisibility(View.VISIBLE);
                    }
                }
            });

            //clicking the paper for a hint
            crushedPaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (crushedPaper.isPressed()) {
                        tableHint.setVisibility(View.VISIBLE);
                        resetBtn.setVisibility(View.VISIBLE);
                    }
                }
            });

            //resets all the view
            resetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resetBtn.isPressed()) {
                        clock7pm.setVisibility(View.INVISIBLE);
                        cmdpromptgroup.setVisibility(View.INVISIBLE);
                        tableHint.setVisibility(View.INVISIBLE);
                        resetBtn.setVisibility(View.INVISIBLE);
                        decpHint1.setVisibility(View.INVISIBLE);
                        decpHint2.setVisibility(View.INVISIBLE);
                    }
                }
            });
        //}
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
