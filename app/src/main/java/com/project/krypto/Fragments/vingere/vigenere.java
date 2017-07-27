package com.project.krypto.Fragments.vingere;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.viglvl;
import com.project.krypto.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link vigenere.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link vigenere#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vigenere extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static EditText editKey;
    private static EditText editPlain;
    private static Button encrypt;
    private static Button decrypt;
    private static Button reset,back;
    private static TextView displayResult;
    private String cipherFromGame;
    private String level;
    private boolean fromGame;

    final Handler handler = new Handler();
    test t = new test();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public vigenere() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment vigenere.
     */
    // TODO: Rename and change types and number of parameters
    public static vigenere newInstance(String param1, String param2) {
        vigenere fragment = new vigenere();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cipherFromGame = getArguments().getString("GameCipher");
            fromGame = getArguments().getBoolean("fromGame");
            level = getArguments().getString("level");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_vigenere, container, false);

        setHasOptionsMenu(true);

        back = (Button) view.findViewById(R.id.backButtonGameVig);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game;
                switch (level)
                {
                    case "1" :  game = new Intent (getContext(), GameActivity.class);
                        startActivity(game);
                        break;
                    case "2" : game = new Intent (getContext(), viglvl.class);
                        startActivity(game);
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                }
            }
        });
        encrypt = (Button) view.findViewById(R.id.btnEnc);
        decrypt = (Button) view.findViewById(R.id.btnDec);
        displayResult = (TextView) view.findViewById(R.id.Output);
        editKey = (EditText) view.findViewById(R.id.inputkey);
        editPlain = (EditText) view.findViewById(R.id.inputPT);
        reset = (Button) view.findViewById(R.id.btnreset);

        if(fromGame == true)
        {
            editPlain.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
        }
        else
        {
            back.setVisibility(View.GONE);
        }

        encrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String keyword = editKey.getText().toString();
                String msg = editPlain.getText().toString();
                msg = msg.replaceAll("[^A-Za-z]+","");
                msg = msg.toLowerCase();


                String tempcipher = "";
                int counter = 0;
                while (counter <= (msg.length()-1))
                {
                    for (int i = 0; i < keyword.length(); i++)
                    {
                        if (counter <= (msg.length()-1))
                        {
                            //keyword char index
                            char tmp = keyword.charAt(i);
                            int temp = (int)tmp - 97; //a = 0

                            //PT char index
                            char msgTmp = msg.charAt(counter);
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
                String sent = "";
                String colorCodeStart = "<font color='#FF0000'>";  // use any color as  your want
                String colorCodeEnd = "</font>";
                for (int i = 0; i < tempcipher.length(); i++) {
                    String testmsg = "";
                    t = new test();
                    sent += tempcipher.charAt(i);
                    testmsg = msg.substring(i+1, tempcipher.length());
                    String comb = "<(>" + sent + "<)>" + testmsg; // <(> and <)> to mark the start and end of the color change
                    comb =  comb.replace("<(>",colorCodeStart); //start of color change
                    comb=  comb.replace("<)>",colorCodeEnd); // end of color change
                    t.cipher = comb;
                    t.counter = i;
                    t.run();
                }
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //editKey = (EditText) view.findViewById(R.id.inputkey);
                String keyword = editKey.getText().toString();
                keyword = keyword.replaceAll("[^A-Za-z]+","");
                keyword = keyword.toLowerCase();

               // editPlain = (EditText) view.findViewById(R.id.inputPT);
                String msg = editPlain.getText().toString();
                msg = msg.replaceAll("[^A-Za-z]+","");
                msg = msg.toLowerCase();
                String plaintext = "";

                String tempplaintext = "";
                int counter2 = 0;

                while (counter2 <= (msg.length()-1))
                {
                    for (int i = 0; i < keyword.length(); i++)
                    {
                        if (counter2 <= (msg.length()-1))
                        {
                            char tmp = keyword.charAt(i);
                            double temp = (double)tmp - 97; //a = 0
                            char cipTmp = msg.charAt(counter2);
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
                String sent = "";
                String colorCodeStart = "<font color='#FF0000'>";  // use any color as  your want
                String colorCodeEnd = "</font>";
                for (int i = 0; i < tempplaintext.length(); i++) {
                    String testmsg = "";
                    t = new test();
                    sent += tempplaintext.charAt(i);
                    testmsg = msg.substring(i+1, tempplaintext.length());
                    String comb = "<(>" + sent + "<)>" + testmsg;
                    comb =  comb.replace("<(>",colorCodeStart); //start of color change
                    comb=  comb.replace("<)>",colorCodeEnd); // end of color change
                    t.cipher = comb;
                    t.counter = i;
                    t.run();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                (editKey).setText("");
                (editPlain).setText("");
                displayResult.setText("");
                handler.removeMessages(0);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.vighelp, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    class test implements Runnable
    {
        String cipher = "";
        int counter = 0;
        @Override
        public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("cipher", cipher);
                    displayResult.setText(Html.fromHtml(cipher));
                }
            }, 200*counter);
        }
    }
}
