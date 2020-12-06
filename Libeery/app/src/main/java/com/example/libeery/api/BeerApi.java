package com.example.libeery.api;

import com.example.libeery.data.Beer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerApi {
    @GET("search/?dataset=open-beer-database%40public-us&q=duvel")
    Call<Beer> getMBeers();

}
