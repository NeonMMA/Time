package com.company.firstedition;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

public class HeartTraining extends AppCompatActivity {
    TextView text;
    Button backbutton, start_stop,clear;
    DrawView drawView;

    private CountDownTimer countDownTimer;
    private boolean TimerRunning;

    long START_TIME = 60;
    private long TimeLeft=START_TIME;

    int DIALOG_TIME = 1;
    int myHour = 0;
    int myMinute = 0;


    public void onclick(View view) {
        showDialog(DIALOG_TIME);
    }

    OnTimeSetListener myCallBack = new OnTimeSetListener() {
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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_training);
        text = findViewById(R.id.setText);
        backbutton = findViewById(R.id.back);
        start_stop = findViewById(R.id.Start_Stop);
        clear = findViewById(R.id.clear);
        drawView = findViewById(R.id.draw);


        Display display = getWindowManager().getDefaultDisplay();

        drawView.WIDTH = display.getWidth()/2;
        drawView.HEIGHT = display.getHeight()/2;

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

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HeartTraining.this, first.class));

            }
        });


    }
    private void startTimer () {
        countDownTimer = new CountDownTimer(TimeLeft*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished/1000;
                drawView.degree = (((millisUntilFinished / 1000) * 360) / START_TIME);
                drawView.invalidate();
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
        drawView.invalidate();
    }
    private void resetTimer () {
        TimeLeft = START_TIME;
        updateCountDownTimer();
        clear.setVisibility(View.INVISIBLE);
        start_stop.setVisibility(View.VISIBLE);
        drawView.setDegree(0);
        drawView.invalidate();
    }
    private void updateCountDownTimer() {
        int min = (int) TimeLeft/60;
        int sec = (int) TimeLeft%60;
        if (sec>=10) drawView.setTimerText(min+":"+sec); else drawView.setTimerText(min+":0"+sec);
        drawView.invalidate();
    }

}
