package com.example.libeery.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Beers {
    @SerializedName("records")
    @Expose
    private List<Beer> beers = null;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> records) {
        this.beers = records;
    }

    public class Beer {
        @SerializedName("datasetid")
        @Expose
        private String datasetid;
        @SerializedName("recordid")
        @Expose
        private String recordid;
        @SerializedName("fields")
        @Expose
        private Fields fields;
        @SerializedName("record_timestamp")
        @Expose
        private String recordTimestamp;
        @SerializedName("geometry")
        @Expose
        private Geometry geometry;

        public String getDatasetid() {
            return datasetid;
        }

        public void setDatasetid(String datasetid) {
            this.datasetid = datasetid;
        }

        public String getRecordid() {
            return recordid;
        }

        public void setRecordid(String recordid) {
            this.recordid = recordid;
        }

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }

        public String getRecordTimestamp() {
            return recordTimestamp;
        }

        public void setRecordTimestamp(String recordTimestamp) {
            this.recordTimestamp = recordTimestamp;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        @Override
        public String toString() {
            return "Beer{" +
                    "datasetid='" + datasetid + '\'' +
                    ", recordid='" + recordid + '\'' +
                    ", fields=" + fields.toString() +
                    ", recordTimestamp='" + recordTimestamp + '\'' +
                    ", geometry=" + geometry.toString() +
                    '}';
        }
    }

    public class Fields {
        @SerializedName("brewery_id")
        @Expose
        private String breweryId;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("cat_id")
        @Expose
        private String catId;
        @SerializedName("descript")
        @Expose
        private String descript;
        @SerializedName("upc")
        @Expose
        private Integer upc;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates = null;
        @SerializedName("srm")
        @Expose
        private Integer srm;
        @SerializedName("add_user")
        @Expose
        private String addUser;
        @SerializedName("abv")
        @Expose
        private Double abv;
        @SerializedName("style_id")
        @Expose
        private String styleId;
        @SerializedName("name_breweries")
        @Expose
        private String nameBreweries;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("ibu")
        @Expose
        private Integer ibu;
        @SerializedName("style_name")
        @Expose
        private String styleName;

        public String getBreweryId() {
            return breweryId;
        }

        public void setBreweryId(String breweryId) {
            this.breweryId = breweryId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCatId() {
            return catId;
        }

        public void setCatId(String catId) {
            this.catId = catId;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public Integer getUpc() {
            return upc;
        }

        public void setUpc(Integer upc) {
            this.upc = upc;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

        public Integer getSrm() {
            return srm;
        }

        public void setSrm(Integer srm) {
            this.srm = srm;
        }

        public String getAddUser() {
            return addUser;
        }

        public void setAddUser(String addUser) {
            this.addUser = addUser;
        }

        public Double getAbv() {
            return abv;
        }

        public void setAbv(Double abv) {
            this.abv = abv;
        }

        public String getStyleId() {
            return styleId;
        }

        public void setStyleId(String styleId) {
            this.styleId = styleId;
        }

        public String getNameBreweries() {
            return nameBreweries;
        }

        public void setNameBreweries(String nameBreweries) {
            this.nameBreweries = nameBreweries;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getIbu() {
            return ibu;
        }

        public void setIbu(Integer ibu) {
            this.ibu = ibu;
        }

        public String getStyleName() {
            return styleName;
        }

        public void setStyleName(String styleName) {
            this.styleName = styleName;
        }

        @Override
        public String toString() {
            return "Fields{" +
                    "breweryId='" + breweryId + '\'' +
                    ", city='" + city + '\'' +
                    ", name='" + name + '\'' +
                    ", catName='" + catName + '\'' +
                    ", country='" + country + '\'' +
                    ", catId='" + catId + '\'' +
                    ", descript='" + descript + '\'' +
                    ", upc=" + upc +
                    ", coordinates=" + coordinates +
                    ", srm=" + srm +
                    ", addUser='" + addUser + '\'' +
                    ", abv=" + abv +
                    ", styleId='" + styleId + '\'' +
                    ", nameBreweries='" + nameBreweries + '\'' +
                    ", id='" + id + '\'' +
                    ", ibu=" + ibu +
                    ", styleName='" + styleName + '\'' +
                    '}';
        }
    }

    public class Geometry {
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

        @Override
        public String toString() {
            return "Geometry{" +
                    "type='" + type + '\'' +
                    ", coordinates=" + coordinates +
                    '}';
        }
    }
}
