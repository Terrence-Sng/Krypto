package com.project.krypto.Fragments.Sub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.krypto.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link subCipher.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link subCipher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class subCipher extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText input;
    Button sub;
    Button insert;
    Button reset;
    Button getHome;
    TextView before;
    TextView after;
    String globalText = "";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public subCipher() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment subCipher.
     */
    // TODO: Rename and change types and number of parameters
    public static subCipher newInstance(String param1, String param2) {
        subCipher fragment = new subCipher();
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
        final View view = inflater.inflate(R.layout.fragment_sub_cipher, container, false);

        //Sub this
        final Spinner dropdown = (Spinner) view.findViewById(R.id.spinner1);
        String[] items = new String[]{"a", "b", "c", "d", "e" ,"f", "g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //with this
        final Spinner dropdown2 = (Spinner) view.findViewById(R.id.spinner2);
        String[] items2 = new String[]{"A", "B", "C", "D", "E" ,"F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        //declare
        sub = (Button)  view.findViewById(R.id.btnsub);
        insert = (Button)  view.findViewById(R.id.btninsert);
        reset = (Button)  view.findViewById(R.id.btnreset);
        before = (TextView)  view.findViewById(R.id.before);
        after = (TextView)  view.findViewById(R.id.after);
        input = (EditText)  view.findViewById(R.id.input);
        getHome = (Button) view.findViewById(R.id.getFromHome);
        Log.d ("log", "Hello " + globalText);

        before.setMovementMethod(new ScrollingMovementMethod());
        before.setTextIsSelectable(true);
        after.setMovementMethod(new ScrollingMovementMethod());
        after.setTextIsSelectable(true);

        getHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!globalText.isEmpty()) {
                    input.setText(globalText);
                    String beforetext = globalText;
                    beforetext = beforetext.toLowerCase();
                    before.setText(beforetext);
                    after.setText(beforetext);

                }
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String beforetext = input.getText().toString();
                beforetext = beforetext.toLowerCase();
                before.setText(beforetext);
                after.setText(beforetext);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String choice = dropdown.getSelectedItem().toString();
                String replace = dropdown2.getSelectedItem().toString();
                String aftertext = after.getText().toString();
                aftertext = aftertext.replaceAll(choice, replace);
                after.setText(aftertext);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aftertext = before.getText().toString();
                after.setText(aftertext);
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
