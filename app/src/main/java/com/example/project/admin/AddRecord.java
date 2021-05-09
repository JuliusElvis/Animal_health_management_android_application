package com.example.project.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activities.DocDisplayActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AddRecord extends AppCompatActivity {
    Button regDoc;
    EditText etregno,etname,etaddress,etqualification,etphone,etlocality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        regDoc = findViewById(R.id.doccReg);
        etregno = findViewById(R.id.regNot);
        etname = findViewById(R.id.nametv);
        etaddress = findViewById(R.id.addresstv);
        etqualification = findViewById(R.id.qualiftv);
        etphone = findViewById(R.id.phonetv);
        etlocality = findViewById(R.id.localitytv);

        regDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDoc();

            }
        });
    }

    public void addDoc(){
        final String reg_no,name,address,qualification,phone,locality;
        name = String.valueOf(etname.getText());
        address = String.valueOf(etaddress.getText());
        qualification = String.valueOf(etaddress.getText());
        phone = String.valueOf(etphone.getText());
        locality = String.valueOf(etlocality.getText());
        reg_no = String.valueOf(etregno.getText());
        if (!phone.equals("") && !locality.equals("") && !name.equals("") && !address.equals("") && !qualification.equals("") && !reg_no.equals("")){
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[6];
                    field[0] = "reg_no";
                    field[1] = "name";
                    field[2] = "address";
                    field[3] = "qualification";
                    field[4] = "locality";
                    field[5] = "phone";
                    //Creating array for data
                    String[] data = new String[6];
                    data[0] = reg_no;
                    data[1] = name;
                    data[2] = address;
                    data[3] = qualification;
                    data[4] = locality;
                    data[5] = phone;
                    PutData putData = new PutData("http://192.168.100.3/project1/vetSignUp.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Success")){
                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                openNewActivity();
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}