package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project.R;

public class diseaseDisplay extends AppCompatActivity {

    TextView disease,doc;
    String disease1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_display);

        disease = findViewById(R.id.dis);
        disease1 = getIntent().getExtras().getString("disease");
        disease.setText(disease1);

    }

    /*public void openDoc(){
        Intent intent = new Intent(this, DocDisplayActivity.class);
        startActivity(intent);
    }*/
}