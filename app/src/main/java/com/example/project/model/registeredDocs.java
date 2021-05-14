package com.example.project.model;

import com.google.gson.annotations.SerializedName;

public class registeredDocs {

    @SerializedName("name") private String name;
    @SerializedName("address") private String address;
    @SerializedName("qualification") private String qualification;
    //@SerializedName("phone") private String phone;
    //@SerializedName("locality") private String locality;



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getQualification() {
        return qualification;
    }

    /*public String getPhone() {
        return phone;
    }

    public String getLocality() {
        return locality;
    }*/
}
