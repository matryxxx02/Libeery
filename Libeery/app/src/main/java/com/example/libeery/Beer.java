package com.example.libeery;

public class Beer {

    private String name;
    private String catName;
    private String country;
    private boolean favorite;

    public Beer(String name, String catName, String country) {
        this.name = name;
        this.catName = catName;
        this.country = country;
        this.favorite = false;
    }

    public String getName() {
        return name;
    }

    public String getCatName() {
        return catName;
    }

    public String getCountry() {
        return country;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
