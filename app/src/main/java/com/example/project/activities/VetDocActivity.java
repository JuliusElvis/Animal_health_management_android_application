package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class VetDocActivity extends AppCompatActivity {
    Button docReg, docDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_doc);

        docDisplay = findViewById(R.id.docDisplay);
        docReg = findViewById(R.id.docReg);

        docDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        docReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocRegActivity();
            }
        });

    }
    public void openActivity(){
        Intent intent = new Intent(this,DocDisplayActivity.class);
        startActivity(intent);
    }
    public void openDocRegActivity(){
        Intent intent = new Intent(this,vetRegInput.class);
        startActivity(intent);
    }

}
