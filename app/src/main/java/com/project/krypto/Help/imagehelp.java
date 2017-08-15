package com.project.krypto.Help;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.project.krypto.R;

import Utils.ViewPagerAdapter;

/**
 * Created by z_x_9 on 25/7/2017.
 */

public class imagehelp extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagehelp);

        toolbar = (Toolbar) findViewById(R.id.toolbarimagehelp);
        toolbar.setTitle("Help");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        setStatusBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        });


        int toolhelp = 2; // 0= home, 1=freq ngram, 2=ioc, 3=period

        try{
            toolhelp = Integer.parseInt(getIntent().getStringExtra("HELPID"));
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
        }

        if (toolhelp == 0)
        {
            //for Home
            int[] images = new int[]{R.drawable.home1, R.drawable.home2, R.drawable.home3,R.drawable.home4,R.drawable.home5,R.drawable.home6,R.drawable.home7,R.drawable.home8,R.drawable.home9};
            String[] des = new String[] {
                    "Step 1:" + "\n" + "Enter desired text in the 'Enter text here' field ",
                    "Step 2:" + "\n" + "Select one function from the dropdown list ",
                    "If 'Analysis' is selected, tap on one of the analysis tool button to go to the respective tool",
                    "If 'Encrypt' is selected, choose the cipher that is needed and enter the keyword or block size(if transposition is selected)' ",
                    "If 'Decrypt' is selected, choose the cipher that is needed and enter the keyword or block size(if transposition is selected)' ",
                    "In the various ciphers, tap on 'How its done?' to view the progress. Tapping on the 'Share' button allows you to share the result through various messaging app. ",
                    "Tapping on the '?' opens up a help instruction, slide the image to view the steps. Tapping on the 'i' brings out information of the respective tools and ciphers.",
                    "To go to the game, tap on the 'Practice it!' in the menu bar",
                    "Use the various tools and cipher to solve the puzzle in the game"
            };
            viewPager = (ViewPager)findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(imagehelp.this,images,des);
            viewPager.setAdapter(adapter);
        }

        else if (toolhelp == 1)
        {
            //for ngram
            int[] images = new int[]{R.drawable.ngram2, R.drawable.ngram3,R.drawable.ngram4};
            String[] des = new String[] {
                    "Step 1:" + "\n" + "Select one value from the drop-down box in 'Choose nGram' ",
                    "Step 2:" + "\n" + "Tap on the 'COUNT!' button to calculate the nGram ",
                    "Step 3:" + "\n" + "Results will be displayed under 'Results "};

            viewPager = (ViewPager)findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(imagehelp.this,images,des);
            viewPager.setAdapter(adapter);
        }

        else if (toolhelp == 2)
        {
            //for ioc
            int[] images = new int[]{R.drawable.ioc1, R.drawable.ioc2};
            String[] des = new String[] {
                    "Step 1:" + "\n" + "Tap on 'Calculate' button to calculate the Index of Conicidence",
                    "Step 2:" + "\n" + "Results will be displayed under 'Index of Coincidence' "};
            viewPager = (ViewPager)findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(imagehelp.this,images,des);
            viewPager.setAdapter(adapter);
        }

        else if (toolhelp == 3)
        {
            //for period
            int[] images = new int[]{R.drawable.period1, R.drawable.period2, R.drawable.period3};
            String[] des = new String[] {
                    "Step 1:" + "\n" + "Enter desired Period number then tap on 'Calculate' ",
                    "Step 2:" + "\n" + "Tap on 'Calculate' button to calculate the period ",
                    "Step 3:" + "\n" + "Results will be displayed under 'Period Result' "};
            viewPager = (ViewPager)findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(imagehelp.this,images,des);
            viewPager.setAdapter(adapter);
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


}
