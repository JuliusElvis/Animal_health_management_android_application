package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.EmailActivity;
import com.example.project.R;

public class HelpActivity extends AppCompatActivity {

    Button btn;
    TextView logout,rateus,help,passreset;
    String username;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        logout = findViewById(R.id.logout);
        rateus = findViewById(R.id.rateus);
        help = findViewById(R.id.help);
        passreset =findViewById(R.id.resetpass);
        username = getIntent().getExtras().getString("username");

        tv1 = findViewById(R.id.tvusername);
        tv1.setText(username);

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmailActivity();
            }
        });

        //logoutMethod();





    }
    public void openNewActivity(){
        Intent intent = new Intent(this, appRating.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logoutMethod(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logout();
            }
        });
    }
    public void openEmailActivity(){
        Intent intent = new Intent(this, EmailActivity.class);
        startActivity(intent);
    }
}
