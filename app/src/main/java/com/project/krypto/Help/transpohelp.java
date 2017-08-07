package com.project.krypto.Help;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.R;

import java.util.ArrayList;

public class transpohelp extends AppCompatActivity {
    char[][] ct;
    ArrayList<TextView> textList, rltxView;
    ArrayList<Integer> textID;
    ArrayList<String> cipherText;
    ArrayList<String> outputct;
    LinearLayout textcontainer, rl;
    Button next, previous, reset;

    Animation animate;
    String pt, message, enc_dec;
    int blocksize;
    double rows, cols;
    int counter;

    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transpohelpact);

        toolbar = (Toolbar) findViewById(R.id.toolbartranspohelp);
        toolbar.setTitle("Transposition Help");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });


        pt  = getIntent().getStringExtra("CIPHER");//= "ctcyoeuxmrrxpsixuetx";
        enc_dec = getIntent().getStringExtra("TYPE");
        blocksize = Integer.parseInt(getIntent().getStringExtra("KEY"));
        TextView blocksizetv = (TextView) findViewById(R.id.keysizetranspohelp);
        blocksizetv.setText(blocksize+"");
        textcontainer = (LinearLayout) findViewById(R.id.textContainer);
        rl = (LinearLayout)findViewById(R.id.outputlayout);

       if(enc_dec.equals("0"))//enc
       {
           counter =0;
           textcontainer.removeAllViews();
           rl.removeAllViews();
           textList = new ArrayList<TextView>();
           rltxView = new ArrayList<TextView>();

           Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();
           enc(pt, blocksize);
           next = (Button) findViewById(R.id.transpohelpnext);
           next.setEnabled(true);
           previous = (Button) findViewById(R.id.transpohelpprevious);
           previous.setEnabled(true);
           reset = (Button) findViewById(R.id.transpohelpreset);
           reset.setEnabled(true);

           rl = (LinearLayout) findViewById(R.id.outputlayout);
           textcontainer = (LinearLayout) findViewById(R.id.textContainer);

           if (counter == 0) {
               previous.setEnabled(false);
               reset.setEnabled(false);
           }

           next.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                   animate.reset();
                   System.out.println(counter);
                   if (counter >= textList.size()) {
                       next.setEnabled(false);
                   } else {
                       if(counter == 0)
                       {
                           textList.get(0).clearAnimation();
                       }else
                       {
                           textList.get(counter).clearAnimation();}
                       textcontainer.addView(textList.get(counter));
                       textList.get(counter).startAnimation(animate);
                       rl.addView(rltxView.get(counter));
                       rltxView.get(counter).startAnimation(animate);
                       counter++;
                   }
                   previous.setEnabled(true);
                   reset.setEnabled(true);
               }
           });

           reset.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   counter =0;
                   textcontainer.removeAllViews();
                   rl.removeAllViews();
               }
           });

           previous.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (counter <= 0) {
                       previous.setEnabled(false);
                       reset.setEnabled(false);
                   } else {
                       --counter;
                       textList.get(counter).clearAnimation();
                       textcontainer.removeView(textList.get(counter));
                       rltxView.get(counter).clearAnimation();
                       rl.removeView(rltxView.get(counter));
                       next.setEnabled(true);
                   }
               }
           });
       }else
       {
                counter = 0;
                textcontainer.removeAllViews();
                rl.removeAllViews();
                textList = new ArrayList<TextView>();
                rltxView = new ArrayList<TextView>();

                dec(pt, blocksize);
                next = (Button) findViewById(R.id.transpohelpnext);
                next.setEnabled(true);
                previous = (Button) findViewById(R.id.transpohelpprevious);
                previous.setEnabled(true);
                reset = (Button) findViewById(R.id.transpohelpreset);
                reset.setEnabled(true);

           rl = (LinearLayout) findViewById(R.id.outputlayout);
                textcontainer = (LinearLayout) findViewById(R.id.textContainer);

                if (counter == 0) {
                    previous.setEnabled(false);
                    reset.setEnabled(false);
                }
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!next.isEnabled())
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(transpohelp.this);
                            builder.setCancelable(true);
                            builder.setTitle("Error!");
                            builder.setMessage("You are at the end now. Unable to next!");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show();
                            return;
                        }
                        animate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                        animate.reset();
                        System.out.println(counter);
                        if (counter >= textList.size()) {
                            next.setEnabled(false);
                        } else {
                            if(counter == 0)
                            {
                                textList.get(0).clearAnimation();
                            }else
                            {
                            textList.get(counter).clearAnimation();}
                            textcontainer.addView(textList.get(counter));
                            textList.get(counter).startAnimation(animate);
                            rl.addView(rltxView.get(counter));
                            rltxView.get(counter).startAnimation(animate);
                            counter++;
                        }
                        previous.setEnabled(true);
                        reset.setEnabled(true);
                    }
                });

           reset.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   counter=0;
                   textcontainer.removeAllViews();
                   rl.removeAllViews();
               }
           });

                previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!previous.isEnabled())
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(transpohelp.this);
                            builder.setCancelable(true);
                            builder.setTitle("Error!");
                            builder.setMessage("You are at the start now. Unable to back!");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.show();
                            return;
                        }
                        if (counter <= 0) {
                            previous.setEnabled(false);
                            reset.setEnabled(false);
                        } else {
                            --counter;
                            textList.get(counter).clearAnimation();
                            textcontainer.removeView(textList.get(counter));
                            rltxView.get(counter).clearAnimation();
                            rl.removeView(rltxView.get(counter));
                            next.setEnabled(true);
                        }
                    }
                });
            }
    }

    public void enc(String message, int blksize) {
        cols = blksize;
        System.out.println("pt.length = :" + message.length());
        rows = Math.ceil(message.length() / (double) cols);
        float remainder = message.length() % (int)cols;
        float numX = blksize - remainder;

        for (int i = 0; i < numX; i++) {
            message = message + "X";
        }
       // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        ct = new char[(int) rows][(int)cols];
        int jtemp = 0;
        for (int i = 0; i < rows; i++) {
            //System.out.println(i);
            for (int j = 0; j < cols; j++) {
                //System.out.print(j);
                //System.out.println(jtemp);
                char temp = message.charAt(jtemp);
                ct[i][j] = temp;
                jtemp++;
            }
        }
        cipherText = new ArrayList<>();
        outputct = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            String temp = "";
            for (int j = 0; j < rows; j++) {
                temp += (ct[j][i] + "\n");
            }
            cipherText.add(temp);
            System.out.println();
        }

        for (int i = 0; i < cols; i++) {
            String temp = "";
            for (int j = 0; j < rows; j++) {
                temp += (ct[j][i] + "");
            }
            outputct.add(temp);
            System.out.println();
        }
        textID = new ArrayList<>();
        textList = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            TextView tx = new TextView(getApplicationContext());
            tx.setText(cipherText.get(i));
            tx.setId(i);
            tx.setTextColor(getResources().getColor(R.color.Neon_Green));
            tx.setTextSize(14);
            textList.add(tx);
            textID.add(i);
        }

        rltxView = new ArrayList<TextView>();
        for (int i = 0; i < cols; i++) {
            TextView tx = new TextView(getApplicationContext());
            tx.setText(outputct.get(i));
            tx.setId(i);
            tx.setTextColor(getResources().getColor(R.color.Neon_Green));
            tx.setTextSize(14);
            rltxView.add(tx);
        }
    }
    public void dec (String message, int blksize)
    {
        rows = blksize;
        //System.out.println("pt.length = :" + message.length());
        cols = Math.ceil(message.length() / rows);
        //System.out.println("cols : " + cols);
        float remainder = message.length() % (int)rows;
        float numX = blksize - remainder;

        for (int i = 0; i < numX; i++) {
            message = message + "X";
        }
       // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        ct = new char[(int) rows][(int)cols];
        int jtemp = 0;
        for (int i = 0; i < rows; i++) {
            //System.out.println(i);
            for (int j = 0; j < cols; j++) {
                //System.out.print(j);
                //System.out.println(jtemp);
                char temp = message.charAt(jtemp);
                ct[i][j] = temp;
                jtemp++;
              //  System.out.print(temp);
            }
           // System.out.println();
        }
        cipherText = new ArrayList<>();
        outputct = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            String temp = "";
            for (int j = 0; j < rows; j++) {
                temp += (ct[j][i] + "\n");
            }
            cipherText.add(temp);
            //System.out.println(temp);
        }

        for (int i = 0; i < cols; i++) {
            String temp = "";
            for (int j = 0; j < rows; j++) {
                temp += (ct[j][i] + "");
            }
            outputct.add(temp);
           // System.out.println(temp);
        }
        textID = new ArrayList<>();
        textList = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            TextView tx = new TextView(getApplicationContext());
            tx.setText(cipherText.get(i));
            tx.setId(i);
            tx.setTextColor(getResources().getColor(R.color.Neon_Green));
            tx.setTextSize(14);
            textList.add(tx);
            textID.add(i);
        }

        rltxView = new ArrayList<TextView>();
        for (int i = 0; i < cols; i++) {
            TextView tx = new TextView(getApplicationContext());
            tx.setText(outputct.get(i));
            tx.setId(i);
            tx.setTextColor(getResources().getColor(R.color.Neon_Green));
            tx.setTextSize(14);
            rltxView.add(tx);
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
