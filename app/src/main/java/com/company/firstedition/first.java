package com.company.firstedition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class first extends AppCompatActivity {
    Button Start,info;
    String s;
    TextView DayOff;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Start = findViewById(R.id.Start);
        DayOff = findViewById(R.id.DayOff);

        DayOff.setText("Дней без тренировок: мноооооооого");

    }
    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.Round:
                if (checked){
                    s="Round";
                    DayOff.setText(s);
                }
                break;
            case R.id.HeartTraining:
                if (checked){
                    s="NOT Round";
                    DayOff.setText(s);
                }
                break;
        }
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == "NOT Round") {Intent intent = new Intent(first.this, HeartTraining.class);startActivity(intent);} else
                    {Intent intent = new Intent(first.this, Round.class);startActivity(intent);}
            }
        });

    }

    public void onclick(View view) {
        Intent infIntent = new Intent(first.this,Settings.class);
        startActivity(infIntent);
    }
}
