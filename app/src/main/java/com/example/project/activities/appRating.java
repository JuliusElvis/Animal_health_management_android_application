package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.project.R;

public class appRating extends AppCompatActivity {

    RatingBar ratingBar;
    int myRating = 0;
    EditText review;
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rating);

        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);
        btnPost = findViewById(R.id.btnReview);

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
    }
}