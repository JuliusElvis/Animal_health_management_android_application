package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.registeredDocs;
import com.example.project.retrofitUtil.Adapter;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class docProfileActivity extends AppCompatActivity {
    TextView nametxtView,tvrating,tvphone,tvlocality;
    String name,phoneno,locality,username;
    public String defRating = "";
    Button btnRate;
    RatingBar ratingStars;
    int myRating = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        nametxtView = findViewById(R.id.nametextView);
        tvrating = findViewById(R.id.ratingtv);
        tvlocality = findViewById(R.id.localitytextView);
        tvphone = findViewById(R.id.phonetv);
        username = getIntent().getExtras().getString("username");
       // phone = getIntent().getExtras().getString("raten");
        rator();
        phoney();
        loc();
        //tvphone.setText(phone);

        btnRate = findViewById(R.id.btnRate);
        ratingStars = findViewById(R.id.ratingBar);

        ratingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int rated = (int) rating;
                String message = null;

                myRating = (int)ratingBar.getRating();

                switch (rated){
                    case 1:
                        message = "rating 1";
                        break;
                    case 2:
                        message = "rating 2";
                        break;
                    case 3:
                        message = "rating 3";
                        break;
                    case 4:
                        message = "rating 4";
                        break;
                    case 5:
                        message = "rating 5";
                        break;
                }
                Toast.makeText(docProfileActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });



        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
            //phone = extras.getString("phone");
            //locality = extras.getString("locality");

        }
        nametxtView.setText(name);


        //tvlocality.setText(result);

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(docProfileActivity.this,String.valueOf(myRating),Toast.LENGTH_SHORT).show();
                final String rating = String.valueOf(myRating);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[3];
                        field[0] = "name";
                        field[1] = "username";
                        field[2] = "rating";
                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = name;
                        data[1] = username;
                        data[2] = rating;

                        PutData putData = new PutData("http://192.168.100.3/project1/rating.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = String.valueOf(putData.getResult());
                                //tvlocality.setText(result);
                                Float f = Float.parseFloat(result);
                                ratingStars.setRating(f);
                            }else{
                                Toast.makeText(docProfileActivity.this,"Not successful",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
    public void rator(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://192.168.100.3/project1/defaultRating.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        defRating = String.valueOf(putData.getResult());
                        if (defRating.equals("not yet rated")){
                            tvrating.setText("not rated");
                        }else {
                            float f1 = Float.parseFloat(defRating);
                            ratingStars.setRating(f1);
                            tvrating.setText(defRating);
                        }

                    }}
            }
        });
    }

    public void phoney(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://192.168.100.3/project1/getPhone.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        phoneno = String.valueOf(putData.getResult());
                        if (!phoneno.equals("")){
                            tvphone.setText(phoneno);
                        }else{
                            tvphone.setText(getResources().getString(R.string.Phone));
                        }

                        }

                    }}
            });

    }

    public void loc(){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://192.168.100.3/project1/getLoc.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        locality = String.valueOf(putData.getResult());
                        if (!locality.equals("")){
                            tvlocality.setText(locality);
                        }else{
                            tvlocality.setText(getResources().getString(R.string.locality));
                        }


                    }

                }}
        });

    }
}