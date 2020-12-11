package com.example.libeery.data;

public class Beer {

    private String id;
    private String name;
    private String catName;
    private String country;
    private String description;
    private boolean favorite;

    public Beer(String id, String name, String catName, String country, String description) {
        this.id = id;
        this.name = name;
        this.catName = catName;
        this.country = country;
        this.description = description;
        this.favorite = false;
    }

    public String getId(){
        return id;
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

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
