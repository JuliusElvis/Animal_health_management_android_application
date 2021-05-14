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
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class CodeActivity extends AppCompatActivity {
    Button bt2;
    EditText email2,code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        bt2 = findViewById(R.id.newCode);
        email2 = findViewById(R.id.email1);
        code = findViewById(R.id.code);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email,verif_code;
                email = String.valueOf(email2.getText());
                verif_code = String.valueOf(code.getText());

                if (!email.equals("") && !verif_code.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "verif_code";

                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = verif_code;


                            PutData putData = new PutData("http://192.168.100.3/project1/verifyCode.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Success")){
                                        openActivity();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Not success",Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Missing details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

public void openActivity(){
        Intent intent = new Intent(this, PassActivity.class);
        intent.putExtra("email",String.valueOf(email2.getText()));
        startActivity(intent);
        }
}