package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment{

    private BottomNavigationView bottomNavigationView;

    TextView textView;
    Handler mHandler = null;
    ImageView imageView_maps;
    ImageView imageView_compass;
    ImageView imageView_alarm;
    ImageView imageView_receipe;
    ImageView imageView_barcode;

    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        textView = view.findViewById(R.id.TimeText);

        imageView_maps = (ImageView) view.findViewById(R.id.halal_map);
        imageView_compass = (ImageView) view.findViewById(R.id.halal_mosque);
        imageView_alarm = (ImageView) view.findViewById(R.id.halal_pray);
        imageView_receipe = (ImageView) view.findViewById(R.id.halal_recipe);
        imageView_barcode = (ImageView) view.findViewById(R.id.halal_barcode);

        mainActivity = new MainActivity();


        imageView_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });

        imageView_compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CompassFragment()).commit();

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

        imageView_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ScannerActivity.class));
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

        return view;
    }

    private String getCurrentTime() {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("Asia/Riyadh"));
        String str = String.format( "%s: %02d:%02d", "Time",gc.get( Calendar.HOUR_OF_DAY ), gc.get( Calendar.MINUTE ));
        return str;
    }

}