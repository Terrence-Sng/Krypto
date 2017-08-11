package com.project.krypto.act_tools.Sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.finallvl;
import com.project.krypto.Game.transpolvl;
import com.project.krypto.Game.viglvl;
import com.project.krypto.Help.helpmenu;
import com.project.krypto.Help.subdecrypthelp;
import com.project.krypto.Help.subencrypthelp;
import com.project.krypto.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Panda on 7/20/2017.
 */

public class SubCipher2  extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editkey;
    private TextView inputfromgameTV;
    Button encrypt, decrypt, help,share,back, exit;
    TextView outputkey;
    TextView outputtext;

    private static String preset_plaintext_p1 = "abcdefghijklmnopqrstuvwxys";
    private static String key_ch1;
    private String level, cipherFromGame;
    private String type, cipher, key;
    private String mastercipher, masterkey;
    boolean fromGame;
    TextInputLayout keyinputlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcipher2);

        toolbar = (Toolbar) findViewById(R.id.toolbarSubCipher);
        toolbar.setTitle("Keyword Option");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        setStatusBarColor();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });


        type = getIntent().getStringExtra("TYPE");
        cipher = getIntent().getStringExtra("CIPHER");
        key = getIntent().getStringExtra("KEY");
        level = getIntent().getStringExtra("LEVEL");
        cipherFromGame = getIntent().getStringExtra("GAMECIPHER");
        fromGame = getIntent().getBooleanExtra("GAME", false);

        help = (Button) findViewById(R.id.btnhelpsub);
        help.setOnClickListener(new View.OnClickListener() {
            String SHORT_KEY;
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_SHORT).show();
                if(fromGame==true) {
                    mastercipher = cipherFromGame.replaceAll("[^A-Za-z]+", "");
                    masterkey = editkey.getText().toString().replaceAll("[^A-Za-z]+", "");
                    Pattern p = Pattern.compile("[^A-za-z]");
                    Matcher m = p.matcher(masterkey);
                    if(masterkey.trim().isEmpty())
                    {
                        keyinputlayout.setError("Key is empty!");
                        keyinputlayout.setErrorEnabled(true);
                        return;
                    }else if(m.find())
                    {
                        keyinputlayout.setError("Only alphabets allowed!");
                        keyinputlayout.setErrorEnabled(true);
                        return;
                    }
                    String actual_key_k1 = keygen(masterkey.toUpperCase());
                    //outputkey.setText("Plaintext: " + "\n" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "\n" + "Ciphertext: " + "\n" + actual_key_k1);
                    key_ch1 = actual_key_k1;
                    Intent mIntent = new Intent(getBaseContext(), helpmenu.class);
                    mIntent.putExtra("CIPHER", mastercipher);
                    mIntent.putExtra("KEY", masterkey);
                    mIntent.putExtra("KEYSTRING", key_ch1);
                    // mIntent.putExtra("RESULT", displayResult.getText().toString());
                    mIntent.putExtra("TYPECIPHER", "S");
                    startActivity(mIntent);
                }
                else
                {
                    mastercipher = cipher;
                    masterkey = key;
                    if(type.equals("0"))
                    {
                        Intent mIntent = new Intent(getBaseContext(), subencrypthelp.class);
                        mIntent.putExtra("CIPHER", mastercipher);
                        mIntent.putExtra("KEY", key);
                        mIntent.putExtra("KEYSTRING", key_ch1);
                        // mIntent.putExtra("RESULT", displayResult.getText().toString());
                        mIntent.putExtra("TYPECIPHER", "S");
                        startActivity(mIntent);
                    }
                    else
                    {
                        Intent mIntent = new Intent(getBaseContext(), subdecrypthelp.class);
                        mIntent.putExtra("CIPHER", mastercipher);
                        mIntent.putExtra("KEY", key);
                        mIntent.putExtra("KEYSTRING", key_ch1);
                        // mIntent.putExtra("RESULT", displayResult.getText().toString());
                        mIntent.putExtra("TYPECIPHER", "S");
                        startActivity(mIntent);

                    }

                }

            }
        });
        share = (Button) findViewById(R.id.btnshare);
        //outputkey = (TextView) findViewById(R.id.outputkey);
        outputtext = (TextView) findViewById(R.id.output);
        editkey = (EditText) findViewById(R.id.inputkeysub);
        editkey.addTextChangedListener(new MyTextWatcher(editkey));
        keyinputlayout = (TextInputLayout) findViewById(R.id.inputkeylayoutSub);

        back = (Button) findViewById(R.id.backtogamesub);
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
                    case "3":
                        game = new Intent (getBaseContext(), transpolvl.class);
                        startActivity(game);
                        break;
                    case "4":game = new Intent (getBaseContext(), finallvl.class);
                        startActivity(game);
                        break;
                }
            }
        });

         if(fromGame == true)
         {
             help.setVisibility(View.INVISIBLE);
        RelativeLayout gamekeyinputgroupsub = (RelativeLayout) findViewById(R.id.gamekeyinputgroupsub);
        gamekeyinputgroupsub.setVisibility(View.VISIBLE);
        RelativeLayout encdecbuttongroupsub = (RelativeLayout)findViewById(R.id.encdecbuttongroupsub);
        encdecbuttongroupsub.setVisibility(View.VISIBLE);
             back.setVisibility(View.VISIBLE);
        RelativeLayout subcipheroutputgroup = (RelativeLayout)findViewById(R.id.subcipheroutputGroup);
        RelativeLayout keyinputgroupsub = (RelativeLayout)findViewById(R.id.keyinputhomegroup);
        keyinputgroupsub.setVisibility(View.GONE);
        share.setVisibility(View.GONE);
        encrypt = (Button) findViewById(R.id.btnenc);
        decrypt = (Button) findViewById(R.id.btndec);

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.setMargins(30,30,30,30);
        p.addRule(RelativeLayout.BELOW, R.id.encdecbuttongroupsub);
        subcipheroutputgroup.setLayoutParams(p);


        inputfromgameTV = (TextView) findViewById(R.id.inputfromhome);
        inputfromgameTV.setText(cipherFromGame);
        decrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String key_ch = editkey.getText().toString();
                editkey.addTextChangedListener(new MyTextWatcher(editkey));
                Pattern patt = Pattern.compile("[^A-Za-z]");
                Matcher m = patt.matcher(key_ch);
                if(m.find())
                {
                    keyinputlayout.setError("Only alphabets allowed!");
                    keyinputlayout.setErrorEnabled(true);
                    help.setVisibility(View.INVISIBLE);
                    return;
                }else if(key_ch.isEmpty())
                {
                    help.setVisibility(View.INVISIBLE);
                    return;
                }
                String actual_key_k1 = keygen(key_ch.toUpperCase());
                //outputkey.setText("Plaintext: " + "\n" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "\n" + "Ciphertext: " + "\n" + actual_key_k1);
                key_ch1 = actual_key_k1;

                String temporary_cipher_ct = cipherFromGame;
                String decrypted_text = doDecryption(temporary_cipher_ct.toUpperCase());
                outputtext.setText(decrypted_text);
                help.setVisibility(View.VISIBLE);
            }
        });

        encrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String key_ch = editkey.getText().toString();//
                editkey.addTextChangedListener(new MyTextWatcher(editkey));
                Pattern patt = Pattern.compile("[^A-Za-z]");
                Matcher m = patt.matcher(key_ch);
                if(m.find())
                {
                    keyinputlayout.setError("Only alphabets allowed!");
                    keyinputlayout.setErrorEnabled(true);
                    help.setVisibility(View.INVISIBLE);
                    return;
                }else if(key_ch.isEmpty())
                {
                    help.setVisibility(View.INVISIBLE);
                    return;
                }
                String actual_key_k1 = keygen(key_ch.toUpperCase());
                //outputkey.setText("Plaintext: " + "\n" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "\n" + "Ciphertext: " + "\n" + actual_key_k1);
                key_ch1 = actual_key_k1;
                System.out.println(key_ch + "&" + key_ch1);
                String temporary_plaintext_pl = cipherFromGame;
                String encrypted_text_en = doEncryption(temporary_plaintext_pl.toLowerCase());
                outputtext.setText(encrypted_text_en);
                help.setVisibility(View.VISIBLE);
            }
        });
    }
    else
    {
        TextView inputfromhome = (TextView) findViewById(R.id.inputfromhome);
        inputfromhome.setText(cipher);
        TextView keyfromhome = (TextView) findViewById(R.id.keyfromhome);
        key_ch1 = keygen(key.toUpperCase());
        keyfromhome.setText(key + "\n" + "\nNew Key is : " + key_ch1);

        if(type.equals("0"))//enc
        {
            outputtext.setText(doEncryption(cipher.toLowerCase()));
        }
        else
        {
            System.out.println(cipher.toUpperCase());
            System.out.println(doDecryption(cipher.toUpperCase()));
            outputtext.setText(doDecryption(cipher.toUpperCase()));
        }
        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sharetext = outputtext.getText().toString().trim();
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

    }


    public void setStatusBarColor ()
    {
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.Black));

    }
    public static String keygen(String keyword_s)
    {
        String temporary_key_preset_c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String temporary_key_c1 = keyword_s + temporary_key_preset_c;
        String realkey = "";
        int count=0,length_len=0;
        do
        {
            try
            {
                char character_name[]=temporary_key_c1.toCharArray();
                length_len=character_name.length;
                count=0;
                for(int j=0;j<length_len;j++)
                {
                    if((character_name[0]==character_name[j])&&((character_name[0]>=65&&character_name[0]<=91)||(character_name[0]>=97&&character_name[0]<=123)))
                        count++;
                }

                if(count!=0)
                    temporary_key_c1=temporary_key_c1.replace(""+character_name[0],"");
                realkey += character_name[0];

            }
            catch(Exception ex){}
        }
        while(length_len!=0);
        return (new String(realkey));
    }

    public static String doEncryption(String plaintext_s)
    {	char[] key_array_ch = key_ch1.toCharArray();
        char[] temporary_plaintext_p = preset_plaintext_p1.toCharArray();
        char plaintext_array_so[]= plaintext_s.toCharArray();
        char encrypted_text_c[] = new char[(plaintext_s.length())];
        for (int i = 0; i < plaintext_s.length(); i++)
        {	int b = (int)plaintext_array_so[i];
            if ( b < 97 || b > 122)
            {
                encrypted_text_c[i] = plaintext_s.charAt(i);
            }
            else
                for (int j = 0; j < 26; j++)
                {
                    if (temporary_plaintext_p[j] == plaintext_s.charAt(i))
                    {
                        encrypted_text_c[i] = key_array_ch[j];
                        break;
                    }

                }
        }
        return (new String(encrypted_text_c));
    }


    public static String doDecryption(String cipher_s)
    {	char[] key_array_ch = key_ch1.toCharArray();
        char[] p = preset_plaintext_p1.toCharArray();
        char cipher_array_so[]= cipher_s.toCharArray();
        char plaintext_p1[] = new char[(cipher_s.length())];
        for (int i = 0; i < cipher_s.length(); i++)
        {	int b = (int)cipher_array_so[i];
            if ( b < 65 || b > 90)
            {
                plaintext_p1[i] = cipher_s.charAt(i);
            }
            else
                for (int j = 0; j < 26; j++)
                {
                    if (key_array_ch[j] == cipher_s.charAt(i))
                    {
                        plaintext_p1[i] = p[j];
                        break;
                    }
                }
        }
        return (new String(plaintext_p1));
    }

    public boolean validateKey()
    {
        String temp = editkey.getText().toString().trim();


        if(temp.isEmpty())
        {
            keyinputlayout.setError("Key Not entered");
            return false;
        }
        else
        {
            keyinputlayout.setErrorEnabled(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.subcipherhelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }


}
