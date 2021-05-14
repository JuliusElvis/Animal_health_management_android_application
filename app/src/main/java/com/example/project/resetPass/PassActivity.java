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
import com.example.project.activities.MainActivity;
import com.example.project.admin.ChangePassword;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PassActivity extends AppCompatActivity {
    String email1;
    EditText et1,et2;
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        email1 = getIntent().getExtras().getString("email");
        et1 = findViewById(R.id.newpass);
        et2 = findViewById(R.id.confpass);

        bt4 = findViewById(R.id.registerButton);

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email,password,st3;
                email = String.valueOf(email1);
                password = String.valueOf(et1.getText());
                st3 = String.valueOf(et2.getText());

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
                                            Toast.makeText(PassActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                                            openActivity();
                                            finish();
                                        }else {
                                            Toast.makeText(PassActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
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
    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}