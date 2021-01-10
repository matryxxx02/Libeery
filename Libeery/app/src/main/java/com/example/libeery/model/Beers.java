package com.example.libeery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beers {
    @SerializedName("data")
    @Expose
    private final List<Beer> beers = null;

    public List<Beer> getBeers() {
        return beers;
    }

}
