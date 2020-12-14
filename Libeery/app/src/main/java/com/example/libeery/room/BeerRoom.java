package com.example.libeery.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beer_table")
public class BeerRoom {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "cat_name")
    private String catName;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "description")
    private String description;

    public BeerRoom(@NonNull String id, @NonNull String name, String catName, String country, String description) {
        this.id = id;
        this.name = name;
        this.catName = catName;
        this.country = country;
        this.description = description;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
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
}
