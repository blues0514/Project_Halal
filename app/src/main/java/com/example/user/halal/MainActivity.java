package com.example.user.halal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_ITEM = "selected_item";

    DrawerLayout mDrawer;
    private BackPressCloseHandler backPressCloseHandler;
    FirebaseAuth mAuth;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    public MenuItem menuItemSelected;
    private int mMenuItemSelected;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


//    private CompassFragment compassFragment = new CompassFragment();
//    private MapsFragment mapsFragment = new MapsFragment();
//    private ScannerFragment scannerFragment = new ScannerFragment();
//    private AlarmFragment alarmFragment = new AlarmFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindrawer);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        backPressCloseHandler = new BackPressCloseHandler(this);
        mAuth = FirebaseAuth.getInstance();
        initLayout();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        if (savedInstanceState != null) {
            mMenuItemSelected = savedInstanceState.getInt(SELECTED_ITEM, 0);
            menuItemSelected = bottomNavigationView.getMenu().findItem(mMenuItemSelected);
        } else {
            menuItemSelected = bottomNavigationView.getMenu().getItem(0);
        }
        selectFragment(menuItemSelected);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mMenuItemSelected);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_barcode:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ScannerFragment())
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void selectFragment(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.navigation_compass:
                fragmentClass = CompassFragment.class;
                break;
            case R.id.navigation_alarm:
                fragmentClass = AlarmFragment.class;
                break;
            case R.id.navigation_user:
                fragmentClass = UserFragment.class;
                break;

            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                item.setChecked(true);
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_map:
                item.setChecked(true);
                selectedFragment = new MapsFragment();
                break;
            case R.id.nav_pray:
                item.setChecked(true);
                selectedFragment = new AlarmFragment();
                break;
            case R.id.nav_mosque:
                item.setChecked(true);
                selectedFragment = new CompassFragment();
                break;
            case R.id.nav_barcode:
                item.setChecked(true);
                selectedFragment = new ScannerFragment();
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void initLayout() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_homeView);
        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity mainactivity) {
            weakReference = new WeakReference<MainActivity>(mainactivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainactivity = weakReference.get();

            if (mainactivity != null) {
                switch (msg.what) {
                    case 101:
                        break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        int selectedItemId = bottomNavigationView.getSelectedItemId();

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else if(R.id.navigation_home != selectedItemId) {
            setHomeItem(MainActivity.this);
        } else {
            backPressCloseHandler.onBackPressed();
        }
    }

    public static void setHomeItem(Activity activity) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                activity.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submenu, menu);
        return true;
    }

}