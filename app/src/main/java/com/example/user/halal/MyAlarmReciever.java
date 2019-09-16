package com.example.user.halal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MyAlarmReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "No."+(intent.getIntExtra("id",0) + 1) +" alarm started!!", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = context.getSharedPreferences("alarm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(intent.getIntExtra("id", 0) + "_status", false);
        editor.apply();

        Intent offIntent = new Intent(context, TurnOffActivity.class);
        offIntent.putExtra("id", intent.getIntExtra("id", 0));
        offIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(offIntent);
    }
}