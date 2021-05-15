package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class DocDetailsActivity extends AppCompatActivity {
    TextInputEditText etphoneNo,etLocality;
    Button regVet;
    String reg;
    TextView vetname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_details);

        etphoneNo = findViewById(R.id.phoneNo);
        regVet = findViewById(R.id.registerButton);
        vetname = findViewById(R.id.vetName);

        etLocality = findViewById(R.id.locality);
         reg = Objects.requireNonNull(getIntent().getExtras()).getString("reg_no");
         vetName();

        regVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone, locality, reg_no;

                phone = String.valueOf(etphoneNo.getText());
                locality = String.valueOf(etLocality.getText());
                reg_no = String.valueOf(reg);

                if (!phone.equals("") && !locality.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "phone";
                            field[1] = "locality";
                            field[2] = "reg_no";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = phone;
                            data[1] = locality;
                            data[2] = reg_no;

                            PutData putData = new PutData("http://192.168.100.3/project1/vetDetails.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Registration Success")){
                                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                        openNewActivity();
                                        finish();
                                    }else {
                                        if (result.equals("Registration Failed")){
                                            Toast.makeText(getApplicationContext(),"Registration failed1",Toast.LENGTH_SHORT).show();
                                        }else {
                                        Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Enter your details",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    public void openNewActivity(){
        Intent intent = new Intent(this, DocDisplayActivity.class);
        startActivity(intent);
    }
    public void vetName(){
        final String reg_no;
        reg_no = String.valueOf(reg);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "reg_no";
                String[] data = new String[1];
                data[0] = reg_no;

                PutData putData = new PutData("http://192.168.100.3/project1/vetName.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = String.valueOf(putData.getResult());
                        vetname.setText(result);
                    }
                }
            }
        });
    }
}
