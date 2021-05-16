package com.example.project.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AddAdmin extends AppCompatActivity {

    TextInputEditText adusername,adpass,adpass1;
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        adusername = findViewById(R.id.adusername);
        adpass = findViewById(R.id.adpassword);
        adpass1 = findViewById(R.id.adConfirmpassword);

        bt3 = findViewById(R.id.BtnAdmin);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username,password,password1;

                username = String.valueOf(adusername.getText()).trim();
                password = String.valueOf(adpass.getText()).trim();
                password1 = String.valueOf(adpass1.getText()).trim();

                if (!username.equals("") && !password1.equals("") && !password.equals("")){
                    if (password.equals(password1)){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[2];
                                field[0] = "username";
                                field[1] = "password";

                                String[] data = new String[2];
                                data[0] = username;
                                data[1] = password;

                                PutData putData = new PutData("http://192.168.100.3/project1/addAdmin.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Admin added successfuly")){
                                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                            openAdminActivity();
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Input all details",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openAdminActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}