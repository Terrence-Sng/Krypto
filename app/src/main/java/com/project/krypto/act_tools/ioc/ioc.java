package com.project.krypto.act_tools.ioc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.finallvl;
import com.project.krypto.Game.transpolvl;
import com.project.krypto.Game.viglvl;
import com.project.krypto.Help.imagehelp;
import com.project.krypto.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class ioc extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static EditText editText;
    private static Button calIOC;
    private static TextView displayIOC;
    private static TextView displayInput;
    private static Button back;
    private static Button reset;
    private static String globalText = "";

    // TODO: Rename and change types of parameters
    private String cipherFromGame;
    private String level;
    private boolean fromGame;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ioc);

        toolbar = (Toolbar) findViewById(R.id.toolbarioc);
        toolbar.setTitle("Index of Conincidence");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        fromGame = getIntent().getBooleanExtra("GAME", false);
        level = getIntent().getStringExtra("LEVEL");
        cipherFromGame = getIntent().getStringExtra("GAMECIPHER");

        back = (Button) findViewById(R.id.backButtonGameIOC);
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
                    case "3":game = new Intent (getBaseContext(), transpolvl.class);
                        startActivity(game);
                        break;
                    case "4":
                        game = new Intent (getBaseContext(), finallvl.class);
                        startActivity(game);
                        break;
                }
            }
        });
        calIOC = (Button) findViewById(R.id.calioc);
        displayIOC = (TextView)findViewById(R.id.displayioc);
        displayInput = (TextView)findViewById(R.id.inputpreviewIOC);
        if(fromGame == true)
        {
            displayInput.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
        }else {
            displayInput.setText(getIntent().getStringExtra("CIPHER"));
            back.setVisibility(View.GONE);
        }
        //back = (Button)findViewById(R.id.btnBack);
        //button for calculating IOC
        calIOC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //editText = (EditText) view.findViewById(R.id.editText);
                String pt = displayInput.getText().toString();
                if (!pt.isEmpty()) {
                    String lowcontent = pt.toLowerCase();
                    String plain = lowcontent.replaceAll("[^A-Za-z]+", "");
                    char character;
                    int count = 0;
                    int n = 0;
                    ArrayList<Integer> freq = new ArrayList<Integer>();
                    freq.addAll(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
                    int a = 0;
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        count = 0;
                        for (int position = 0; position < plain.length(); position++) {
                            character = plain.charAt(position);
                            if (character == letter) {
                                count++;
                                n++;
                            }
                        }
                        if (count != 0) {
                            a = letter - 97;
                            freq.add(a, count);
                        }
                    }
                    //calculate the IC of the content
                    double sum = 0.0;
                    int f;
                    for (int i = 0; i < 26; i++) {
                        f = freq.get(i);
                        sum = sum + (f * (f - 1));
                    }
                    double IC = 0.00000;
                    IC = sum / (n * (n - 1));
                    DecimalFormat precision = new DecimalFormat("0.00000");
                    //display IOC in textbox
                    displayIOC.setText(precision.format(IC));
                    //display input in textbox
                    displayInput.setMovementMethod(new ScrollingMovementMethod());
                    //displayInput.setText(pt);
                }
            }
        });

        //back button
        /* removed back button because they navigate by navigation drawer
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(ioc_old.this, MainActivity.class);
                startActivity(i);
            }
        });*/
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.iochelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iochelpex:
                Intent mHelp = new Intent (this, imagehelp.class);
                mHelp.putExtra("HELPID", "2");
                startActivity(mHelp);
                // do your sign-out stuff
                break;
            default:
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

}
