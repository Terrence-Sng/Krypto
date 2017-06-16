package com.project.krypto.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.project.krypto.Fragments.Home.HomeFrag;
import com.project.krypto.Fragments.Sub.subCipher;
import com.project.krypto.Fragments.nGram.nGramCounter;
import com.project.krypto.Fragments.ioc.*;
import com.project.krypto.Fragments.period.*;
//import com.example.panda.krypto.Interfaces.fragmentInterfaces;
import com.project.krypto.Fragments.vingere.*;
import com.project.krypto.Fragments.transpo.*;
import com.project.krypto.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFrag.OnFragmentInteractionListener, nGramCounter.OnFragmentInteractionListener,
        ioc.OnFragmentInteractionListener, period.OnFragmentInteractionListener, vigenere.OnFragmentInteractionListener, subCipher.OnFragmentInteractionListener, transpo.OnFragmentInteractionListener{//, fragmentInterfaces {

    private Toolbar toolbar;
    private ExpandableListView expandableList;
    private nGramCounter nfrag;
    private HomeFrag homeFrag;
    private ioc iocFrag;
    private period periodFrag;
    private vigenere vigFrag;
    private subCipher subcipher;
    private transpo transpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Krypto");
        setSupportActionBar(toolbar);

        initFrags();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //expandableList = (ExpandableListView) findViewById(R.id.navsubmenu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            showHomeFrag();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showHomeFrag ()
    {
        Fragment fragment = homeFrag;
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        toolbar.setTitle("Home");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            fragment = new HomeFrag();
            toolbar.setTitle("Home");
        } else if (id == R.id.nav_frequency) {
            fragment = nfrag;
            toolbar.setTitle("Frequency");
        } else if (id == R.id.nav_ioc) {
            fragment = iocFrag;
            toolbar.setTitle("Index of Coincidence");
        } else if (id == R.id.nav_period) {
            fragment = periodFrag;
            toolbar.setTitle("Period");
        } else if (id == R.id.nav_vingere) {
            fragment = vigFrag;
        } else if (id == R.id.nav_sub) {
            fragment = subcipher;
        } else if (id == R.id.nav_transpo)
        {
            fragment = transpo;
        }

        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initFrags()
    {
        nfrag = new nGramCounter();
        homeFrag = new HomeFrag();
        iocFrag = new ioc();
        periodFrag = new period();
        vigFrag = new vigenere();
        subcipher = new subCipher();
        transpo = new transpo();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void sendText (String text) {
        Log.d("Log", "hello " + text);
        nfrag.updateText(text);
        iocFrag.updateText(text);
        periodFrag.updateText(text);
        subcipher.updateText(text);
    }

}
