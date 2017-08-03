package com.project.krypto.act_tools.Sub;

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
import android.widget.TextView;

import com.project.krypto.R;

/**
 * Created by Panda on 7/20/2017.
 */

public class SubCipher2  extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editkey;
    private EditText editText;
    Button encrypt;
    Button help;
    Button share;
    TextView outputkey;
    TextView outputtext;


    public static String p1 = "abcdefghijklmnopqrstuvwxys";
    public static String k1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String ch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcipher2);

        toolbar = (Toolbar) findViewById(R.id.toolbarSubCipher);
        toolbar.setTitle("Keyword Option");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        setStatusBarColor();

        final int type = Integer.parseInt(getIntent().getStringExtra("TYPE"));
        String cipher = getIntent().getStringExtra("CIPHER");
        String key = getIntent().getStringExtra("KEY");

        help = (Button) findViewById(R.id.btnhelp);
        share = (Button) findViewById(R.id.btnshare);
        outputkey = (TextView) findViewById(R.id.outputkey);
        outputtext = (TextView) findViewById(R.id.output);

        if (type == 1) {
            //editkey = (EditText) findViewById(R.id.editkey);
            String ch = key;
            String tempkey = key;
            char so[] = ch.toCharArray();
                /*for (int i = 0; i < ch.length();)
                {
                    int b = (int)so[i];
                    if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122))
                    {i++;}
                    else
                    {
                        System.out.println("The key should contain only character (A-Z)");
                        System.exit(0);
                    }
                }*/
            int count = 0, len = 0;
            do {
                try {
                    char name[] = ch.toCharArray();
                    len = name.length;
                    count = 0;
                    for (int j = 0; j < len; j++) {
                        if ((name[0] == name[j]) && ((name[0] >= 65 && name[0] <= 91) || (name[0] >= 97 && name[0] <= 123)))
                            count++;
                    }

                    if (count != 0)
                        ch = ch.replace("" + name[0], "");
                    tempkey += name[0];

                } catch (Exception ex) {
                }
            }
            while (len != 0);

            String k1 = keygen(key.toUpperCase());
            outputkey.setText("Plaintext: " + "\n" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "\n" + "Ciphertext: " + "\n" + k1);
            ch1 = k1;
            editText = (EditText) findViewById(R.id.editText);
            String ct = key;
            String de = doDecryption(ct.toUpperCase());
            outputtext.setText(de);
        }
        else {
            String ch = key;
            String tempkey = key;
            char so[]= ch.toCharArray();
                /*for (int i = 0; i < ch.length();)
                {
                    int b = (int)so[i];
                    if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122))
                    {i++;}
                    else
                    {
                        System.out.println("The key should contain only character (A-Z)");
                        System.exit(0);
                    }
                }*/
            int count=0,len=0;
            do
            {
                try
                {
                    char name[]=ch.toCharArray();
                    len=name.length;
                    count=0;
                    for(int j=0;j<len;j++)
                    {
                        if((name[0]==name[j])&&((name[0]>=65&&name[0]<=91)||(name[0]>=97&&name[0]<=123)))
                            count++;
                    }

                    if(count!=0)
                        ch=ch.replace(""+name[0],"");
                    tempkey += name[0];

                }
                catch(Exception ex){}
            }
            while(len!=0);
            String k1 = keygen(key.toUpperCase());
            outputkey.setText("Plaintext: " + "\n" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "\n" + "Ciphertext: " + "\n" + k1);
            ch1 = k1;
            editText = (EditText) findViewById(R.id.editText);
            String pl = key;
            String en = doEncryption(pl.toLowerCase());
            outputtext.setText(en);
        }

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sharetext = outputtext.getText().toString();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                if(type == 0)//
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

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
    public static String keygen(String s)
    {
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String c1 = s + c;
        String realkey = "";
        int count=0,len=0;
        do
        {
            try
            {
                char name[]=c1.toCharArray();
                len=name.length;
                count=0;
                for(int j=0;j<len;j++)
                {
                    if((name[0]==name[j])&&((name[0]>=65&&name[0]<=91)||(name[0]>=97&&name[0]<=123)))
                        count++;
                }

                if(count!=0)
                    c1=c1.replace(""+name[0],"");
                realkey += name[0];

            }
            catch(Exception ex){}
        }
        while(len!=0);
        return (new String(realkey));
    }

    public static String doEncryption(String s)
    {	char[] ch = ch1.toCharArray();
        char[] k = k1.toCharArray();
        char[] p = p1.toCharArray();
        char so[]= s.toCharArray();
        char c[] = new char[(s.length())];
        for (int i = 0; i < s.length(); i++)
        {	int b = (int)so[i];
            if ( b < 97 || b > 122)
            {
                c[i] = s.charAt(i);
            }
            else
                for (int j = 0; j < 26; j++)
                {
                    if (p[j] == s.charAt(i))
                    {
                        c[i] = ch[j];
                        break;
                    }

                }
        }
        return (new String(c));
    }

    public static String doDecryption(String s)
    {	char[] ch = ch1.toCharArray();
        char[] k = k1.toCharArray();
        char[] p = p1.toCharArray();
        char so[]= s.toCharArray();
        char p1[] = new char[(s.length())];
        for (int i = 0; i < s.length(); i++)
        {	int b = (int)so[i];
            if ( b < 65 || b > 90)
            {
                p1[i] = s.charAt(i);
            }
            else
                for (int j = 0; j < 26; j++)
                {
                    if (ch[j] == s.charAt(i))
                    {
                        p1[i] = p[j];
                        break;
                    }
                }
        }
        return (new String(p1));
    }

}
