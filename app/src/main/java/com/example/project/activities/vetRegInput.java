package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class vetRegInput extends AppCompatActivity {

    EditText regNo;
    Button sendReg;
    String reg_no = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_reg_input);

        regNo = findViewById(R.id.regNo);
        sendReg = findViewById(R.id.button3);

        sendReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg_no = String.valueOf(regNo.getText());
                if (!reg_no.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                                    //Starting Write and Read data with URL
                                    //Creating array for parameters
                                    String[] field = new String[1];
                                    field[0] = "reg_no";
                                    //Creating array for data
                                    String[] data = new String[1];
                                    data[0] = reg_no;

                            PutData putData = new PutData("http://192.168.100.3/project1/verifyReg.php", "POST", field, data);
                            if (putData.startPut()){
                                if (putData.onComplete()){
                                    String result = putData.getResult();
                                    if (result.equals("Verification Success")){
                                        openNewActivity();
                                    }else {
                                        Toast.makeText(getApplicationContext(),"Verification failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"You didn't enter anything",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, DocDetailsActivity.class);
        intent.putExtra("reg_no",reg_no);
        startActivity(intent);
    }
}