package com.project.krypto.Help;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.krypto.R;

import java.util.ArrayList;

/**
 * Created by Panda on 7/27/2017.
 */

public class vigHelp extends AppCompatActivity{

    TableRow tr1, tr2,tr3,tr4,tr5,tr6,tr7,tr8,tr9,tr10,tr11,tr12,tr13,tr14,tr15,tr16,tr17,tr18,tr19,tr20,tr21,tr22,tr23,tr24,tr25,tr26;
    ArrayList<TextView> letterslist = new ArrayList<>();
    String[] letters = new String[]{"A", "B", "C", "D", "E" ,"F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    Button next;
    Button back;
    Button restart;
    TextView tview;
    TextView tvfinal;
    TextView tvshow;
    TextView keytv;
    TextView msgtv;
    TextView cttv;

    //USE INTENT TO PASS ALL THESE VALUE FROM THE PREVIOUS ACTIVITY
    String keyword;// = "CAFE";
    String msg;//= "WOLLONGONG";
     String ct;//= "YOQPQNLSPG";
    int choice = 0; //0=encryption 1=decryption

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vighelp);
        toolbar = (Toolbar) findViewById(R.id.toolbarVig);
        toolbar.setTitle("Vigenere Help");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        keyword = getIntent().getStringExtra("KEY").toUpperCase();
        msg = getIntent().getStringExtra("CIPHER").toUpperCase();
        ct = getIntent().getStringExtra("RESULT").toUpperCase();
        try{
            choice = Integer.parseInt(getIntent().getStringExtra("TYPE"));
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        System.out.println(choice);

        tview = (TextView) findViewById(R.id.tview);
        tvfinal = (TextView) findViewById(R.id.tvfinal);
        tvshow = (TextView) findViewById(R.id.tvshow);
        next = (Button) findViewById(R.id.btnext);
        back = (Button) findViewById(R.id.btnback);
        restart = (Button) findViewById(R.id.btnrestart);

        keytv = (TextView) findViewById(R.id.keytv); //INVISIBLE
        msgtv = (TextView) findViewById(R.id.msgtv); //INVISIBLE
        cttv = (TextView) findViewById(R.id.cttv); //INVISIBLE


        //declare 26 table row
        tr1 = (TableRow) findViewById(R.id.trow1);
        tr2 = (TableRow) findViewById(R.id.trow2);
        tr3 = (TableRow) findViewById(R.id.trow3);
        tr4 = (TableRow) findViewById(R.id.trow4);
        tr5 = (TableRow) findViewById(R.id.trow5);
        tr6 = (TableRow) findViewById(R.id.trow6);
        tr7 = (TableRow) findViewById(R.id.trow7);
        tr8 = (TableRow) findViewById(R.id.trow8);
        tr9 = (TableRow) findViewById(R.id.trow9);
        tr10 = (TableRow) findViewById(R.id.trow10);
        tr11 = (TableRow) findViewById(R.id.trow11);
        tr12 = (TableRow) findViewById(R.id.trow12);
        tr13 = (TableRow) findViewById(R.id.trow13);
        tr14 = (TableRow) findViewById(R.id.trow14);
        tr15 = (TableRow) findViewById(R.id.trow15);
        tr16 = (TableRow) findViewById(R.id.trow16);
        tr17 = (TableRow) findViewById(R.id.trow17);
        tr18 = (TableRow) findViewById(R.id.trow18);
        tr19 = (TableRow) findViewById(R.id.trow19);
        tr20 = (TableRow) findViewById(R.id.trow20);
        tr21 = (TableRow) findViewById(R.id.trow21);
        tr22 = (TableRow) findViewById(R.id.trow22);
        tr23 = (TableRow) findViewById(R.id.trow23);
        tr24 = (TableRow) findViewById(R.id.trow24);
        tr25 = (TableRow) findViewById(R.id.trow25);
        tr26 = (TableRow) findViewById(R.id.trow26);
        final TableRow[] row = new TableRow[]{tr1,tr2,tr3,tr4,tr5,tr6,tr7,tr8,tr9,tr10,tr11,tr12,tr13,tr14,tr15,tr16,tr17,tr18,tr19,tr20,tr21,tr22,tr23,tr24,tr25,tr26};

        //populate for row 1-26
        for (int i = 0; i < 26 ; i++)
        {
            popTable(row[i], i);
        }

        //make the keyword as long as the msg
        String fullkey = keyword;
        while (fullkey.length() != msg.length())
        {
            fullkey = fullkey + keyword;
            if (fullkey.length() > msg.length())
            {
                int diff = fullkey.length()-msg.length();
                fullkey = fullkey.substring(0, fullkey.length() - diff);
            }
        }

        final String fullLengthKey = fullkey;

        keytv.setText(fullLengthKey);
        msgtv.setText(msg);
        cttv.setText(ct);



        //display the key and the input text
        tview.setText("Key:  \n" + "\t" + fullkey + "\n\n" + "Text: \n" + "\t" + msg);

        //display result in tvfinal
        if (choice == 0) {
            tvfinal.setText("CipherText: \n\n");
        }else{
            tvfinal.setText("PlainText: ");
        }

        //next button
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //refresh table
                for (int i = 0; i < 26; i++) {
                    TextView tv = (TextView) row[i].getChildAt(0);
                    tv.setBackgroundResource(R.drawable.tablecellvig);
                    for (int j = 0; j < 26; j++) {
                        TextView tv2 = (TextView) row[i].getChildAt(j);
                        tv2.setBackgroundResource(R.drawable.tablecellvig);
                    }
                }

                String keywordtest = keytv.getText().toString();
                String msgtest = msgtv.getText().toString();
                String cttest = cttv.getText().toString();
                /*if (keywordtest.isEmpty()) {
                    keywordtest = keyword;
                }*/

                //if finished show alert
                if (msgtest.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(vigHelp.this);
                    builder.setCancelable(true);
                    builder.setTitle("Done!");
                    if (choice == 0)
                    {
                        builder.setMessage("Encryption is Done!");
                    }
                    else if (choice ==1)
                    {
                        builder.setMessage("Decryption is Done!");
                    }
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                } else {

                    //execute depends on encryption or decryption
                    if (choice == 0)
                    {
                        String top = Character.toString(keywordtest.charAt(0));
                        String left = Character.toString(msgtest.charAt(0));
                        String ct = Character.toString(cttest.charAt(0));


                        //highlight left to right
                        for (int i = 0; i < 26; i++) {
                            TextView tv = (TextView) row[i].getChildAt(0);
                            String text = tv.getText().toString();
                            if (text.equals(left)) {
                                tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));

                                for (int j = 0; j < 26; j++) {
                                    TextView tv2 = (TextView) row[i].getChildAt(j);
                                    String text2 = tv2.getText().toString();
                                    if (!text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                    } else if (text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                        break;
                                    }
                                }
                            }
                        }

                        //highlight top to down
                        for (int i = 0; i < 26; i++) {
                            TextView tv = (TextView) tr1.getChildAt(i);
                            String text = tv.getText().toString();
                            if (text.equals(top)) {
                                tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));

                                for (int j = 0; j < 26; j++) {
                                    TextView tv2 = (TextView) row[j].getChildAt(i);
                                    String text2 = tv2.getText().toString();
                                    if (!text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                    } else if (text2.equals(ct)) {
                                        tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                        break;
                                    }
                                }
                            }
                        }

                        keywordtest = keywordtest.substring(1);
                        msgtest = msgtest.substring(1);
                        cttest = cttest.substring(1);
                        keytv.setText(keywordtest);
                        msgtv.setText(msgtest);
                        cttv.setText(cttest);

                        tvshow.setText(ct);
                        tvshow.bringToFront();
                        tvshow.setVisibility(View.VISIBLE);

                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
                        valueAnimator.setDuration(3000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float alpha = Float.parseFloat(animation.getAnimatedValue().toString());
                                tvshow.setAlpha(alpha);
                            }
                        });
                        valueAnimator.start();

                        String result = tvfinal.getText().toString();
                        result = result + ct;
                        tvfinal.setText(result);
                    }

                    else if (choice == 1)
                    {
                        String top = Character.toString(keywordtest.charAt(0));
                        String left = Character.toString(cttest.charAt(0));
                        String ct = Character.toString(msgtest.charAt(0));


                        //highlight left to right
                        for (int i = 0; i < 26; i++) {
                            TextView tv = (TextView) row[i].getChildAt(0);
                            String text = tv.getText().toString();
                            if (text.equals(left)) {
                                tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));

                                for (int j = 0; j < 26; j++) {
                                    TextView tv2 = (TextView) row[i].getChildAt(j);
                                    String text2 = tv2.getText().toString();
                                    if (!text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                    } else if (text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                        break;
                                    }
                                }
                            }
                        }

                        //highlight top to down
                        for (int i = 0; i < 26; i++) {
                            TextView tv = (TextView) tr1.getChildAt(i);
                            String text = tv.getText().toString();
                            if (text.equals(top)) {
                                tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));

                                for (int j = 0; j < 26; j++) {
                                    TextView tv2 = (TextView) row[j].getChildAt(i);
                                    String text2 = tv2.getText().toString();
                                    if (!text2.equals(ct)) {
                                        tv2.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                    } else if (text2.equals(ct)) {
                                        tv.setBackgroundColor(getResources().getColor(R.color.violetedpink));
                                        break;
                                    }
                                }
                            }
                        }

                        keywordtest = keywordtest.substring(1);
                        msgtest = msgtest.substring(1);
                        cttest = cttest.substring(1);
                        keytv.setText(keywordtest);
                        msgtv.setText(msgtest);
                        cttv.setText(cttest);



                        tvshow.setText(left);
                        tvshow.bringToFront();
                        tvshow.setVisibility(View.VISIBLE);

                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
                        valueAnimator.setDuration(3000);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float alpha = Float.parseFloat(animation.getAnimatedValue().toString());
                                tvshow.setAlpha(alpha);
                            }
                        });
                        valueAnimator.start();

                        String result = tvfinal.getText().toString();
                        result = result + left;
                        tvfinal.setText(result);
                    }
                }
            }
        });

        //restart the process
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String tempkey = keytv.getText().toString();
                String tempmsg = msgtv.getText().toString();
                String tempct = cttv.getText().toString();

                //if finished show alert
                if (tempkey.length() == fullLengthKey.length()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(vigHelp.this);
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
                }

                else {
                    //refresh table
                    for (int i = 0; i < 26; i++) {
                        TextView tv = (TextView) row[i].getChildAt(0);
                        tv.setBackgroundResource(R.drawable.tablecellvig);
                        for (int j = 0; j < 26; j++) {
                            TextView tv2 = (TextView) row[i].getChildAt(j);
                            tv2.setBackgroundResource(R.drawable.tablecellvig);
                        }
                    }


                    int diff = fullLengthKey.length() - tempkey.length();
                    diff--;

                    tempkey = Character.toString(fullLengthKey.charAt(diff)) + tempkey;
                    tempmsg = Character.toString(msg.charAt(diff)) + tempmsg;
                    tempct = Character.toString(ct.charAt(diff)) + tempct;
                    keytv.setText(tempkey);
                    msgtv.setText(tempmsg);
                    cttv.setText(tempct);

                    String finaltemp = tvfinal.getText().toString();
                    finaltemp = finaltemp.substring(0, finaltemp.length() - 1);
                    tvfinal.setText(finaltemp);
                }
            }
        });

        //restart the process
        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //refresh table
                AlertDialog.Builder builder = new AlertDialog.Builder(vigHelp.this);
                builder.setCancelable(true);
                builder.setTitle("Reset!");
                builder.setMessage("Process Restarted!");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                for (int i = 0; i < 26; i++) {
                    TextView tv = (TextView) row[i].getChildAt(0);
                    tv.setBackgroundResource(R.drawable.tablecellvig);
                    for (int j = 0; j < 26; j++) {
                        TextView tv2 = (TextView) row[i].getChildAt(j);
                        tv2.setBackgroundResource(R.drawable.tablecellvig);
                    }
                }
                keytv.setText(fullLengthKey);
                msgtv.setText(msg);
                cttv.setText(ct);
                if (choice == 0) {
                    tvfinal.setText("CipherText: ");
                }else{
                    tvfinal.setText("PlainText: ");
                }
            }
        });
    }


    //populate function for row 1-26
    public void popTable (TableRow tr, int count)
    {
        //for row 1
        if (count == 0)
        {
            for(int i = 0; i < 26; i ++)
            {
                TextView text = new TextView(this);
                text.setText(letters[i]);
                text.setWidth(10);
                text.setGravity(Gravity.CENTER_HORIZONTAL);
                text.setBackgroundResource(R.drawable.tablecellvig);
                text.setTextColor(getResources().getColor(R.color.Neon_Green));
                letterslist.add(text);
            }
            for (int i = 0; i < 26 ; i ++)
            {
                Log.d("Letters", letterslist.get(i).getText().toString());
                tr.addView(letterslist.get(i));
            }
        }
        //for row 2-26
        else
        {
            letterslist.clear();
            String first = letters[0];
            System.arraycopy(letters, 1, letters, 0, letters.length - 1);
            letters[25] = first;
            for (int i = 0; i < 26; i++) {
                TextView text = new TextView(this);
                text.setText(letters[i]);
                text.setWidth(38);
                text.setGravity(Gravity.CENTER_HORIZONTAL);
                text.setBackgroundResource(R.drawable.tablecellvig);
                text.setTextColor(getResources().getColor(R.color.Neon_Green));
                letterslist.add(text);
            }
            for (int i = 0; i < 26; i++) {
                tr.addView(letterslist.get(i));
            }
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
