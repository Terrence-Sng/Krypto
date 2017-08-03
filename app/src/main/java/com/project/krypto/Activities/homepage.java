package com.project.krypto.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.krypto.R;
/**
 * Created by z_x_9 on 29/7/2017.
 */

public class homepage extends Activity {

    private EditText input;
    Button ngram;
    Button toIOC;
    Button period;
    TextView tvchoice;
    private EditText inputkey;
    RadioGroup radioGroup;
    RadioButton radiochoice;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ngram = (Button) findViewById(R.id.btnngram);
        toIOC = (Button) findViewById(R.id.btnioc);
        period = (Button) findViewById(R.id.btnperiod);
        tvchoice = (TextView) findViewById(R.id.tvchoose);
        inputkey = (EditText) findViewById(R.id.inputkey);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        go = (Button) findViewById(R.id.btngo);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinnerchoice);
        String[] items = new String[]{"Select One", "Analysis", "Encrypt", "Decrypt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //for displaying of appropriate object for each selection from dropdown menu
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
                String choice = dropdown.getSelectedItem().toString();
                if (choice.equals("Analysis"))
                {
                    tvchoice.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    inputkey.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.INVISIBLE);

                    ngram.setVisibility(View.VISIBLE);
                    toIOC.setVisibility(View.VISIBLE);
                    period.setVisibility(View.VISIBLE);
                }
                else if (choice.equals("Encrypt"))
                {
                    ngram.setVisibility(View.INVISIBLE);
                    toIOC.setVisibility(View.INVISIBLE);
                    period.setVisibility(View.INVISIBLE);

                    tvchoice.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    inputkey.setVisibility(View.VISIBLE);
                    go.setVisibility(View.VISIBLE);
                }
                else if (choice.equals("Decrypt"))
                {
                    ngram.setVisibility(View.INVISIBLE);
                    toIOC.setVisibility(View.INVISIBLE);
                    period.setVisibility(View.INVISIBLE);

                    tvchoice.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    inputkey.setVisibility(View.VISIBLE);
                    go.setVisibility(View.VISIBLE);
                }
                else if (choice.equals("Select One"))
                {
                    ngram.setVisibility(View.INVISIBLE);
                    toIOC.setVisibility(View.INVISIBLE);
                    period.setVisibility(View.INVISIBLE);

                    tvchoice.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    inputkey.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Buttons for analysis
        ngram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to ngram with input text
            }
        });

        toIOC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to ioc with input text
            }
        });

        period.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to period with input text
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String choice = dropdown.getSelectedItem().toString(); //encrypt or decrypt --> so if encrypt run the encryption algo at the cipher page, same for decryption
                String keyword = inputkey.getText().toString();

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radiochoice = (RadioButton) findViewById(selectedId);

                //get cipher choice
                String cipherchoice = radiochoice.getText().toString();
                if (cipherchoice.equals("Subsitution"))
                {
                    //pass values and go to sub
                }
                else if (cipherchoice.equals("Vigenere"))
                {
                    //pass values and go to vig
                }
                else if (cipherchoice.equals("Transposition"))
                {
                    //pass values and go to trans
                }



            }
        });





    }
}
