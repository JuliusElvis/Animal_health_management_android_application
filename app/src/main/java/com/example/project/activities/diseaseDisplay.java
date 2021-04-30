package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project.R;

public class diseaseDisplay extends AppCompatActivity {

    TextView disease;
    String disease1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_display);

        disease = findViewById(R.id.dis);
        disease1 = getIntent().getExtras().getString("disease");
        disease.setText(disease1);



    }
}