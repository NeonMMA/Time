package com.company.firstedition;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class Round extends AppCompatActivity {

    TextView text;
    Button backbutton, start_stop,clear;
    RoundDrawView RdrawView;

    private CountDownTimer countDownTimer;
    private boolean TimerRunning;

    long START_TIME = 60;
    private long TimeLeft=START_TIME;

    int DIALOG_TIME = 1;
    int myHour = 0;
    int myMinute = 0;


  /*  public void onclick(View view) {
        showDialog(DIALOG_TIME);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            START_TIME = (myHour * 60 * 60 + myMinute * 60);
            resetTimer();
        }
    };

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return timePickerDialog;
        }
        return super.onCreateDialog(id);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        backbutton = findViewById(R.id.back2);
        start_stop = findViewById(R.id.Start_Stop2);
        clear = findViewById(R.id.clear2);
        text= findViewById(R.id.countRound);
        RdrawView = findViewById(R.id.Rdraw);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Round.this,first.class);
                startActivity(intent);
            }
        });




        start_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownTimer();
    }

    private void startTimer () {
        countDownTimer = new CountDownTimer(TimeLeft*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished/1000;
                RdrawView.degree = (((millisUntilFinished / 1000) * 360) / START_TIME);
                RdrawView.invalidate();
                updateCountDownTimer();
            }

            @Override
            public void onFinish() {
                TimerRunning =false;
                start_stop.setText("Start");
                start_stop.setVisibility(View.INVISIBLE);
                clear.setVisibility(View.VISIBLE);
            }
        }.start();

        TimerRunning=true;
        start_stop.setText("stop");
        clear.setVisibility(View.INVISIBLE);

    }
    private void pauseTimer () {
        countDownTimer.cancel();
        TimerRunning = false;
        start_stop.setText("Start");
        clear.setVisibility(View.VISIBLE);
        RdrawView.invalidate();
    }
    private void resetTimer () {
        TimeLeft = START_TIME;
        updateCountDownTimer();
        clear.setVisibility(View.INVISIBLE);
        start_stop.setVisibility(View.VISIBLE);
        RdrawView.setDegree(0);
        RdrawView.invalidate();
    }
    private void updateCountDownTimer() {
        int min = (int) TimeLeft/60;
        int sec = (int) TimeLeft%60;
        if (sec>=10) RdrawView.setTextofTimer1(min+":"+sec); else RdrawView.setTextofTimer1(min+":0"+sec);
        RdrawView.invalidate();
    }
}
