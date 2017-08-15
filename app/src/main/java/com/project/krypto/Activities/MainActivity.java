package com.project.krypto.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.project.krypto.Game.prologue;
import com.project.krypto.Help.imagehelp;
import com.project.krypto.Info.infoact;
import com.project.krypto.R;
import com.project.krypto.act_tools.Home.HomeFrag;
import com.project.krypto.act_tools.ioc.ioc;
import com.project.krypto.act_tools.nGram.nGramCounter;
import com.project.krypto.act_tools.period.period;
import com.project.krypto.act_tools.transpo.transpo;
import com.project.krypto.act_tools.vingere.vigenere;

import java.util.ArrayList;

//import com.example.panda.krypto.Interfaces.fragmentInterfaces;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFrag.OnFragmentInteractionListener
        {

    private Toolbar toolbar;
    private ExpandableListView expandableList;
    private nGramCounter nfrag;
    private HomeFrag homeFrag;
    private ioc iocFrag;
    private period periodFrag;
    private vigenere vigFrag;
    private transpo transpoFrag;
    private TextView username, textEmail;
    private ArrayList <Fragment> listFrags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Krypto");
        setSupportActionBar(toolbar);

        setStatusBar();

        initFrags();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //expandableList = (ExpandableListView) findViewById(R.id.navsubmenu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            showHomeFrag();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        username = (TextView) headerView.findViewById(R.id.userName);
        username.setTextColor(getResources().getColor(R.color.Dark_Green));
        textEmail = (TextView) headerView.findViewById(R.id.userEmail);
        textEmail.setTextColor(getResources().getColor(R.color.Dark_Green));

    }

    public void showHomeFrag() {
        Fragment fragment = homeFrag;
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = homeFrag;
        }
        else if(id == R.id.game)
        {
            Intent i = new Intent (getApplicationContext(), prologue.class);
            startActivity(i);
        }

        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void initFrags()
    {
        homeFrag = new HomeFrag();
        listFrags.add(homeFrag);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void sendText (String text) {
        Log.d("Log", "hello " + text);
       // nfrag.updateText(text);
       // iocFrag.updateText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homehelpex:
                // do your sign-out stuff
                Intent mHelp = new Intent (this, imagehelp.class);
                mHelp.putExtra("HELPID", "0");
                startActivity(mHelp);
                break;
            default:
                Intent mInfo = new Intent (this,infoact.class);
                mInfo.putExtra("INFOID", "0");
                startActivity(mInfo);
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
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
