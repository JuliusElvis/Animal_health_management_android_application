package com.example.project.resetPass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.diseaseDisplay;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class EmailPassActivity extends AppCompatActivity {
    TextInputEditText email1;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_pass);

        email1 = findViewById(R.id.vrfemail);
        bt1 = findViewById(R.id.buttonVrfEmail);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email;
                email = String.valueOf(email1.getText()).trim();

                if (!email.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[1];
                            field[0] = "email";

                            String[] data = new String[1];
                            data[0] = email;

                            PutData putData = new PutData("http://192.168.100.3/project1/resetpass.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    openActivity();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openActivity(){
        Intent intent = new Intent(this, CodeActivity.class);
        startActivity(intent);
    }
}