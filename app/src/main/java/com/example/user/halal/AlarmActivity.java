package com.example.user.halal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener{
    TimePicker timePicker;
    Button buttonSetOne, buttonCancelOne, buttonSetTwo, buttonCancelTwo, buttonSetThree, buttonCancelThree,
            buttonSetfour, buttonCancelfour, buttonSetfive, buttonCancelfive, buttonSetsix, buttonCancelsix,
            buttonSetseven, buttonCancelseven;
    TextView alarmStatus, ntime, rtime;
    ImageView watch;

    Long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("dd") ;
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
    SimpleDateFormat sdf4 = new SimpleDateFormat("mm");


    int day = Integer.parseInt(sdf.format(date))-1;


    String nowtime = sdf2.format(date);

    int  hour[][] = {{17, 11, 11, 11,11,11},{5,6,12,16,18,20}, {5, 6, 12, 16, 18, 20}};
    int  minute[][] = {{45,3,3,4, 5, 5}, {2, 15, 35, 10, 56, 9}, {1, 14, 35, 10, 57, 10}};

    int  lasthour = Integer.parseInt(sdf3.format(date))- hour[0][0];
    int  lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][0];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // timePicker = findViewById(R.id.time_picker);

        buttonSetOne = findViewById(R.id.button_set_one);
        buttonCancelOne = findViewById(R.id.button_cancel_one);
        buttonSetTwo = findViewById(R.id.button_set_two);
        buttonCancelTwo = findViewById(R.id.button_cancel_two);
        buttonSetThree = findViewById(R.id.button_set_three);
        buttonCancelThree = findViewById(R.id.button_cancel_three);
        buttonSetfour = findViewById(R.id.button_set_four);
        buttonCancelfour = findViewById(R.id.button_cancel_four);
        buttonSetfive = findViewById(R.id.button_set_five);
        buttonCancelfive = findViewById(R.id.button_cancel_five);
        buttonSetsix = findViewById(R.id.button_set_six);
        buttonCancelsix = findViewById(R.id.button_cancel_six);
        buttonSetseven = findViewById(R.id.button_set_seven);
        buttonCancelseven = findViewById(R.id.button_cancel_seven);
        ntime = findViewById(R.id.nowtime);
        rtime = findViewById(R.id.rtime);
        watch = findViewById(R.id.myImageView);

        alarmStatus = findViewById(R.id.alarm_status);
        getAlarmStatus();

        buttonSetOne.setOnClickListener(this);
        buttonCancelOne.setOnClickListener(this);
        buttonSetTwo.setOnClickListener(this);
        buttonCancelTwo.setOnClickListener(this);
        buttonSetThree.setOnClickListener(this);
        buttonCancelThree.setOnClickListener(this);
        buttonSetfour.setOnClickListener(this);
        buttonCancelfour.setOnClickListener(this);
        buttonSetfive.setOnClickListener(this);
        buttonCancelfive.setOnClickListener(this);
        buttonSetsix.setOnClickListener(this);
        buttonCancelsix.setOnClickListener(this);
        buttonSetseven.setOnClickListener(this);
        buttonCancelseven.setOnClickListener(this);

        ntime.setTextSize(18);
        ntime.setText("Now Time\n"+nowtime);
        rtime.setTextSize(35);
        rtime.setText("All alarms are off.");



    }

    @Override
    protected void onResume() {
        super.onResume();
        getAlarmStatus();
    }
    @Override
    public void onClick(View v) {

        Calendar calendar = Calendar.getInstance(); //캘린더 객체를 현재 시간으로 생성
        switch (v.getId()) {
            case R.id.button_set_one:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][0]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][0]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmone = new Alarm(0,calendar);
                alarmone.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][0]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][0];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][0]) {
                    lasthour =  hour[0][0]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][0]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][0];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][0]) {
                    lastminute  = minute[0][0]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Fajr sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_one:
                cancelAlarm(new Alarm(0)); //취소할 알람 아이디는 0
                break;
            case R.id.button_set_two:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][1]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][1]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmtwo = new Alarm(1,calendar);
                alarmtwo.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][1]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][1];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][1]) {
                    lasthour =  hour[0][1]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][1]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][1];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][1]) {
                    lastminute  = minute[0][1]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Sunrise sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_two:
                cancelAlarm(new Alarm(1)); //취소할 알람 아이디는 1
                break;
            case R.id.button_set_three:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][2]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][2]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmthree = new Alarm(2,calendar);
                alarmthree.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][2]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][2];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][2]) {
                    lasthour =  hour[0][0]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][2]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][2];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][2]) {
                    lastminute  = minute[0][2]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Duhr sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_three:
                cancelAlarm(new Alarm(2)); //취소할 알람 아이디는 1
                break;
            case R.id.button_set_four:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][3]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][3]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmfour = new Alarm(3,calendar);
                alarmfour.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][3]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][3];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][3]) {
                    lasthour =  hour[0][3]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][3]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][3];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][3]) {
                    lastminute  = minute[0][3]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Asr sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_four:
                cancelAlarm(new Alarm(3)); //취소할 알람 아이디는 1
                break;
            case R.id.button_set_five:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][4]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][4]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmfive = new Alarm(4,calendar);
                alarmfive.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][4]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][4];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][4]) {
                    lasthour =  hour[0][4]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][4]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][4];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][4]) {
                    lastminute  = minute[0][4]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Magrib sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_five:
                cancelAlarm(new Alarm(4)); //취소할 알람 아이디는 1
                break;
            case R.id.button_set_six:
                calendar.set(Calendar.HOUR_OF_DAY, hour[0][5]); //TimePicker에서 설정한 시로 캘린더를 설정
                calendar.set(Calendar.MINUTE,minute[0][5]); //TimePicker에서 설정한 분으로 캘린더를 설정
                Alarm alarmsix = new Alarm(5,calendar);
                alarmsix.setAlarm(this);

                if(Integer.parseInt(sdf3.format(date))>=hour[0][5]) {
                    lasthour = Integer.parseInt(sdf3.format(date)) - hour[0][5];
                }else if(Integer.parseInt(sdf3.format(date))<hour[0][5]) {
                    lasthour =  hour[0][5]- Integer.parseInt(sdf3.format(date)) ;
                }

                if (  Integer.parseInt(sdf4.format(date))>=minute[0][5]) {
                    lastminute  = Integer.parseInt(sdf4.format(date))- minute[0][5];
                } else if ( Integer.parseInt(sdf4.format(date))< minute[0][5]) {
                    lastminute  = minute[0][5]-Integer.parseInt(sdf4.format(date));
                }
                rtime.setText("Isha sounds after\n" + String.valueOf(lasthour) +" hour "+String.valueOf(lastminute)+" minutes ");
                break;
            case R.id.button_cancel_six:
                cancelAlarm(new Alarm(5)); //취소할 알람 아이디는 1
                break;
            case R.id.button_set_seven:
                setAlarm(new Alarm(6)); //설정할 알람 아이디는 1
                break;
            case R.id.button_cancel_seven:
                cancelAlarm(new Alarm(6)); //취소할 알람 아이디는 1
                break;
        }

        getAlarmStatus();
    }

    public void setAlarm(Alarm alarm) {
        Calendar calendar = Calendar.getInstance(); //캘린더 객체를 현재 시간으로 생성
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour()); //TimePicker에서 설정한 시로 캘린더를 설정
        calendar.set(Calendar.MINUTE, timePicker.getMinute()); //TimePicker에서 설정한 분으로 캘린더를 설정
        calendar.set(Calendar.SECOND, 0); //캘린더 초를 0으로 설정

        alarm.setDate(calendar); //알람 객체의 날짜를 위에서 바꿔준 캘린더 객체로 설정
        alarm.setAlarm(this); //알람 객체의 setAlarm 메소드 실행
    }

    public void cancelAlarm(Alarm alarm) {
        alarm.cancelAlarm(this); //알람 객체의 cancelAlarm 메소드 실행
    }

    public void getAlarmStatus() {
        //화면 아래에 알람이 설정 됐는지 안 됐는지 출력해주기 위한 메소드
        SharedPreferences sharedPreferences = getSharedPreferences("alarm", MODE_PRIVATE);

        StringBuilder builder = new StringBuilder();
       /* builder.append("No.1 alarm status : ")
                .append(sharedPreferences.getBoolean("0_status", false) + "\n")
                .append("No.2 alarm status : ")
                .append(sharedPreferences.getBoolean("1_status", false) + "\n")
                .append("No.3 alarm status : ")
                .append(sharedPreferences.getBoolean("2_status", false) + "\n")
                .append("No.4 alarm status : ")
                .append(sharedPreferences.getBoolean("3_status", false) + "\n")
                .append("No.5 alarm status : ")
                .append(sharedPreferences.getBoolean("4_status", false) + "\n")
                .append("No.6 alarm status : ")
                .append(sharedPreferences.getBoolean("5_status", false) + "\n")
                .append("No.7 alarm status : ")
                .append(sharedPreferences.getBoolean("6_status", false) + "\n");*/


        alarmStatus.setText(builder.toString());
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
