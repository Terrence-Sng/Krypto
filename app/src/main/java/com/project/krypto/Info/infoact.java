package com.project.krypto.Info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.project.krypto.R;

/**
 * Created by Panda on 8/10/2017.
 */

public class infoact extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = 0;

        try {
            id = Integer.parseInt(getIntent().getStringExtra("INFOID"));
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

       setView(id);

        setToolbar(id);


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

    public void setView (int id)
    {
        switch(id)
        {
            case 0:
                setContentView(R.layout.homeinfo);//home
                break;
            case 1:
                setContentView(R.layout.freqinfo);//ngram
                break;
            case 2://ioc
                setContentView(R.layout.iocinfo);
                break;
            case 3://period
                setContentView(R.layout.periodinfo);
                break;
            case 4:
                setContentView(R.layout.subinfo);
                break;
            case 5://vig
                setContentView(R.layout.viginfo);
                break;
            case 6://transpo
                setContentView(R.layout.transpoinfo);
                break;
        }
    }

    public void setToolbar (int id)
    {
        switch(id)
        {
            case 0:   toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle("About");
                toolbar.setNavigationIcon(R.drawable.ic_back);
                toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
                setSupportActionBar(toolbar);

                setStatusBar();
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                });
                break;
            default: toolbar = (Toolbar) findViewById(R.id.toolbarinfo);
                toolbar.setTitle("About");
                toolbar.setNavigationIcon(R.drawable.ic_back);
                toolbar.setBackgroundColor(getResources().getColor(R.color.Black));
                setSupportActionBar(toolbar);

                setStatusBar();
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   Toast.makeText(getApplicationContext(), "Back Pressed", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                });
                break;
        }
    }

}
