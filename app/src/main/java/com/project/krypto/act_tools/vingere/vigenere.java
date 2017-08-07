package com.project.krypto.act_tools.vingere;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.project.krypto.Help.helpmenu;
import com.project.krypto.Help.vigHelp;
import com.project.krypto.R;
import com.project.krypto.act_tools.Sub.SubCipher2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vigenere extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static EditText editKey;
    private static TextView gamecipheroutput_input, keyinputfromhome;
    private TextInputLayout vigkeyinputlayout;
    private static Button encrypt;
    private static Button decrypt;
    private static Button help;
    private static Button reset,back,share;
    private static TextView displayResult;
    private String mastercipher;
    private String cipherFromGame;
    private String level;
    private boolean fromGame;
    private Toolbar toolbar;
    private String type;
    String cipher, key;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vigenere);

        toolbar = (Toolbar) findViewById(R.id.toolbarvigcipher);
        toolbar.setTitle("Vigenere");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        fromGame = getIntent().getBooleanExtra("GAME", false);

        back = (Button) findViewById(R.id.backButtonGameVig);
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
                    case "4": game = new Intent (getBaseContext(),finallvl.class);
                        startActivity(game);
                        break;
                }
            }
        });
       vigkeyinputlayout = (TextInputLayout) findViewById(R.id.inputKeyLayoutVig);
        help = (Button) findViewById(R.id.vighelp);
       help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempcipher = mastercipher;
                if(mastercipher.length() > 20)
                {
                    tempcipher = mastercipher.substring(0,20);
                }
                else
                {
                    tempcipher = mastercipher;
                }

                if(key.trim().isEmpty())
                {
                    vigkeyinputlayout.setError("Key is empty!");
                    vigkeyinputlayout.setErrorEnabled(true);
                    return;
                }
                if(fromGame == true) {
                    Intent mIntent = new Intent(getBaseContext(), helpmenu.class);
                    mIntent.putExtra("CIPHER", tempcipher);
                    mIntent.putExtra("KEY", key);
                    mIntent.putExtra("RESULT", displayResult.getText().toString());
                    mIntent.putExtra("TYPECIPHER", "V");
                    startActivity(mIntent);
                }
                else
                {
                    Intent mIntent = new Intent(getBaseContext(), vigHelp.class);
                    mIntent.putExtra("CIPHER", tempcipher);
                    mIntent.putExtra("KEY", key);
                    mIntent.putExtra("RESULT", displayResult.getText().toString());
                    mIntent.putExtra("TYPECIPHER", "V");
                    startActivity(mIntent);
                }
            }
        });

        share = (Button) findViewById(R.id.vigsharebtn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharetext = displayResult.getText().toString().trim();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                if(type.equals("0"))//
                {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my encrypted cipher! Try to decrypt it!\n Cipher:\n" + sharetext);
                }
                else
                {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my decrypted cipher! cool right?!\n Cipher: \n " + sharetext);
                }
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Share using..."));
            }
        });
        encrypt = (Button) findViewById(R.id.btnEnc);
        decrypt = (Button) findViewById(R.id.btnDec);
        displayResult = (TextView) findViewById(R.id.Output);
        RelativeLayout outputview = (RelativeLayout) findViewById(R.id.OutputGroupVig);
        RelativeLayout key_cipherview = (RelativeLayout) findViewById(R.id.keyoutputgroup);
        RelativeLayout back_resetview = (RelativeLayout) findViewById(R.id.back_resetgroup);
        RelativeLayout enc_decview = (RelativeLayout) findViewById(R.id.vigBtnGroup);

        editKey = (EditText) findViewById(R.id.inputkey);
        gamecipheroutput_input = (TextView) findViewById(R.id.gameciphervig);
        keyinputfromhome = (TextView) findViewById(R.id.keyoutputfromhome);

        if(fromGame == true)
        {
            help.setVisibility(View.INVISIBLE);
            level = getIntent().getStringExtra("LEVEL");
            cipherFromGame = getIntent().getStringExtra("GAMECIPHER");
            help.setVisibility(View.INVISIBLE);
            mastercipher=cipherFromGame;

            gamecipheroutput_input.setText(cipherFromGame);
            editKey.setVisibility(View.VISIBLE);
            key_cipherview.setVisibility(View.GONE);
            vigkeyinputlayout.setVisibility(View.VISIBLE);
            share.setVisibility(View.GONE);

            enc_decview.setVisibility(View.VISIBLE);
            back_resetview.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.vigBtnGroup);
            p.setMargins(30, 30, 30, 30);
            outputview.setLayoutParams(p);

            encrypt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    displayResult.setText("");
                    key = editKey.getText().toString();
                    editKey.addTextChangedListener(new MyTextWatcher(editKey));
                    String msg = gamecipheroutput_input.getText().toString();
                    Pattern p = Pattern.compile("[^A-Za-z]");
                    Matcher m = p.matcher(key);
                    if(key.trim().isEmpty())
                    {
                        vigkeyinputlayout.setErrorEnabled(true);
                        vigkeyinputlayout.setError("Key is empty!");
                        help.setVisibility(View.INVISIBLE);
                    }else if(m.find()) {
                        vigkeyinputlayout.setErrorEnabled(true);
                        vigkeyinputlayout.setError("Key is must only contain alphabets & no spacing!!");
                        help.setVisibility(View.INVISIBLE);
                    }
                    else {
                        msg = msg.replaceAll("[^A-Za-z]+", "");
                        msg = msg.toLowerCase();
                        mastercipher = msg;

                        String tempcipher = "";
                        int counter = 0;
                        while (counter <= (msg.length() - 1)) {
                            for (int i = 0; i < key.length(); i++) {
                                if (counter <= (msg.length() - 1)) {
                                    //keyword char index
                                    char tmp = key.charAt(i);
                                    int temp = (int) tmp - 97; //a = 0

                                    //PT char index
                                    char msgTmp = msg.charAt(counter);
                                    int msgTemp = (int) msgTmp - 97;

                                    //encrypted char index
                                    int enc = ((msgTemp + temp) % 26) + 97;
                                    char encTmp = (char) enc;

                                    tempcipher = tempcipher + Character.toString(encTmp);

                                    counter++;
                                }
                            }
                        }

                        displayResult.setMovementMethod(new ScrollingMovementMethod());
                        //displayResult.setText(msg);
                        displayResult.setText(tempcipher);
                        help.setVisibility(View.VISIBLE);
                    }
                }
            });

            decrypt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    displayResult.setText("");
                    //editKey = (EditText) view.findViewById(R.id.inputkey);
                    key = editKey.getText().toString();
                    editKey.addTextChangedListener(new MyTextWatcher(editKey));
                    key = key.toLowerCase();
                    String msg = gamecipheroutput_input.getText().toString();
                    Pattern p = Pattern.compile("[^A-Za-z]");
                    Matcher m = p.matcher(key);
                    if(key.trim().isEmpty())
                    {
                        vigkeyinputlayout.setErrorEnabled(true);
                        vigkeyinputlayout.setError("Key is empty!");
                        help.setVisibility(View.INVISIBLE);
                    }else if(m.find())
                    {
                        vigkeyinputlayout.setErrorEnabled(true);
                        vigkeyinputlayout.setError("Key is must only contain alphabets & no spacing!");
                        help.setVisibility(View.INVISIBLE);
                    } else
                    // editPlain = (EditText) view.findViewById(R.id.inputPT);
                    {
                        msg = msg.replaceAll("[^A-Za-z]+", "");
                        msg = msg.toLowerCase();
                        mastercipher=msg;
                        String plaintext = "";

                        String tempplaintext = "";
                        int counter2 = 0;

                        while (counter2 <= (msg.length() - 1)) {
                            for (int i = 0; i < key.length(); i++) {
                                if (counter2 <= (msg.length() - 1)) {
                                    char tmp = key.charAt(i);
                                    double temp = (double) tmp - 97; //a = 0
                                    char cipTmp = msg.charAt(counter2);
                                    double cipTemp = (double) cipTmp - 97;
                                    double dec = cipTemp - temp;
                                    if (dec < 0) {
                                        dec = dec + 26;
                                    } else {
                                        dec = dec % 26.0;
                                    }
                                    dec = dec + 97;
                                    char decTmp = (char) dec;
                                    tempplaintext = tempplaintext + Character.toString(decTmp);
                                    counter2++;
                                }
                            }
                        }
                        displayResult.setMovementMethod(new ScrollingMovementMethod());
                        displayResult.setText(tempplaintext);
                        help.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else
        {
            key_cipherview.setVisibility(View.VISIBLE);
            enc_decview.setVisibility(View.GONE);
            back_resetview.setVisibility(View.GONE);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.keyoutputgroup);
            p.setMargins(30, 30, 30, 30);
            outputview.setLayoutParams(p);

            type = getIntent().getStringExtra("TYPE");
            cipher = getIntent().getStringExtra("CIPHER");
            key = getIntent().getStringExtra("KEY");
            mastercipher = cipher;

            gamecipheroutput_input.setText(cipher);
            keyinputfromhome.setText(key);

            if(type.equals("0")) // enc
            {
                //cipher = cipher.replaceAll("[^A-Za-z]+","");
                cipher = cipher.toLowerCase();

                String tempcipher = "";
                int counter = 0;
                while (counter <= (cipher.length()-1))
                {
                    for (int i = 0; i < key.length(); i++)
                    {
                        if (counter <= (cipher.length()-1))
                        {
                            //keyword char index
                            char tmp = key.charAt(i);
                            int temp = (int)tmp - 97; //a = 0

                            //PT char index
                            char msgTmp = cipher.charAt(counter);
                            int msgTemp = (int)msgTmp - 97;

                            //encrypted char index
                            int enc = ((msgTemp + temp) % 26) + 97;
                            char encTmp = (char)enc;

                            tempcipher = tempcipher + Character.toString(encTmp);

                            counter++;
                        }
                    }
                }

                displayResult.setMovementMethod(new ScrollingMovementMethod());
                //displayResult.setText(msg);
                displayResult.setText(tempcipher);
            }
            else
            {
                key = key.replaceAll("[^A-Za-z]+","");
                key = key.toLowerCase();

                // editPlain = (EditText) view.findViewById(R.id.inputPT);
                //cipher = cipher.replaceAll("[^A-Za-z]+","");
                cipher = cipher.toLowerCase();

                String tempplaintext = "";
                int counter2 = 0;

                while (counter2 <= (cipher.length()-1))
                {
                    for (int i = 0; i < key.length(); i++)
                    {
                        if (counter2 <= (cipher.length()-1))
                        {
                            char tmp = key.charAt(i);
                            double temp = (double)tmp - 97; //a = 0
                            char cipTmp = cipher.charAt(counter2);
                            double cipTemp = (double)cipTmp - 97;
                            double dec = cipTemp - temp;
                            if (dec < 0)
                            {
                                dec = dec + 26;
                            }
                            else
                            {
                                dec = dec % 26.0;
                            }
                            dec = dec + 97;
                            char decTmp = (char)dec;
                            tempplaintext = tempplaintext + Character.toString(decTmp);
                            counter2++;
                        }
                    }
                }
                displayResult.setMovementMethod(new ScrollingMovementMethod());
                displayResult.setText(tempplaintext);
            }

        }

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharetext = displayResult.getText().toString();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                if(type.equals("0"))//
                {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my encrypted cipher! Try to decrypt it!\n Cipher:\n" + sharetext);
                }
                else
                {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my decrypted cipher! cool right?!\n Cipher: \n " + sharetext);
                }
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Share using..."));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vighelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vighelpex:
                // do your sign-out stuff
                break;
            default:
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
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


    public boolean validateKey()
    {
        String temp = editKey.getText().toString().trim();


        if(temp.isEmpty())
        {
            vigkeyinputlayout.setError("Key Not entered");
            return false;
        }
        else
        {
            vigkeyinputlayout.setErrorEnabled(false);
            return true;
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch(view.getId())
            {
                case R.id.inputkeysub : validateKey();
                    break;
            }
        }
    }

}
