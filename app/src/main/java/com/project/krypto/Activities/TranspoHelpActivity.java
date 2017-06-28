package com.project.krypto.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.project.krypto.Fragments.transpo.TranspoFragment;
import com.project.krypto.R;

import java.util.ArrayList;

/**
 * Created by Panda on 6/21/2017.
 */


public class TranspoHelpActivity extends AppCompatActivity implements TranspoFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ArrayList<Fragment> list = new ArrayList<Fragment>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transpo_help_main);
        toolbar = (Toolbar) findViewById(R.id.toolbarTranspo);
        toolbar.setTitle("Transposition Examples");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initList();

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        viewPager.setAdapter(new HelpPagerAdapter(
                getSupportFragmentManager()));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

    }

    public void initList()
    {
        TranspoFragment frag1 = new TranspoFragment().newInstance("1", "hellow 2nd String");
        list.add(frag1);
        list.add(new TranspoFragment());
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class HelpPagerAdapter extends FragmentPagerAdapter
        {
            public HelpPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                    if(position == 0)
                    {
                        return list.get(0);
                    }
                    else
                    {
                        return list.get(1);
                    }
            }

            @Override
            public int getCount() {
                return 2;
            }
        }
}
