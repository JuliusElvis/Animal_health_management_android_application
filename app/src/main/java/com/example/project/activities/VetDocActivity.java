package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class VetDocActivity extends AppCompatActivity {
    Button docReg, docDisplay;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_doc);

        docDisplay = findViewById(R.id.docDisplay);
        docReg = findViewById(R.id.docReg);
        username = getIntent().getExtras().getString("username");

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
        intent.putExtra("username",username);
        startActivity(intent);
    }
    public void openDocRegActivity(){
        Intent intent = new Intent(this,vetRegInput.class);
        startActivity(intent);
    }

}
