package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;

public class HomeActivity extends AppCompatActivity {
    CardView animalCalendar,vetReg,healthCheck,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        animalCalendar = findViewById(R.id.animalCalendar);
        vetReg = findViewById(R.id.vetReg);
        healthCheck = findViewById(R.id.healthCheck);
        help = findViewById(R.id.help);


        animalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarActivity();
            }
        });

        vetReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        healthCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openHealthActivity();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void openActivity(){
        Intent intent = new Intent(this, VetDocActivity.class);
        startActivity(intent);
    }
    public void openHealthActivity(){
        Intent intent = new Intent(this, HealthActivity.class);
        startActivity(intent);
    }
    public void openHelpActivity(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    public void openCalendarActivity(){
        Intent intent = new Intent(this, calendarActivity.class);
        startActivity(intent);
    }
}
