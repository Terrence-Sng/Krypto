package com.project.krypto.Fragments.period;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.viglvl;
import com.project.krypto.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link period.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link period#newInstance} factory method to
 * create an instance of this fragment.
 */
public class period extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private  TextView gText;
    private  EditText editPeriod;
    private  TextView displayResult;
    private  Button back;
    private static String globalText = "";

    // TODO: Rename and change types of parameters
    private String cipherFromGame;
    private String level;
    private boolean fromGame;

    private OnFragmentInteractionListener mListener;

    public period() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment period.
     */
    // TODO: Rename and change types and number of parameters
    public static period newInstance(String param1, String param2) {
        period fragment = new period();
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
        final View view = inflater.inflate(R.layout.fragment_period, container, false);

        back = (Button) view.findViewById(R.id.backButtonGamePeriod);
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
        gText = (TextView) view.findViewById(R.id.periodText);
        if(fromGame == true)
        {
            gText.setText(cipherFromGame);
            back.setVisibility(View.VISIBLE);
        }
        else{
            gText.setText(globalText);
            back.setVisibility(View.GONE);
        }

        Button calIOC = (Button) view.findViewById(R.id.calioc);
        displayResult = (TextView) view.findViewById(R.id.results);
        //back = (Button) findViewById(R.id.btnBack);
        editPeriod = (EditText) view.findViewById(R.id.editperiod);
        Button reset = (Button) view.findViewById(R.id.btnreset);

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //(editText).setText("");
                gText.setText("");
                displayResult.setText("");
            }
        });

        calIOC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pt = gText.getText().toString();
                        if(!pt.isEmpty()) {
                            String lowcontent = pt.toLowerCase();
                            String finalInput = lowcontent.replaceAll("[^A-Za-z]+", "");

                            String period = editPeriod.getText().toString();
                            if(period.isEmpty())
                            {
                                Toast.makeText(view.getContext(), "Period is empty!", Toast.LENGTH_SHORT).show();
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

    public void updateText (String text)
    {
        globalText = text;
    }
}
