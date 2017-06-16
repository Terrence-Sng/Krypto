package com.project.krypto.Fragments.nGram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
 * Created by Panda on 5/4/2017.
 */

public class BarChart extends AppCompatActivity {

        private com.github.mikephil.charting.charts.BarChart barChart;
        private String[] theDates = new String [] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        private Float [] letterFreq;
        private String FNAME = "hello_file";
        public static Button butt;
        Spinner spin;
        String tempStr = "hello_world";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            final EditText textArea = (EditText) findViewById(R.id.EnterText);

            butt = (Button) findViewById(R.id.button);
            spin = (Spinner) findViewById(R.id.spin);
            addItemsOnSpinner2();
            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = textArea.getText().toString();
                    if(!text.isEmpty())
                    {
                        String value = spin.getSelectedItem().toString();
                        Toast.makeText(getBaseContext(),value,Toast.LENGTH_SHORT).show();
                        countFreq(text, value);
                        barChart = (com.github.mikephil.charting.charts.BarChart) findViewById(R.id.barChart);

                        ArrayList<BarEntry> barEntries = new ArrayList<>();
                        for(int i = 0; i < 26; i++) {
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
                        barChart.setVisibleXRange(100,26);
                        barChart.setTouchEnabled(true);
                        barChart.enableScroll();
                        barChart.setDescription(null);
                        barChart.invalidate();
                    }

                }
            });


        }

        public void countFreq (String text, String nGram)
        {
            int n = Integer.parseInt(nGram);
            text = text.toUpperCase();
            if(n == 1) {
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
            }else if(n > 1)
            {

            }
        }
        public void addItemsOnSpinner2() {
            List<String> list = new ArrayList<String>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(dataAdapter);
        }
    }
