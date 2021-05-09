package com.example.project.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.adapters.ToolbarBindingAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.HomeActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ChangePassword extends AppCompatActivity {
    EditText textemail,textpass,textpass2;
    //String email,password,st3;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        textemail = findViewById(R.id.ademail);
        textpass = findViewById(R.id.adpass);
        textpass2 = findViewById(R.id.adconfpass);
        bt1 = findViewById(R.id.bt2);




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email,password,st3;
                email = String.valueOf(textemail.getText());
                password = String.valueOf(textpass.getText());
                st3 = String.valueOf(textpass2.getText());
                if (!email.equals("") && !password.equals("") && !st3.equals("")){
                    if (password.equals(st3)){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[2];
                                field[0] = "email";
                                field[1] = "password";
                                //Creating array for data
                                String[] data = new String[2];
                                data[0] = email;
                                data[1] = password;
                                PutData putData = new PutData("http://192.168.100.3/project1/changePassword.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Update Success")) {
                                            openNewActivity();
                                            finish();
                                        }else {
                                            Toast.makeText(ChangePassword.this, "Update failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });

                    }else {
                        Toast.makeText(getApplicationContext(), "Password don't match", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Please fill in all details", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    public void openNewActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}