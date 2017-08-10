package com.project.krypto.act_tools.Home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.project.krypto.R;
import com.project.krypto.act_tools.Sub.SubCipher2;
import com.project.krypto.act_tools.ioc.ioc;
import com.project.krypto.act_tools.nGram.nGramCounter;
import com.project.krypto.act_tools.period.period;
import com.project.krypto.act_tools.transpo.transpo;
import com.project.krypto.act_tools.vingere.vigenere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PREF_FILE = "MyPrefsFile"; // Name of prefs file; don't change this after it's saved something

    private String mParam1, mParam2, cipher;
    Toolbar toolbar;
    private EditText input, inputkey;
    Button ngram, toIOC, period, go;

    RadioGroup radioGroup;
    RadioButton radiochoice;
    RelativeLayout homegroupbuttons;
    TextInputLayout homekey, homeinput;

    int enc_dec_choice = 0; //default is enc, enc = 0, dec = 1;
    SharedPreferences settings = null;
    private OnFragmentInteractionListener mListener;
    boolean firstRun;

    public HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        //for the first run algo
        settings = getActivity().getSharedPreferences(PREF_FILE, 0);
        firstRun = settings.getBoolean("firstRun", true); // Is it first run? If not specified, use "true"

        homeinput  = (TextInputLayout) view.findViewById(R.id.inputHome);
        homekey = (TextInputLayout) view.findViewById(R.id.keylayouthome);
        homegroupbuttons = (RelativeLayout) view.findViewById(R.id.homebuttongroup);
        input = (EditText) view.findViewById(R.id.homeText);
        input.addTextChangedListener(new MyTextWatcher(input));
        homeinput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v);
                }
            }
        });
        ngram = (Button) view.findViewById(R.id.btnngram);
        toIOC = (Button) view.findViewById(R.id.btnioc);
        period = (Button) view.findViewById(R.id.btnperiod);
        inputkey = (EditText) view.findViewById(R.id.inputkey);
        inputkey.addTextChangedListener(new MyTextWatcher(inputkey));
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        go = (Button) view.findViewById(R.id.btngo);

        final Spinner dropdown = (Spinner) view.findViewById(R.id.spinnerchoice);
        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.homearray, R.layout.spinner_text);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);

        //for displaying of appropriate object for each selection from dropdown menu
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
                String choice = dropdown.getSelectedItem().toString();
                if (choice.equals("Analysis")) {
                    enc_dec_choice = 0;
                    radioGroup.setVisibility(View.INVISIBLE);
                   homekey.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.INVISIBLE);
                  homegroupbuttons.setVisibility(View.VISIBLE);
                } else if (choice.equals("Encrypt")) {
                    enc_dec_choice = 0;
                    homegroupbuttons.setVisibility(View.INVISIBLE);

                    radioGroup.setVisibility(View.VISIBLE);
                    homekey.setVisibility(View.VISIBLE);
                    go.setVisibility(View.VISIBLE);
                } else if (choice.equals("Decrypt")) {
                    enc_dec_choice = 1;
                    homegroupbuttons.setVisibility(View.INVISIBLE);

                    radioGroup.setVisibility(View.VISIBLE);
                    homekey.setVisibility(View.VISIBLE);
                    go.setVisibility(View.VISIBLE);
                } else if (choice.equals("None Selected")) {
                    enc_dec_choice = 0;
                    homegroupbuttons.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    homekey.setVisibility(View.INVISIBLE);
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
                cipher = input.getText().toString().replaceAll("[^A-Za-z]+","");
                if(validateCipher())
                {
                    Intent mIntent = new Intent (getContext(), nGramCounter.class);
                    mIntent.putExtra("CIPHER", cipher);
                    startActivity(mIntent);
                }
                else
                {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("ERROR!")
                            .setMessage("No cipher field is empty! Are you sure you have entered a cipher?")
                            .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                //go to ngram with input text

            }
        });

        toIOC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cipher = input.getText().toString().replaceAll("[^A-Za-z]+","");
                if(validateCipher())
                {
                    Intent mIntent = new Intent (getContext(), ioc.class);
                    mIntent.putExtra("CIPHER", cipher);
                    startActivity(mIntent);
                }
                else
                {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("ERROR!")
                            .setMessage("No cipher field is empty! Are you sure you have entered a cipher?")
                            .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        period.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to period with input text
                cipher = input.getText().toString().replaceAll("[^A-Za-z]+","");
                if(validateCipher())
                {
                    Intent mIntent = new Intent (getContext(), period.class);
                    mIntent.putExtra("CIPHER", cipher);
                    startActivity(mIntent);
                }
                else
                {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("ERROR!")
                            .setMessage("No cipher field is empty! Are you sure you have entered a cipher?")
                            .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //String choice = dropdown.getSelectedItem().toString(); //encrypt or decrypt --> so if encrypt run the encryption algo at the cipher page, same for decryption
                cipher = input.getText().toString().replaceAll("[^A-Za-z]+","");
                String keyword = inputkey.getText().toString();//
                //Toast.makeText(getContext(), keyword, Toast.LENGTH_SHORT).show();

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radiochoice = (RadioButton) view.findViewById(selectedId);
                //check if radio button has been checked.
                if (radiochoice == null || keyword.trim().isEmpty() || cipher.trim().isEmpty()) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    if(radiochoice==null) {
                        builder.setTitle("ERROR!")
                                .setMessage("No ciphers selected! Are you sure you have chosen?")
                                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                    if(keyword.trim().isEmpty())
                    {
                        builder.setTitle("ERROR!")
                                .setMessage("Key field is empty! Are you sure you have entered?")
                                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        homekey.setError("Key Not entered");
                    }
                    else
                    {

                        homekey.setErrorEnabled(false);
                    }
                    if(cipher.trim().isEmpty())
                    {
                        builder.setTitle("ERROR!")
                                .setMessage("Cipher field is empty! Are you sure you have entered?")
                                .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        homeinput.setError("Cipher not Entered!");
                    }
                    else
                    {
                        homeinput.setErrorEnabled(false);
                    }
                }
                else {
                    //get cipher choice

                    String cipherchoice = radiochoice.getText().toString();
                    if (cipherchoice.equals("Subsitution")) {
                        //pass values and go to sub
                        keyword.replaceAll("[^A-Za-z]+","");
                        Pattern p = Pattern.compile("[^A-Za-z]");
                        Matcher m = p.matcher(keyword);
                        if(m.find())
                        {
                            homekey.setErrorEnabled(true);
                            homekey.setError("Keyword contains illegal characters");
                            return;
                        }
                            Intent mIntent = new Intent(getContext(), SubCipher2.class);
                            mIntent.putExtra("CIPHER", cipher);
                            mIntent.putExtra("KEY", keyword);
                            mIntent.putExtra("TYPE", enc_dec_choice + "");
                            startActivity(mIntent);
                    } else if (cipherchoice.equals("Vigenere")) {
                        keyword.replaceAll("[^A-Za-z]+","");
                        Pattern p = Pattern.compile("[^A-Za-z]");
                        Matcher m = p.matcher(keyword);
                        if(m.find())
                        {
                            homekey.setErrorEnabled(true);
                            homekey.setError("Keyword contains illegal characters");
                            return;
                        }
                            Intent mIntent = new Intent (getContext(), vigenere.class);
                            mIntent.putExtra("CIPHER", cipher);
                            mIntent.putExtra("KEY", keyword);
                            mIntent.putExtra("TYPE", enc_dec_choice+"");
                            mIntent.putExtra("GAME", false);
                            startActivity(mIntent);
                        //pass values and go to vig
                    } else if (cipherchoice.equals("Transposition")) {
                        //pass values and go to trans
                        int blksize;
                        try{
                            blksize = Integer.parseInt(keyword);
                        }catch(Exception e)
                        {
                            homekey.setError("Only alphabets allowed!");
                            homekey.setErrorEnabled(true);
                            return;
                        }
                        homekey.setErrorEnabled(false);
                        if(blksize > 13)
                        {
                            homekey.setError("Block size too large");
                            homekey.setErrorEnabled(true);
                            return;
                        }else if (blksize <= 0)
                        {
                            homekey.setError("Block size is 0");
                            homekey.setErrorEnabled(true);
                            return;
                        }
                        Pattern p = Pattern.compile("[^A-Za-z]");
                        Matcher m = p.matcher(keyword);
                        p = Pattern.compile("[0-9]");
                        m = p.matcher(keyword);
                        if(m.find())
                        {
                            Intent mIntent = new Intent (getContext(), transpo.class);
                            mIntent.putExtra("CIPHER", cipher);
                            mIntent.putExtra("KEY", keyword);
                            mIntent.putExtra("TYPE", enc_dec_choice+"");
                            mIntent.putExtra("GAME", false);
                            startActivity(mIntent);
                        }
                        else
                        {
                            homekey.setErrorEnabled(true);
                            homekey.setError("Keyword must contain number only");
                            return;
                        }

                    } else {

                    }
                }
            }
        });




        return view;
    }


    //this will check for the first run of the application
    @Override
    public void onResume() {
        super.onResume();

        if (firstRun) {
            Log.w("activity", "first time");
            SharedPreferences.Editor editor = settings.edit(); // Open the editor for our settings
           /* editor.putBoolean("firstRun", false); // It is no longer the first run
            editor.commit(); // Save all changed settings
*/
            final AppCompatActivity act = (AppCompatActivity) getActivity();
            Toolbar toolbar;
            if (act.getSupportActionBar() != null) {
                toolbar = (Toolbar) act.getSupportActionBar().getCustomView();

            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch(Exception e) {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void sendText (String text);
    }

    public boolean validateCipher()
    {
        String s = input.getText().toString().trim();

        if(s.isEmpty())
        {
            homeinput.setError("Cipher not Entered!");
            return false;
        }
        else
        {
            homeinput.setErrorEnabled(false);
            return true;
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean validateKey()
    {
        String temp = inputkey.getText().toString().trim();


        if(temp.isEmpty())
        {
                homekey.setError("Key Not entered");
            return false;
        }
        else
        {
            homekey.setErrorEnabled(false);
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
                case R.id.homeText : validateCipher();
                    break;
                case R.id.inputkey : validateKey();
                    break;
            }
        }
    }
}
