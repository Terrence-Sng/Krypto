package com.project.krypto.act_tools.transpo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.finallvl;
import com.project.krypto.Game.transpolvl;
import com.project.krypto.Game.viglvl;
import com.project.krypto.R;

import java.util.ArrayList;


public class transpo extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private RelativeLayout gameoutput, blocksizegroup,buttongroup, blksizefromhome, outputgroup;
    private String cipherFromGame, cipherfromhome,keyfromhome, type;
    private String level;
    private boolean fromGame;
    private Button back;
    double cols, rows;
    char [][] ct;
    ArrayList <String> cipherText,outputct;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transpo);

        toolbar = (Toolbar) findViewById(R.id.toolbartranspocipher);
        toolbar.setTitle("Transposition");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        outputgroup = (RelativeLayout) findViewById(R.id.transpooutput);
        blksizefromhome = (RelativeLayout) findViewById(R.id.blksizegroupfromhome);
        gameoutput = (RelativeLayout) findViewById(R.id.gamecipherinput);
        blocksizegroup = (RelativeLayout) findViewById(R.id.blockSizeGroup);
        buttongroup = (RelativeLayout) findViewById(R.id.buttonGroupTranspo);
        back = (Button) findViewById(R.id.backButtonGameTranspo);

        fromGame = getIntent().getBooleanExtra("GAME", false);
        cipherFromGame = getIntent().getStringExtra("GAMECIPHER");
        level = getIntent().getStringExtra("LEVEL");
        cipherfromhome=getIntent().getStringExtra("CIPHER");
        keyfromhome=getIntent().getStringExtra("KEY");
        type=getIntent().getStringExtra("TYPE");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game;
                switch (level)
                {
                    case "1" :  game = new Intent (getBaseContext(), GameActivity.class);
                        startActivity(game);
                        break;
                    case "2" : game = new Intent (getBaseContext(), viglvl.class);
                        startActivity(game);
                        break;
                    case "3": game = new Intent (getBaseContext(), transpolvl.class);
                        startActivity(game);
                        break;
                    case "4": game = new Intent (getBaseContext(), finallvl.class);
                        startActivity(game);
                        break;
                }
            }
        });
        final TextView mtext = (TextView)findViewById(R.id.inputviewtranspo);
        final EditText mEdit2 = (EditText) findViewById(R.id.blksize);
        final TextView outputview = (TextView) findViewById(R.id.outputText);
        final TextView keyview = (TextView) findViewById(R.id.blksizefromhome);

        if(fromGame==true)
        {
            mtext.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
            gameoutput.setVisibility(View.VISIBLE);
            blocksizegroup.setVisibility(View.VISIBLE);
            buttongroup.setVisibility(View.VISIBLE);
            blksizefromhome.setVisibility(View.GONE);
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.buttonGroupTranspo);
            p.setMargins(30, 30, 30, 30);
            outputgroup.setLayoutParams(p);


            Button encrypt = (Button) findViewById(R.id.encrypt);
            encrypt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = mtext.getText().toString();
                    int blksize = Integer.valueOf(String.valueOf(mEdit2.getText()));

                    //sample text
                    //message = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG";


                    outputview.setText("");
                    enc(message, blksize, outputview);
                }
            });

            Button decrypt = (Button) findViewById(R.id.decrypt);
            decrypt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = mtext.getText().toString();
                    int blksize = Integer.valueOf(String.valueOf(mEdit2.getText()));

                    //sample message
                    //message = "TXZWHKVQMOOAOTCOEUDFLRRISHJYNEBEUPG";

                    int choice = 2;
                    outputview.setText("");
                    dec(message, blksize, outputview);
                }
            });

        }else
        {
            if(type.equals("0")) // enc
            {
                String message = cipherfromhome;
                int blksize = Integer.parseInt(keyfromhome);
                mtext.setText(cipherfromhome);
                keyview.setText(blksize + "");

                outputview.setText("");
                enc(message, blksize, outputview);
            }
            else // dec
            {
                String message = cipherfromhome;
                int blksize = Integer.parseInt(keyfromhome);
                keyview.setText(blksize + "");

                outputview.setText("");
                dec(message, blksize, outputview);
            }
        }



    }



    public void enc(String message, int blksize, TextView output) {
        cols = blksize;
        System.out.println("pt.length = :" + message.length());
        rows = Math.ceil(message.length() / (double) cols);
        float remainder = message.length() % (int)cols;
        float numX = blksize - remainder;

        for (int i = 0; i < numX; i++) {
            message = message + "x";
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
        for (int i = 0; i < rows; i++) {
            String temp = "";
            for (int j = 0; j < cols; j++) {
                    temp += String.format("%5s", ct[i][j]);
                }
                temp += "\n";
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
        String tempct = "";
        String tempout = "";
        for(int i = 0; i < cipherText.size(); i ++)
        {
            tempct += cipherText.get(i);
        }
        for(int i = 0; i < outputct.size(); i++)
        {
            tempout += outputct.get(i);
        }
        output.setText("Decrypted Block is: \n" + tempct +
                "\n\n" + "Decrypted Message is: \n" + tempout);
    }

    public void dec (String message, int blksize, TextView output)
    {
        rows = blksize;
        System.out.println("pt.length = :" + message.length());
        cols = Math.ceil(message.length() / rows);
        System.out.println("cols : " + cols);
        float remainder = message.length() % (int)rows;
        float numX = blksize - remainder;

        for (int i = 0; i < numX; i++) {
            message = message + "x";
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
                //System.out.print(temp);
            }
            // System.out.println();
        }
        cipherText = new ArrayList<>();
        outputct = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            String temp = "";
            for (int j = 0; j < cols; j++) {
                temp += String.format("%5s", ct[i][j]);
            }
            temp += "\n";
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
        String tempct = "";
        String tempout = "";
        for(int i = 0; i < cipherText.size(); i ++)
        {
            tempct += cipherText.get(i);
            System.out.print(tempct);
        }
        for(int i = 0; i < outputct.size(); i++)
        {
            tempout += outputct.get(i);
        }
        output.setText("Decrypted Block is: \n" + tempct +
                            "\n\n" + "Decrypted Message is: \n" + tempout);
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
