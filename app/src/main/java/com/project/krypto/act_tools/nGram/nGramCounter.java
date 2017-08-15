package com.project.krypto.act_tools.nGram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.project.krypto.Game.GameActivity;
import com.project.krypto.Game.finallvl;
import com.project.krypto.Game.transpolvl;
import com.project.krypto.Game.viglvl;
import com.project.krypto.Help.imagehelp;
import com.project.krypto.Info.infoact;
import com.project.krypto.R;

import java.util.ArrayList;
import java.util.List;


public class nGramCounter extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private com.github.mikephil.charting.charts.BarChart barChart;
    private String[] theDatas = new String [] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Float [] letterFreq;
    private String FNAME = "hello_file";
    private String globalText = "";
    private static TextView eText;
    private static TextView vText;
    private static Button bCount,backToGame;
    private static Spinner spin;
    Toolbar toolbar;

    // TODO: Rename and change types of parameters
    private String cipherFromGame;
    private String level;
    private boolean fromGame;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ngram);
        toolbar = (Toolbar) findViewById(R.id.toolbarngram);
        toolbar.setTitle("Frequency");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });

        fromGame = getIntent().getBooleanExtra("GAME", false);
        level = getIntent().getStringExtra("LEVEL");
        cipherFromGame = getIntent().getStringExtra("GAMECIPHER");

        backToGame = (Button) findViewById(R.id.backButtonGameNGram);
        backToGame.setOnClickListener(new View.OnClickListener() {
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
                    case "3":
                        game = new Intent (getBaseContext(), transpolvl.class);
                        startActivity(game);
                        break;
                    case "4":
                        game = new Intent (getBaseContext(), finallvl.class);
                        startActivity(game);
                        break;
                }
            }
        });
        eText = (TextView) findViewById(R.id.EnterText);
        if(fromGame == false) {
            eText.setText(getIntent().getStringExtra("CIPHER"));
            backToGame.setVisibility(View.GONE);
        }
        else
        {
            eText.setText(cipherFromGame);
            backToGame.setVisibility(View.VISIBLE);
        }

        vText = (TextView) findViewById(R.id.showFrequency);
        vText.setVisibility(View.INVISIBLE);
        bCount = (Button)findViewById(R.id.button);
        spin = (Spinner) findViewById(R.id.spin);
        barChart = (com.github.mikephil.charting.charts.BarChart)findViewById(R.id.barChart);

        addItemsOnSpinner2();

        bCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = eText.getText().toString();//eText.getText().toString;
                //Log.d("Log", "hellos " + text);
                if (!text.isEmpty()) {
                    String value = spin.getSelectedItem().toString();
                    //Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
                    countFreq(text, value);
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Input Detected!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        }

    public void addItemsOnSpinner2() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.nGram, R.layout.spinner_text);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

    }


    public void countFreq ( String text, String nGram)
    {
        int n = Integer.parseInt(nGram);
        text = text.toUpperCase();
        text = text.replaceAll("[^a-zA-Z]+", "");
        if(text.length() < n)
        {
            Toast.makeText(getBaseContext(), "Text is too short for " + n + " frequency", Toast.LENGTH_SHORT).show();
            return;
        }
        if(n == 1) {
            if(barChart.getVisibility() == View.INVISIBLE)
            {
                barChart.setVisibility(View.VISIBLE);
            }
            if(vText.getVisibility() == View.VISIBLE)
            {
                vText.setVisibility(View.INVISIBLE);
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
            barDataSet.setColor(getResources().getColor(R.color.Neon_Green));
            barDataSet.setValueTextColor(getResources().getColor(R.color.Dark_Green));
            //barDataSet.setHighLightColor(getResources().getColor(R.color.Neon_Green));
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add((IBarDataSet) barDataSet);

            BarData theData = new BarData(dataSets);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setGranularity(0.1f);
            xAxis.setDrawGridLines(false);
            xAxis.setLabelCount(25);
            //xAxis.setTextColor(getResources().getColor(R.color.Dark_Green));
            xAxis.setValueFormatter(new MyAxisValueFormatter(theDatas));

            barChart.setData(theData);
            barChart.getLegend().setTextColor(getResources().getColor(R.color.Dark_Green));
            barChart.getXAxis().setTextColor(getResources().getColor(R.color.Dark_Green));
            barChart.getAxisLeft().setTextColor(getResources().getColor(R.color.Dark_Green));
            barChart.getAxisRight().setTextColor(getResources().getColor(R.color.Dark_Green));
            barChart.setVisibleXRange(100, 26);
            barChart.setScaleEnabled(true);
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
        getMenuInflater().inflate(R.menu.ngramhelp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ngramhelpex:
                Intent mHelp = new Intent (this, imagehelp.class);
                mHelp.putExtra("HELPID", "1");
                startActivity(mHelp);
                // do your sign-out stuff
                break;
            default:
                Intent mInfo = new Intent (this, infoact.class);
                mInfo.putExtra("INFOID", "1");
                startActivity(mInfo);
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }
}
