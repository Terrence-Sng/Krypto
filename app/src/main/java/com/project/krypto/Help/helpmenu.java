package com.project.krypto.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.project.krypto.R;

/**
 * Created by Panda on 8/4/2017.
 */
/*choose enc or dec or users*/
public class helpmenu extends AppCompatActivity {
    Button enc, dec;
    String cipher, key, extras, typeofcipher,keystring;
    Toolbar toolbar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enc_dec);

        toolbar = (Toolbar) findViewById(R.id.toolbarhelpmenu);
        toolbar.setTitle("");
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


        cipher = getIntent().getStringExtra("CIPHER");
        key = getIntent().getStringExtra("KEY");
        extras = getIntent().getStringExtra("RESULT"); //get from vig
        keystring = getIntent().getStringExtra("KEYSTRING");
        typeofcipher = getIntent().getStringExtra("TYPECIPHER");
        enc = (Button) findViewById(R.id.helpmenuenc);
        dec = (Button) findViewById(R.id.helpmenudec);

        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent;
                switch (typeofcipher)
                {
                    case "S":
                        mIntent= new Intent(getBaseContext(), subencrypthelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                        mIntent.putExtra("KEYSTRING",keystring);
                        //mIntent.putExtra("TYPE", "0");//enc=0; // sub has two does not need to state;
                        startActivity(mIntent);
                        break;
                    case "V":
                        mIntent= new Intent(getBaseContext(), vigHelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                        mIntent.putExtra("RESULT",extras);
                      //mIntent.putExtra("TYPE", "0");//enc=0; // vig does not need to state;
                        startActivity(mIntent);
                        break;
                    case "T":
                        mIntent = new Intent(getBaseContext(), transpohelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                       // mIntent.putExtra("RESULT",extras);
                        mIntent.putExtra("TYPE", "0");//enc=0; // vig does not need to state;
                        startActivity(mIntent);
                        break;
                }

            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent;
                switch (typeofcipher)
                {
                    case "S":
                        mIntent= new Intent(getBaseContext(), subdecrypthelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                        mIntent.putExtra("KEYSTRING",keystring);
                        //mIntent.putExtra("TYPE", "0");//enc=0; // sub has two does not need to state;
                        startActivity(mIntent);
                        break;
                    case "V": mIntent = new Intent(getBaseContext(), vigHelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                        mIntent.putExtra("RESULT",extras);
                        //mIntent.putExtra("TYPE", "0");//enc=0; // vig does not need to state;
                        startActivity(mIntent);
                        break;
                    case "T":    mIntent = new Intent(getBaseContext(), transpohelp.class);
                        mIntent.putExtra("CIPHER", cipher);
                        mIntent.putExtra("KEY",key);
                        // mIntent.putExtra("RESULT",extras);
                        mIntent.putExtra("TYPE", "0");//enc=0; // vig does not need to state;
                        startActivity(mIntent);
                        break;
                }
            }
        });
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
