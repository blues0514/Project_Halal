package com.example.user.halal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm {
    private int id;
    private Calendar date;

    public Alarm(int id) {
        this.id = id;
    }

    public Alarm(int id, Calendar date) {
        this(id);
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setAlarm(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("alarm", Context.MODE_PRIVATE); //
        if (sharedPreferences.getBoolean(id + "_status", false)) { //

            Toast.makeText(context,"alarm No."+ (id + 1) + " is already set.", Toast.LENGTH_SHORT).show();

            return; //
        } //

        Intent intent = new Intent(context, MyAlarmReciever.class);
        intent.putExtra("id", id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), pendingIntent);

        SharedPreferences.Editor editor = sharedPreferences.edit(); //
        editor.putBoolean(id + "_status", true); //
        editor.apply(); //
    }

    public void cancelAlarm(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("alarm", Context.MODE_PRIVATE); //
        if (!sharedPreferences.getBoolean(id + "_status", false)) { //

            Toast.makeText(context, "alarm No."+(id + 1) + " is not set.", Toast.LENGTH_SHORT).show(); //
            return; //
        } //

        Intent intent = new Intent(context, MyAlarmReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

        SharedPreferences.Editor editor = sharedPreferences.edit(); //
        editor.putBoolean(id + "_status", false); //
        editor.apply(); //
    }
}

