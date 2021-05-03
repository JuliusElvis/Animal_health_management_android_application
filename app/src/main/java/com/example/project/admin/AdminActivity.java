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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        deleteRec = findViewById(R.id.deleterecord);

        deleteRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord();
            }
        });


    }

    public void deleteRecord(){
        Intent intent = new Intent(this, DeleteRecord.class);
        startActivity(intent);
    }
}