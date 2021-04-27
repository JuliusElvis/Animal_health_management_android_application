package com.example.project.model;

import com.google.gson.annotations.SerializedName;

public class registeredDocs {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("address") private String address;
    @SerializedName("qualification") private String qualification;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getQualification() {
        return qualification;
    }
}
