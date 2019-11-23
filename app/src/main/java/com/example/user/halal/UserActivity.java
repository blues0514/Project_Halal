package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nameTextView;
    private TextView emailTextView;
    private ImageView imageView;
    private Button logout_button;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth = FirebaseAuth.getInstance();
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        imageView = findViewById(R.id.imageView);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(100);
                    }
                    catch(InterruptedException e)
                    {

                    }
                    if(getApplicationContext() == null) return;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                nameTextView.setText(auth.getCurrentUser().getDisplayName());
                                emailTextView.setText(auth.getCurrentUser().getEmail());
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            startActivity(new Intent(this, MainActivity.class));
            Log.v("Nav","클릭 확인");
        }
        else if(id==R.id.nav_map){
            startActivity(new Intent(this, MapsActivity.class));
        }
        else if(id == R.id.nav_pray){
            startActivity(new Intent(this, AlarmActivity.class));
        }
        else if (id == R.id.nav_mosque){
            startActivity(new Intent(this, CompassFragment.class));
        } else if (id == R.id.nav_barcode) {
            startActivity(new Intent(this, ScannerActivity.class));
        } else if (id == R.id.nav_logout) {
            auth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                Intent intent= new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                return false;
        }
        return false;
    }
}
