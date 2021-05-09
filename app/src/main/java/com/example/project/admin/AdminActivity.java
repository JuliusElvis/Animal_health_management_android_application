package com.example.project.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.activities.MainActivity;

public class AdminActivity extends AppCompatActivity {

    TextView deleteRec;
    TextView addRec,vemails,changePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        deleteRec = findViewById(R.id.deleterecord);
        addRec = findViewById(R.id.addDoc);
        vemails = findViewById(R.id.emails);
        changePass = findViewById(R.id.changePass);

        addRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVet();
            }
        });

        deleteRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord();
            }
        });

        vemails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMail();
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass();
            }
        });



    }

    public void deleteRecord(){
        Intent intent = new Intent(this, DeleteRecord.class);
        startActivity(intent);
    }
    public void AddVet(){
        Intent intent = new Intent(this, AddRecord.class);
        startActivity(intent);
    }

    public void openMail(){
        Intent intent = new Intent(this, mailActivity.class);
        startActivity(intent);
    }
    public void changePass(){
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }
}