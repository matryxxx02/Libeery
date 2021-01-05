package com.example.libeery.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity(tableName = "beer_table")
public class BeerRoom implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "cat_name")
    private String catName;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "favorite")
    private int favorite;
    @ColumnInfo(name = "update_date")
    private String updateDate;
    @ColumnInfo(name = "long_description")
    private String longDescription;
    @ColumnInfo(name = "abv")
    private String abv;
    @ColumnInfo(name = "image_url")
    private String imageURL;
    @ColumnInfo(name = "styleName")
    private String styleName;

    public BeerRoom(@NonNull String id, @NonNull String name, String catName, String description, int favorite, String updateDate, String longDescription, String abv, String imageURL, String styleName) {
        this.id = id;
        this.name = name;
        this.catName = catName;
        this.description = description;
        this.favorite = favorite;
        this.updateDate = updateDate;
        this.longDescription = longDescription;
        this.abv = abv;
        this.imageURL = imageURL;
        this.styleName = styleName;
    }

    public BeerRoom(@NonNull Beer b) {
        this.id = b.getId();
        this.name = b.getName();
        this.catName = b.getNameDisplay();

        if(b.getDescription() != null) this.description = b.getDescription();
        else this.description = "/";

        this.favorite = b.isFavorite()?1:0;

        if (b.getStyle() != null && b.getStyle().getUpdateDate() != null) this.updateDate = b.getStyle().getUpdateDate().split(" ")[0].replace("-","/");
        else this.updateDate = "/";

        if (b.getStyle() != null && b.getStyle().getDescription() != null) this.longDescription = b.getStyle().getDescription();
        else this.longDescription = "/";

        if (b.getAbv()!=null) this.abv = b.getAbv()+" %";
        else this.abv = "/";

        if(b.getLabels() != null && b.getLabels().getMedium() != null) this.imageURL = b.getLabels().getMedium();
        else this.imageURL = "";

        if(b.getStyle() != null && b.getStyle().getCategory() != null && b.getStyle().getCategory().getName() != null) this.styleName = b.getStyle().getCategory().getName();
        else this.styleName = "";
    }

    protected BeerRoom(Parcel in) {
        id = Objects.requireNonNull(in.readString());
        name = Objects.requireNonNull(in.readString());
        catName = in.readString();
        description = in.readString();
        favorite = in.readInt();
        updateDate = in.readString();
        longDescription = in.readString();
        abv = in.readString();
        imageURL = in.readString();
        styleName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(catName);
        dest.writeString(description);
        dest.writeInt(favorite);
        dest.writeString(updateDate);
        dest.writeString(longDescription);
        dest.writeString(abv);
        dest.writeString(imageURL);
        dest.writeString(styleName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeerRoom> CREATOR = new Creator<BeerRoom>() {
        @Override
        public BeerRoom createFromParcel(Parcel in) {
            return new BeerRoom(in);
        }

        @Override
        public BeerRoom[] newArray(int size) {
            return new BeerRoom[size];
        }
    };

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

    public String getDescription() {
        return description;
    }

    public int getFavorite() { return favorite; }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getAbv() {
        return abv;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getStyleName() {
        return styleName;
    }

    @NotNull
    @Override
    public String toString() {
        return "BeerRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", catName='" + catName + '\'' +
                ", description='" + description + '\'' +
                ", favorite=" + favorite +
                '}';
    }

}
