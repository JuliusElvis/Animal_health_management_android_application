package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterActivity extends AppCompatActivity {
    Button btnReg;
    TextInputEditText etFullname,etUsername,etEmail,etPassword,etConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.BtnReg);
        etFullname = findViewById(R.id.fullname);
        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPass = findViewById(R.id.Confirmpassword);

        TextView text3 = findViewById(R.id.text3);
        text3.setPaintFlags(text3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname, username, password, email, confPass;

                fullname = String.valueOf(etFullname.getText()).trim();
                username = String.valueOf(etUsername.getText()).trim();
                password = String.valueOf(etPassword.getText()).trim();
                email = String.valueOf(etEmail.getText()).trim();
                confPass = String.valueOf(etConfirmPass.getText()).trim();

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    if (password.equals(confPass)) {
                        //Start ProgressBar first (Set visibility VISIBLE)
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[4];
                                field[0] = "email";
                                field[1] = "fullname";
                                field[2] = "username";
                                field[3] = "password";
                                //Creating array for data
                                String[] data = new String[4];
                                data[0] = email;
                                data[1] = fullname;
                                data[2] = username;
                                data[3] = password;

                                PutData putData = new PutData("http://192.168.100.3/project1/signup.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign Up Success")){
                                            openLoginActivity();
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Sign up failed",Toast.LENGTH_SHORT).show();
                                        }

                                        //End ProgressBar (Set visibility to GONE)
                                        Log.i("PutData", result);
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });

                    }else {
                        Toast.makeText(getApplicationContext(),"Passwords entered donot match",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });


    }
    public void openNewActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
