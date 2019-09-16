package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    TextView textView;
    Handler mHandler = null;
    ImageView imageView_maps;
    ImageView imageView_compass;
    ImageView imageView_alarm;
    ImageView imageView_receipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        textView = view.findViewById(R.id.TimeText);



        imageView_maps = (ImageView) view.findViewById(R.id.imageView1);
        imageView_compass = (ImageView) view.findViewById(R.id.imageView2);
        imageView_alarm = (ImageView) view.findViewById(R.id.imageView3);
        imageView_receipe = (ImageView) view.findViewById(R.id.imageView4);


        imageView_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });

        imageView_compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CompassActivity.class));
            }
        });

        imageView_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AlarmActivity.class));
            }
        });
        imageView_receipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReceipeActivity.class));
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
                    if(getActivity() == null) return;
                    getActivity().runOnUiThread(new Runnable() {
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

        // Inflate the layout for this fragment
        //textView.setText(getCurrentTime());
        // System.out.println();
        return view;
    }
    private String getCurrentTime() {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("Asia/Riyadh"));
        String str = String.format( "%s: %02d:%02d", "Time",gc.get( Calendar.HOUR_OF_DAY ), gc.get( Calendar.MINUTE ));
//        long time = System.currentTimeMillis();
//        SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm");
//        final String str = dayTime.format(new Date(time));
        return str;
    }

}