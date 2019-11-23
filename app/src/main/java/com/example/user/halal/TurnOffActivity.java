package com.example.user.halal;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TurnOffActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSubmit;
    TextView notice, timetext;

    int i;
    MediaPlayer mp;
    Intent intent;

    int  hour[][] = {{17, 11, 11, 11,11,11},{5,6,12,16,18,20}, {5, 6, 12, 16, 18, 20}};
    int  minute[][] = {{45,3,3,4, 5, 5}, {2, 15, 35, 10, 56, 9}, {1, 14, 35, 10, 57, 10}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off);

        buttonSubmit = findViewById(R.id.button_submit);


        timetext = findViewById(R.id.timetext);
        timetext.setTextColor(Color.WHITE);
        timetext.setTextSize(40);



        i =  getIntent().getIntExtra("id",0) + 1;
        if(i==1) {
            timetext.setText("Fajr Start!\n" + String.valueOf(hour[0][0])+" hour "+String.valueOf(minute[0][0])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        } else if(i==2) {
            timetext.setText("Sunrise Start!\n" + String.valueOf(hour[0][1])+" hour "+String.valueOf(minute[0][1])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        } else if(i==3) {
            timetext.setText("Duhr Start!\n" + String.valueOf(hour[0][2])+" hour "+String.valueOf(minute[0][2])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        }else if(i==4) {
            timetext.setText("Asr Start!\n" + String.valueOf(hour[0][3])+" hour "+String.valueOf(minute[0][3])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        }else if(i==5) {
            timetext.setText("Magrib Start!\n" + String.valueOf(hour[0][4])+" hour "+String.valueOf(minute[0][4])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        }else if(i==6) {
            timetext.setText("Isha Start!\n" + String.valueOf(hour[0][5])+" hour "+String.valueOf(minute[0][5])+" minutes ");
            mp = MediaPlayer.create(this,R.raw.sleep);
            mp.start();
        }/*else if(i==7) {
            mp = MediaPlayer.create(this,R.raw.kal);
            mp.start();
        }*/




        notice = findViewById(R.id.notice);
        notice.setText("No."+(getIntent().getIntExtra("id",0) + 1) + " alarm service!");
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                Toast.makeText(this, "alarm No."+(getIntent().getIntExtra("id",0) + 1)+ " is Off.", Toast.LENGTH_SHORT).show();
                mp.stop();
                mp.reset();
                mp.release();
                finish();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("onDestory() 실행", "서비스 파괴");

    }
}