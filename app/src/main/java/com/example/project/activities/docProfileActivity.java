package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project.R;

public class docProfileActivity extends AppCompatActivity {
    TextView nametxtView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        nametxtView = findViewById(R.id.nametextView);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");

        }
        nametxtView.setText(name);
    }
}