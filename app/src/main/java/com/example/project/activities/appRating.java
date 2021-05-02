package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class appRating extends AppCompatActivity {

    RatingBar ratingBar;
    int myRating = 0;
    EditText review1;
    Button btnPost;
    String username;
    public String defRating = "";
    TextView appRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rating);

        ratingBar = findViewById(R.id.ratingBar);
        review1 = findViewById(R.id.etreview);
        btnPost = findViewById(R.id.btnReview);
        username = getIntent().getExtras().getString("username");

        appRating = findViewById(R.id.apprating);
        rator();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
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
                Toast.makeText(appRating.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rating = String.valueOf(myRating);
                final String review;
                review = String.valueOf(review1.getText());
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[3];
                        field[0] = "username";
                        field[1] = "rating";
                        field[2] = "review";
                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = username;
                        data[1] = rating;
                        data[2] = review;

                        PutData putData = new PutData("http://192.168.100.3/project1/AppRating.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                //String result = String.valueOf(putData.getResult());
                                //appRating.setText(result);
                                Toast.makeText(appRating.this,"Success",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(appRating.this,"Not successful",Toast.LENGTH_SHORT).show();
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
                field[0] = "username";
                //Creating array for data
                String[] data = new String[1];
                data[0] = username;

                PutData putData = new PutData("http://192.168.100.3/project1/defAppRated.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        defRating = String.valueOf(putData.getResult());
                            float f1 = Float.parseFloat(defRating);
                            ratingBar.setRating(f1);
                            appRating.setText(defRating);

                    }}
            }
        });
    }
}