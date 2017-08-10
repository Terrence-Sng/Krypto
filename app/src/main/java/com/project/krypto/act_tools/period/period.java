package com.project.krypto.act_tools.period;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class period extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  TextView gText;
    private  EditText editPeriod;
    private  TextView displayResult;
    private  Button back;
    private static String globalText = "";
    TextInputLayout periodlayout;
    // TODO: Rename and change types of parameters
    private String cipherFromGame;
    private String level;
    private boolean fromGame;
    Toolbar toolbar;

    // TODO: Rename and change types and number of parameters
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_period);


        toolbar = (Toolbar) findViewById(R.id.toolbarperiod);
        toolbar.setTitle("Period");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
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
        level = getIntent().getStringExtra("LEVEL");
        cipherFromGame = getIntent().getStringExtra("GAMECIPHER");

        back = (Button) findViewById(R.id.backButtonGamePeriod);
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
        gText = (TextView) findViewById(R.id.periodText);
        if(fromGame == true)
        {
            gText.setText("");
            gText.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
        }
        else{
            gText.setText(getIntent().getStringExtra("CIPHER"));
            back.setVisibility(View.GONE);
        }

        Button calIOC = (Button)findViewById(R.id.calioc);
        displayResult = (TextView) findViewById(R.id.results);
        //back = (Button) findViewById(R.id.btnBack);
        editPeriod = (EditText) findViewById(R.id.editperiod);
        editPeriod.addTextChangedListener(new MyTextWatcher(editPeriod));
        //Button reset = (Button) findViewById(R.id.btnreset);
        periodlayout = (TextInputLayout) findViewById(R.id.editTextPeriod);
        calIOC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pt = gText.getText().toString();
                if(!pt.isEmpty()) {
                    String lowcontent = pt.toLowerCase();
                    String finalInput = lowcontent.replaceAll("[^A-Za-z]+", "");


                    String period = editPeriod.getText().toString();
                    Pattern patt = Pattern.compile("[^0-9]");
                    Matcher m = patt.matcher(period);
                    if(period.isEmpty())
                    {
                        periodlayout.setErrorEnabled(true);
                        periodlayout.setError("Period is empty!");
                        return;
                    }
                    else if(m.find())
                    {
                        periodlayout.setError("Only numbers allowed!");
                        periodlayout.setErrorEnabled(true);
                        return;
                    }else if (period.equals("0"))
                    {
                        periodlayout.setError("Period cannot be 0");
                        periodlayout.setErrorEnabled(true);
                        return;
                    }

                    int p = Integer.valueOf(period);

                    //init the p strings
                    double[] IOC = new double[p];
                    for (int k = 0; k < p; k++) {
                        IOC[k] = 0.0;
                    }

                    //init the p strings
                    String[] table = new String[p];
                    for (int k = 0; k < p; k++) {
                        table[k] = "";
                    }

                    //split input into p strings
                    int counter = 0;
                    while (counter != finalInput.length()) {
                        for (int k = 0; k < p; k++) {
                            if (counter != finalInput.length()) {
                                char temp = finalInput.charAt(counter);
                                table[k] = table[k].concat(Character.toString(temp));
                                counter++;
                            }
                        }
                    }

                    //calculate the IOC for each periods
                    for (int k = 0; k < p; k++) {
                        String plain = table[k];
                        char character;
                        int count = 0;
                        int n = 0;
                        ArrayList<Integer> freq = new ArrayList<>();
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

                        double IC = 0.0;
                        IC = sum / (n * (n - 1));
                        IOC[k] = IC;
                    }

                    displayResult.setText("");
                    //display results
                    for (int k = 0; k < p; k++) {

                        int num = k + 1;
                        DecimalFormat precision = new DecimalFormat("0.00000");
                        displayResult.setMovementMethod(new ScrollingMovementMethod());
                        displayResult.setText(displayResult.getText() + "\n" + "Period " + num + ": " + table[k] + "\n" + "Index of Coincidence: " + precision.format(IOC[k]) + "\n");
                    }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.periodhelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.periodhelpex:
                // do your sign-out stuff
                Intent mHelp = new Intent (this, imagehelp.class);
                mHelp.putExtra("HELPID", "3");
                startActivity(mHelp);
                break;
            default:
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

    public boolean validateKey()
    {
        String temp = editPeriod.getText().toString().trim();


        if(temp.isEmpty())
        {
            periodlayout.setError("Key Not entered");
            return false;
        }
        else
        {
            periodlayout.setErrorEnabled(false);
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
                case R.id.editperiod : validateKey();
                    break;
            }
        }
    }
}
