package com.project.krypto.Fragments.nGram;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.R;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link nGramCounter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nGramCounter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nGramCounter extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private com.github.mikephil.charting.charts.BarChart barChart;
    private String[] theDates = new String [] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Float [] letterFreq;
    private String FNAME = "hello_file";
    private String globalText = "";
    private static TextView eText;
    private static TextView vText;
    private static Button bCount;
    private static Spinner spin;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public nGramCounter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nGramCounter.
     */
    // TODO: Rename and change types and number of parameters
    public static nGramCounter newInstance(String param1, String param2) {
        nGramCounter fragment = new nGramCounter();
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
        final View view = inflater.inflate(R.layout.fragment_ngram, container, false);
        //fragmentInterfaces fragInt = (fragmentInterfaces) getActivity();
        // Inflate the layout for this fragment
        //Toast.makeText(view.getContext(), globalText, Toast.LENGTH_SHORT).show();
        eText = (TextView) view.findViewById(R.id.EnterText);
        eText.setText(globalText);
        vText = (TextView) view.findViewById(R.id.showFrequency);
        vText.setVisibility(View.INVISIBLE);
        bCount = (Button) view.findViewById(R.id.button);
        spin = (Spinner) view.findViewById(R.id.spin);
        barChart = (com.github.mikephil.charting.charts.BarChart) view.findViewById(R.id.barChart);

        addItemsOnSpinner2(view);

        bCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = globalText;//eText.getText().toString;
                //Log.d("Log", "hellos " + text);
                if (!text.isEmpty()) {
                    String value = spin.getSelectedItem().toString();
                    //Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();

                    countFreq(view, text, value);
                    /*
                    barChart = (com.github.mikephil.charting.charts.BarChart) view.findViewById(R.id.barChart);

                    ArrayList<BarEntry> barEntries = new ArrayList<>();
                    for (int i = 0; i < 26; i++) {
                        barEntries.add(new BarEntry(i, letterFreq[i]));

                    }
                    BarDataSet barDataSet = new BarDataSet(barEntries, "Frequency");

                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                    dataSets.add((IBarDataSet) barDataSet);

                    BarData theData = new BarData(dataSets);

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setGranularity(0.1f);
                    xAxis.setDrawGridLines(false);
                    xAxis.setLabelCount(25);
                    xAxis.setValueFormatter(new MyAxisValueFormatter(theDates));

                    barChart.setData(theData);
                    barChart.setVisibleXRange(100, 26);
                    barChart.setTouchEnabled(true);
                    barChart.setPinchZoom(true);
                    barChart.enableScroll();
                    barChart.setDescription(null);
                    barChart.invalidate();*/
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

    public void addItemsOnSpinner2(View view) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
    }

    public void countFreq (View view, String text, String nGram)
    {
        int n = Integer.parseInt(nGram);
        text = text.toUpperCase();
        text = text.replaceAll("[^a-zA-Z]+", "");
        if(text.length() < n)
        {
            Toast.makeText(this.getContext(), "Text is too short for " + n + " frequency", Toast.LENGTH_SHORT).show();
            return;
        }
        if(n == 1) {
            if(barChart.getVisibility() == view.INVISIBLE)
            {
                barChart.setVisibility(view.VISIBLE);
            }
            if(vText.getVisibility() == view.VISIBLE)
            {
                vText.setVisibility(view.INVISIBLE);
            }
            letterFreq = new Float[26];
            for (int j = 0; j < letterFreq.length; j++) {
                letterFreq[j] = 0.0f;
            }
            for (int i = 0; i < text.length(); i++) {
                char letter = text.charAt(i);
                if (Character.isLetter(letter)) {
                    int letterPos = letter - 'A';
                    letterFreq[letterPos]++;
                } else {

                }
            }

            ArrayList<BarEntry> barEntries = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                barEntries.add(new BarEntry(i, letterFreq[i]));

            }
            BarDataSet barDataSet = new BarDataSet(barEntries, "Frequency");

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add((IBarDataSet) barDataSet);

            BarData theData = new BarData(dataSets);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setGranularity(0.1f);
            xAxis.setDrawGridLines(false);
            xAxis.setLabelCount(25);
            xAxis.setValueFormatter(new MyAxisValueFormatter(theDates));

            barChart.setData(theData);
            barChart.setVisibleXRange(100, 26);
            barChart.setTouchEnabled(true);
            barChart.setPinchZoom(true);
            barChart.enableScroll();
            barChart.setDescription(null);
            barChart.invalidate();

        }else if(n > 1)
        {
            barChart.setVisibility(View.INVISIBLE);
            vText.setVisibility(View.VISIBLE);

            ArrayList <String> map = new ArrayList <String> ();
            ArrayList <Integer> values = new ArrayList <Integer> ();
            for (int i = 0; i < text.length(); i++)
            {
                if(i+n <= text.length())
                {
                    String iTemp = text.substring(i,i+n);
                    //    System.out.println(iTemp);
                    if(!map.contains(iTemp))
                    {
                        map.add(iTemp);
                        values.add(0);
                    }
                }
            }

            for (int x = 0; x < text.length(); x++)
            {
                if(x+n <= text.length())
                {
                    String iTemp = text.substring(x,x+n);
                    for (int j = 0; j < map.size(); j++)
                    {
                        if(iTemp.equalsIgnoreCase(map.get(j)))
                        {
                            int v = values.get(j)+1;
                            values.set(j, v);
                        }
                    }
                }
            }

            for (int i = 0 ; i < values.size(); i ++)
            {
                for (int j = 0 ; j < values.size(); j ++)
                {
                    if(values.get(i) > values.get(j))
                    {
                        int vTemp = values.get(i);
                        values.set(i,values.get(j));
                        values.set(j, vTemp);

                        String sTemp = map.get(i);
                        map.set(i, map.get(j));
                        map.set(j, sTemp);
                    }
                }
            }
            String result = "";
            double size = Math.round((double)map.size()/2);

            for(int y = 0; y < size; y ++)
            {
                result += (map.get(y) + ": " + values.get(y) + "\n");
            }

            vText.setText(result);
        }
    }

    public void updateText (String text) {
        globalText = text;
    }
}
