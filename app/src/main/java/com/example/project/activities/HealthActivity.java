package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.project.R;

public class HealthActivity extends AppCompatActivity {
    public CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7,ch8,ch9,ch10,ch11,ch12,ch13,ch14,ch15,ch16,ch17,ch18,ch19;
    TextView t1;
    Button btnHealth;
    public String st1="",st2 = "",st3 = "",st4 ="",st5 ="",st6 = "",st7="",st8 = "",st9 = "",st10 = "",st11= "",
            st12 = "",st13 = "",st14 = "",st15 = "",st16 = "",st17 = "",st18 = "",st19 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        btnHealth = findViewById(R.id.button2);
        t1 = findViewById(R.id.dis);
        checkBoxes();

        //this starts python
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        //creates python object
        final PyObject pyObject = py.getModule("test3");

        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&!ch5.isChecked()&&!ch6.isChecked()&&
                        !ch7.isChecked()&&!ch8.isChecked()&&!ch9.isChecked()&&!ch10.isChecked()&&!ch11.isChecked()&&!ch12.isChecked()&&
                        !ch13.isChecked()&&!ch14.isChecked()&&!ch15.isChecked()&&!ch16.isChecked()&&!ch17.isChecked()&&!ch18.isChecked()&&
                        !ch19.isChecked()){
                    Toast.makeText(HealthActivity.this,"You did not select any symptom",Toast.LENGTH_SHORT).show();
                }else {
                    checkBox1();
                    PyObject obj = pyObject.callAttr("main",st1,st2,st3,st4,st5,st6,st7,st8,st9,st10,st11,st12,st13,st14,
                           st15,st16,st17,st18,st19);
                   //t1.setText(obj.toString());
                    //PyObject obj = pyObject.callAttr("main",st1);
                    t1.setText(obj.toString());


                }
            }
        });

    }
    public void checkBoxes(){
        ch1 = findViewById(R.id.checkBox);
        ch2 = findViewById(R.id.checkBox2);
        ch3 = findViewById(R.id.checkBox3);
        ch4 = findViewById(R.id.checkBox4);
        ch5 = findViewById(R.id.checkBox5);
        ch6 = findViewById(R.id.checkBox6);
        ch7 = findViewById(R.id.checkBox7);
        ch8 = findViewById(R.id.checkBox8);
        ch9 = findViewById(R.id.checkBox9);
        ch10 = findViewById(R.id.checkBox10);
        ch11 = findViewById(R.id.checkBox11);
        ch12 = findViewById(R.id.checkBox12);
        ch13 = findViewById(R.id.checkBox13);
        ch14 = findViewById(R.id.checkBox14);
        ch15 = findViewById(R.id.checkBox15);
        ch16 = findViewById(R.id.checkBox16);
        ch17 = findViewById(R.id.checkBox17);
        ch18 = findViewById(R.id.checkBox18);
        ch19 = findViewById(R.id.checkBox19);


    }
    public void checkBox1(){
        if (ch1.isChecked()){
            st1 = "low_milk";
        }
        if (ch2.isChecked()){
            st2 = "dullness";
        }
        if (ch3.isChecked()){
            st3 = "weakness";
        }
        if (ch4.isChecked()){
            st4 = "low_appetite";
        }
        if (ch5.isChecked()){
            st5 = "high_fever";
        }
        if (ch6.isChecked()){
            st6 = "distress";
        }
        if (ch7.isChecked()){
            st7 = "convulsions";
        }
        if (ch8.isChecked()){
            st8 = "breathing_difficulty";
        }
        if (ch9.isChecked()){
            st9 = "salivation";
        }
        if (ch10.isChecked()){
            st10 = "swollen_teats";
        }
        if (ch11.isChecked()){
            st11 = "abortion";
        }
        if (ch12.isChecked()){
            st12 = "swollen_hoof";
        }
        if (ch13.isChecked()){
            st13 = "body_discharge";
        }
        if (ch14.isChecked()){
            st14 = "sudden_death";
        }
        if (ch15.isChecked()){
            st15 = "swellings";
        }
        if (ch16.isChecked()){
            st16 = "lameness";
        }
        if (ch17.isChecked()){
            st17 = "ruminations";
        }
        if (ch18.isChecked()){
            st18 = "rapid_pulse";
        }
        if (ch19.isChecked()){
            st19 = "stomach_swelling";
        }
    }
}
