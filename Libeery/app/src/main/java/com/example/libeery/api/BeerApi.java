package com.example.libeery.api;

import com.example.libeery.model.Beers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerApi {
    @GET("search?key=325db7a335e682c607ad018cc1b32151&q=bud&type=beer")
    Call<Beers> getBeers();

}
