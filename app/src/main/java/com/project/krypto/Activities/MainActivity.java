package com.project.krypto.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.krypto.Help.vigHelp;
import com.project.krypto.act_tools.Home.HomeFrag;
import com.project.krypto.act_tools.Sub.SubCipher2;
import com.project.krypto.act_tools.Sub.subCipher;
import com.project.krypto.act_tools.ioc.ioc;
import com.project.krypto.act_tools.nGram.nGramCounter;
import com.project.krypto.act_tools.period.period;
import com.project.krypto.act_tools.transpo.transpo;
import com.project.krypto.act_tools.vingere.vigenere;
import com.project.krypto.Game.prologue;
import com.project.krypto.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import com.example.panda.krypto.Interfaces.fragmentInterfaces;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFrag.OnFragmentInteractionListener,
        subCipher.OnFragmentInteractionListener
        {//, fragmentInterfaces {

    private Toolbar toolbar;
    private ExpandableListView expandableList;
    private nGramCounter nfrag;
    private HomeFrag homeFrag;
    private ioc iocFrag;
    private period periodFrag;
    private vigenere vigFrag;
    private subCipher subFrag;
    private SubCipher2 subFrag2;
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
        checkExternalStorage();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //expandableList = (ExpandableListView) findViewById(R.id.navsubmenu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
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

        Menu menu = navigationView.getMenu();

        MenuItem menuitem = menu.findItem(R.id.statistic);
        SpannableString s = new SpannableString(menuitem.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.menuTitle), 0, s.length(),0);
        menuitem.setTitle(s);
        menuitem = menu.findItem(R.id.ciphers);
        s = new SpannableString(menuitem.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.menuTitle), 0, s.length(),0);
        menuitem.setTitle(s);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        username = (TextView) headerView.findViewById(R.id.userName);
        username.setTextColor(getResources().getColor(R.color.Dark_Green));
        textEmail = (TextView) headerView.findViewById(R.id.userEmail);
        textEmail.setTextColor(getResources().getColor(R.color.Dark_Green));
       // initUserSession();
    }

    public void showHomeFrag() {
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
Intent intent;
        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.action_settings :
            break;
            case R.id.transpoHelp :
                intent = new Intent(this, transposliderhelp.class);
               startActivity(intent);
            break;
            case R.id.subCipher2:
                intent = new Intent(this, SubCipher2.class);
                startActivity(intent);
                break;
            case R.id.vigHelp:
                intent = new Intent (this, vigHelp.class);
                startActivity(intent);
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
            fragment = homeFrag;
            toolbar.setTitle("Home");
        }
        else if(id == R.id.game)
        {
            Intent i = new Intent (getApplicationContext(), prologue.class);
            startActivity(i);
        }
        //else if (id == R.id.nav_frequency) {
        // fragment = nfrag;
        // toolbar.setTitle("Frequency");
        //} else if (id == R.id.nav_ioc) {
        // fragment = iocFrag;
        // toolbar.setTitle("Index of Coincidence");
        // } else if (id == R.id.nav_period) {
        //fragment = periodFrag;
        //  toolbar.setTitle("Period");
        // } else if (id == R.id.nav_vigenere) {
        //fragment = vigFrag;
        //    toolbar.setTitle("Vigenere Cipher");
        if(fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onDestory()
    {
        super.onDestroy();
    }

    public void initFrags()
    {
        homeFrag = new HomeFrag();
        listFrags.add(homeFrag);
     //   nfrag = new nGramCounter();
     //   listFrags.add(nfrag);
        //iocFrag = new ioc();
        //listFrags.add(iocFrag);
       // periodFrag = new period();
       // listFrags.add(periodFrag);
        subFrag = new subCipher();
        listFrags.add(subFrag);
       // vigFrag = new vigenere();
       // listFrags.add(vigFrag);
        //transpoFrag = new transpo();
       // listFrags.add(transpoFrag);
        subFrag2 = new SubCipher2();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void sendText (String text) {
        Log.d("Log", "hello " + text);
       // nfrag.updateText(text);
       // iocFrag.updateText(text);
        periodFrag.updateText(text);
        subFrag.updateText(text);
    }

    public void checkExternalStorage()
    {
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
            Log.d("Log" , "External Storage Available");
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
            Log.d("Log" , "External Storage Available cannot write");
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
            Log.d("Log" , "External Storage not Available");
        }

        if(mExternalStorageAvailable == true && mExternalStorageWriteable == true) {
            try {
                File newFile = new File(Environment.getExternalStorageDirectory(), "Cipher");
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                File notes = new File(newFile, "cipher1");
                FileWriter fw = new FileWriter(notes);
                fw.append("hello");
                fw.flush();
                fw.close();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e)
            {
                Log.d("Saving issues", "cannot save");
        }
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
