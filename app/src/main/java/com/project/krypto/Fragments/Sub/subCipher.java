package com.project.krypto.Fragments.Sub;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.R;

import java.util.ArrayList;

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
    Button sub, undo, reset;
    TextView before;
    TextView after;
    String globalText = "";
    String subbedText = "";
    int instance = 0;
    String previousSub = "";
    TableRow tRow, tRow2;
    ArrayList<TextView> letters1 = new ArrayList<>();
    ArrayList<TextView> letters2 = new ArrayList<>();
    String[] letters = new String[]{"A", "B", "C", "D", "E" ,"F", "G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

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
        reset = (Button)  view.findViewById(R.id.btnreset);
        before = (TextView)  view.findViewById(R.id.before);
        before.setTextIsSelectable(true);
        after = (TextView)  view.findViewById(R.id.after);
        after.setTextIsSelectable(true);
        undo = (Button) view.findViewById(R.id.undoBtn);
        reset = (Button) view.findViewById(R.id.resetBtn);
        Log.d ("log", "Hello " + globalText);

        before.setMovementMethod(new ScrollingMovementMethod());
        after.setMovementMethod(new ScrollingMovementMethod());
        String beforetext = globalText;
        beforetext = beforetext.toLowerCase();
        before.setText(beforetext);
        if(instance == 0)
        {
            after.setText(beforetext);

            instance ++;
        }
        else
        {
            after.setText(subbedText);
        }

        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String choice = dropdown.getSelectedItem().toString();
                String replace = dropdown2.getSelectedItem().toString();
                subbedText = before.getText().toString();
                subbedText = subbedText.replaceAll(choice, replace);
                after.setText(subbedText);
                setPrevious(choice, replace);
                updateTable(choice, replace, view);
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = previousSub;
                String [] split = temp.split(",");
                String choice = split[0];
                String replace = split[1];
                String initial = split[2];
                char c = choice.toUpperCase().charAt(0);
                int index = c - 65;
                TextView text = letters2.get(index);
                text.setText(initial);
                letters2.set(index, text);
                if(letters2.get(index).getText().toString().equalsIgnoreCase(letters1.get(index).getText().toString()))
                {
                    setCellColor(index, letters2, ContextCompat.getColor(view.getContext(), R.color.white));
                    setCellColor(index, letters1, ContextCompat.getColor(view.getContext(), R.color.white));
                }
                Toast.makeText(view.getContext(), temp, Toast.LENGTH_SHORT).show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                after.setText(globalText.toLowerCase());
                for(int i =0; i < 26; i++)
                {
                    TextView text = letters2.get(i);
                    text.setText(letters[i]);
                    letters2.set(i, text);
                }
                setCellColor(-1, letters1, ContextCompat.getColor(view.getContext(), R.color.white));
                setCellColor(-1, letters2, ContextCompat.getColor(view.getContext(), R.color.white));
            }
        });

        initTable(view);
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
        tRow.removeAllViews();
        tRow2.removeAllViews();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.subcipher2, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
        globalText = text.replaceAll("[^a-zA-Z]+", "");;
    }

    public String getHomeText ()
    {
        subbedText = globalText;
        return subbedText;
    }

    public void initTable(View view)
    {
        tRow = (TableRow) view.findViewById(R.id.subTableRow1);

        for(int i = 0; i < 26; i ++)
        {
            TextView text = new TextView(view.getContext());
            text.setText(letters[i]);
            text.setWidth(40);
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextColor(ContextCompat.getColor(view.getContext(),R.color.Black));

            letters1.add(text);
        }
        for(int i = 0; i < 26; i ++)
        {
            tRow.addView(letters1.get(i));
        }

        tRow2 = (TableRow) view.findViewById(R.id.subTableRow2);
        for(int i = 0; i < 26; i ++)
        {
            TextView text = new TextView(view.getContext());
            text.setText(letters[i]);
            text.setWidth(40);
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextColor(ContextCompat.getColor(view.getContext(),R.color.Black));

            letters2.add(text);
        }
        for(int i = 0; i < 26; i ++)
        {
            tRow2.addView(letters2.get(i));
        }
    }
    public void updateTable (String choice, String replace, View view)
    {
        char c = choice.toUpperCase().charAt(0);
        int index = c - 65;

        TextView text = letters2.get(index);
        text.setText(replace);
        letters2.set(index, text);

        setCellColor(index, letters1, ContextCompat.getColor(view.getContext(), R.color.btn_logut_bg));
        setCellColor(index, letters2, ContextCompat.getColor(view.getContext(), R.color.btn_logut_bg));
    }

    public void setPrevious (String choice, String replace)
    {
        String temp = choice;
        char t = temp.toUpperCase().charAt(0);
        int index = t - 65;
        previousSub = choice + "," + replace + "," + letters2.get(index).getText().toString();
    }

    public void setCellColor (int index, ArrayList<TextView> letter, int color)
    {
        if(index == -1)
        {
            for(int i = 0; i < 26; i ++) {
                letter.get(i).setBackgroundColor(color);
            }
        }
        else {
            TextView text = letter.get(index);
            text.setBackgroundColor(color);
            letter.set(index, text);
        }

    }
}
