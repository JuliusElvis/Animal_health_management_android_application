package com.example.project.model;

import com.google.gson.annotations.SerializedName;

public class reviews {
    @SerializedName("username") private String username;
    @SerializedName("review") private String review;

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }
}
