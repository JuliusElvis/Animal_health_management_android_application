package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.admin.AdminActivity;
import com.example.project.resetPass.EmailPassActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    //EditText ;
    TextInputEditText etUsername,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = findViewById(R.id.passwordEt);
        etUsername = findViewById(R.id.usernameEt);

        loginBtn = findViewById(R.id.loginButton);
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);

        text1.setPaintFlags(text1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        text2.setPaintFlags(text2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password, username;

                username = String.valueOf(etUsername.getText()).trim();
                password = String.valueOf(etPassword.getText()).trim();

                if (!username.equals("") && !password.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.100.3/project1/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        openNewActivity();
                                        finish();
                                    } else if (username.equals("admin")){
                                        checkAdmin();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Log in failed", Toast.LENGTH_SHORT).show();
                                    }

                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPasswordActivity();
            }
        });
    }
    int counter = 0;

    @Override
    public void onBackPressed() {
        counter++;
        if (counter == 2)
        super.onBackPressed();

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("username",String.valueOf(etUsername.getText()));
        startActivity(intent);
    }
    public void openRegisterActivity(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void openPasswordActivity(){
        Intent intent = new Intent(this, EmailPassActivity.class);
        startActivity(intent);
    }
    public void openAdminActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public void checkAdmin() {
        final String password, username;

        username = String.valueOf(etUsername.getText());
        password = String.valueOf(etPassword.getText());

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "password";
                //Creating array for data
                String[] data = new String[2];
                data[0] = username;
                data[1] = password;

                PutData putData = new PutData("http://192.168.100.3/project1/adminLogin.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Admin Login Success")) {
                            openAdminActivity();
                            finish();
                        }
                    }
                }
            }
        });
    }
}
