package com.example.libeery.model;

import com.example.libeery.model.Beer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beers {
    @SerializedName("data")
    @Expose
    private List<Beer> beers = null;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> records) {
        this.beers = records;
    }
}
