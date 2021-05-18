package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;

public class diseaseDisplay extends AppCompatActivity {

    TextView disease,doc;
    String disease1,username;
    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_display);

        disease = findViewById(R.id.dis);
        disease1 = getIntent().getExtras().getString("disease");
        username = getIntent().getExtras().getString("username");
        //imageView = findViewById(R.id.image);
        //imageView.setImageResource(R.drawable.cows);
        disease.setText(disease1);

    }

    /*public void openDoc(){
        Intent intent = new Intent(this, DocDisplayActivity.class);
        startActivity(intent);
    }*/

    public void onBackPressed(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("username",username);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}