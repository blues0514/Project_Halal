package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class drawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    Handler mHandler = null;
    ImageView imageView_maps;
    ImageView imageView_compass;
    ImageView imageView_alarm;
    ImageView imageView_receipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindrawer);
        textView = findViewById(R.id.TimeText);



        imageView_maps = (ImageView) findViewById(R.id.imageView1);
        imageView_compass = (ImageView) findViewById(R.id.imageView2);
        imageView_alarm = (ImageView) findViewById(R.id.imageView3);
        imageView_receipe = (ImageView) findViewById(R.id.imageView4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imageView_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        imageView_compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CompassActivity.class));
            }
        });

        imageView_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AlarmActivity.class));
            }
        });
        imageView_receipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReceipeActivity.class));
            }
        });

        mHandler = new Handler();
        textView.setText(getCurrentTime());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {

                    }
                    if(getApplicationContext() == null) return;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                textView.setText(getCurrentTime());

                            }
                            catch(Exception e){

                            }
                        }
                    });
                }}
        });
        t.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private String getCurrentTime() {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("Asia/Riyadh"));
        String str = String.format( "%s: %02d:%02d", "Time",gc.get( Calendar.HOUR_OF_DAY ), gc.get( Calendar.MINUTE ));
//        long time = System.currentTimeMillis();
//        SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm");
//        final String str = dayTime.format(new Date(time));
        return str;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.submenu, menu);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
