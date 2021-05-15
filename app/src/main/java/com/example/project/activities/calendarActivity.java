package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calendarActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;


    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    TextInputEditText cowName;
    Spinner mySpinner;
    Button reportGen;
    TextView textView;

    public String newDate = "";
    //public String birthDate;

    //variables to carry the inputs
    String a;
    String b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        mySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(calendarActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.animalName));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        cowName = findViewById(R.id.cowname);
        reportGen = findViewById(R.id.BtnReport);
        radioGroup = findViewById(R.id.radioGroup);
        // textView = findViewById(R.id.newDate);

        mDisplayDate = findViewById(R.id.birthDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        calendarActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;

                newDate = month +"/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(newDate);

            }
        };

        cowName.addTextChangedListener(textWatcher);
        mDisplayDate.addTextChangedListener(textWatcher);

        reportGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

        });

    }
    public void checkButton(){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        c= String.valueOf(radioButton.getText());

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = cowName.getText().toString().trim();
            String datey = mDisplayDate.getText().toString().trim();
            reportGen.setEnabled(!name.isEmpty() && !datey.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void openActivity(){

        Intent intent = new Intent(this, ReportActivity.class);
        a = cowName.getText().toString();
        intent.putExtra("Value",a);

        b = mySpinner.getSelectedItem().toString();
        intent.putExtra("Value1",b);

        checkButton();
        intent.putExtra("Value2",c);
        intent.putExtra("Value3",newDate);

        startActivity(intent);
    }
}

