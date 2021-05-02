package com.example.project.retrofitUtil;

import com.example.project.model.registeredDocs;
import com.example.project.model.reviews;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getUsers.php")
    Call<List<registeredDocs>> getUser( @Query("key") String keyword );
            //( @Query("key") String keyword );

   @GET("fetchReviews.php")
    Call<List<reviews>> getReviews();
}
