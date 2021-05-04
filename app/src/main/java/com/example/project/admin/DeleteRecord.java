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

public class DeleteRecord extends AppCompatActivity {

    EditText etName,etReg;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);

        etName = findViewById(R.id.nameet);
        etReg = findViewById(R.id.regnoet);
        delete = findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,reg_no;
                name = String.valueOf(etName.getText());
                reg_no = String.valueOf(etReg.getText());
                if (!name.equals("") && !reg_no.equals("")){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "name";
                            field[1] = "reg_no";
                            String[] data = new String[2];
                            data[0] = name;
                            data[1] = reg_no;
                            PutData putData = new PutData("http://192.168.100.3/project1/delRec.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Delete Success")){
                                        Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();
                                        openActivity();
                                    }else {
                                        Toast.makeText(getApplicationContext(),"Couldn't delete",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });




    }
    public void openActivity(){
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}