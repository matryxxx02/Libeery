package com.example.libeery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beer {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nameDisplay")
    @Expose
    private String nameDisplay;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("abv")
    @Expose
    private String abv;
    @SerializedName("glasswareId")
    @Expose
    private Integer glasswareId;
    @SerializedName("srmId")
    @Expose
    private Integer srmId;
    @SerializedName("availableId")
    @Expose
    private Integer availableId;
    @SerializedName("styleId")
    @Expose
    private Integer styleId;
    @SerializedName("isOrganic")
    @Expose
    private String isOrganic;
    @SerializedName("isRetired")
    @Expose
    private String isRetired;
    @SerializedName("labels")
    @Expose
    private Labels labels;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusDisplay")
    @Expose
    private String statusDisplay;
    @SerializedName("servingTemperature")
    @Expose
    private String servingTemperature;
    @SerializedName("servingTemperatureDisplay")
    @Expose
    private String servingTemperatureDisplay;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("updateDate")
    @Expose
    private String updateDate;
    @SerializedName("glass")
    @Expose
    private Glass glass;
    @SerializedName("srm")
    @Expose
    private Srm srm;
    @SerializedName("available")
    @Expose
    private Available available;
    @SerializedName("style")
    @Expose
    private Style style;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public Integer getGlasswareId() {
        return glasswareId;
    }

    public void setGlasswareId(Integer glasswareId) {
        this.glasswareId = glasswareId;
    }

    public Integer getSrmId() {
        return srmId;
    }

    public void setSrmId(Integer srmId) {
        this.srmId = srmId;
    }

    public Integer getAvailableId() {
        return availableId;
    }

    public void setAvailableId(Integer availableId) {
        this.availableId = availableId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(String isRetired) {
        this.isRetired = isRetired;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getServingTemperature() {
        return servingTemperature;
    }

    public void setServingTemperature(String servingTemperature) {
        this.servingTemperature = servingTemperature;
    }

    public String getServingTemperatureDisplay() {
        return servingTemperatureDisplay;
    }

    public void setServingTemperatureDisplay(String servingTemperatureDisplay) {
        this.servingTemperatureDisplay = servingTemperatureDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    public Srm getSrm() {
        return srm;
    }

    public void setSrm(Srm srm) {
        this.srm = srm;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameDisplay='" + nameDisplay + '\'' +
                ", description='" + description + '\'' +
                ", abv='" + abv + '\'' +
                ", glasswareId=" + glasswareId +
                ", srmId=" + srmId +
                ", availableId=" + availableId +
                ", styleId=" + styleId +
                ", isOrganic='" + isOrganic + '\'' +
                ", isRetired='" + isRetired + '\'' +
                ", labels=" + labels +
                ", status='" + status + '\'' +
                ", statusDisplay='" + statusDisplay + '\'' +
                ", servingTemperature='" + servingTemperature + '\'' +
                ", servingTemperatureDisplay='" + servingTemperatureDisplay + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", glass=" + glass +
                ", srm=" + srm +
                ", available=" + available +
                ", style=" + style +
                ", type='" + type + '\'' +
                '}';
    }

    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("createDate")
        @Expose
        private String createDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", createDate='" + createDate + '\'' +
                    '}';
        }
    }

    public class Glass {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("createDate")
        @Expose
        private String createDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        @Override
        public String toString() {
            return "Glass{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", createDate='" + createDate + '\'' +
                    '}';
        }
    }

    public class Labels {

        @SerializedName("icon")
        @Expose
        private String icon;
        @SerializedName("medium")
        @Expose
        private String medium;
        @SerializedName("large")
        @Expose
        private String large;
        @SerializedName("contentAwareIcon")
        @Expose
        private String contentAwareIcon;
        @SerializedName("contentAwareMedium")
        @Expose
        private String contentAwareMedium;
        @SerializedName("contentAwareLarge")
        @Expose
        private String contentAwareLarge;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getContentAwareIcon() {
            return contentAwareIcon;
        }

        public void setContentAwareIcon(String contentAwareIcon) {
            this.contentAwareIcon = contentAwareIcon;
        }

        public String getContentAwareMedium() {
            return contentAwareMedium;
        }

        public void setContentAwareMedium(String contentAwareMedium) {
            this.contentAwareMedium = contentAwareMedium;
        }

        public String getContentAwareLarge() {
            return contentAwareLarge;
        }

        public void setContentAwareLarge(String contentAwareLarge) {
            this.contentAwareLarge = contentAwareLarge;
        }

        @Override
        public String toString() {
            return "Labels{" +
                    "icon='" + icon + '\'' +
                    ", medium='" + medium + '\'' +
                    ", large='" + large + '\'' +
                    ", contentAwareIcon='" + contentAwareIcon + '\'' +
                    ", contentAwareMedium='" + contentAwareMedium + '\'' +
                    ", contentAwareLarge='" + contentAwareLarge + '\'' +
                    '}';
        }
    }

    public class Srm {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("hex")
        @Expose
        private String hex;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHex() {
            return hex;
        }

        public void setHex(String hex) {
            this.hex = hex;
        }

        @Override
        public String toString() {
            return "Srm{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", hex='" + hex + '\'' +
                    '}';
        }
    }

    public class Style {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("categoryId")
        @Expose
        private Integer categoryId;
        @SerializedName("category")
        @Expose
        private Category category;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shortName")
        @Expose
        private String shortName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("ibuMin")
        @Expose
        private String ibuMin;
        @SerializedName("ibuMax")
        @Expose
        private String ibuMax;
        @SerializedName("abvMin")
        @Expose
        private String abvMin;
        @SerializedName("abvMax")
        @Expose
        private String abvMax;
        @SerializedName("srmMin")
        @Expose
        private String srmMin;
        @SerializedName("srmMax")
        @Expose
        private String srmMax;
        @SerializedName("ogMin")
        @Expose
        private String ogMin;
        @SerializedName("fgMin")
        @Expose
        private String fgMin;
        @SerializedName("fgMax")
        @Expose
        private String fgMax;
        @SerializedName("createDate")
        @Expose
        private String createDate;
        @SerializedName("updateDate")
        @Expose
        private String updateDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIbuMin() {
            return ibuMin;
        }

        public void setIbuMin(String ibuMin) {
            this.ibuMin = ibuMin;
        }

        public String getIbuMax() {
            return ibuMax;
        }

        public void setIbuMax(String ibuMax) {
            this.ibuMax = ibuMax;
        }

        public String getAbvMin() {
            return abvMin;
        }

        public void setAbvMin(String abvMin) {
            this.abvMin = abvMin;
        }

        public String getAbvMax() {
            return abvMax;
        }

        public void setAbvMax(String abvMax) {
            this.abvMax = abvMax;
        }

        public String getSrmMin() {
            return srmMin;
        }

        public void setSrmMin(String srmMin) {
            this.srmMin = srmMin;
        }

        public String getSrmMax() {
            return srmMax;
        }

        public void setSrmMax(String srmMax) {
            this.srmMax = srmMax;
        }

        public String getOgMin() {
            return ogMin;
        }

        public void setOgMin(String ogMin) {
            this.ogMin = ogMin;
        }

        public String getFgMin() {
            return fgMin;
        }

        public void setFgMin(String fgMin) {
            this.fgMin = fgMin;
        }

        public String getFgMax() {
            return fgMax;
        }

        public void setFgMax(String fgMax) {
            this.fgMax = fgMax;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        @Override
        public String toString() {
            return "Style{" +
                    "id=" + id +
                    ", categoryId=" + categoryId +
                    ", category=" + category +
                    ", name='" + name + '\'' +
                    ", shortName='" + shortName + '\'' +
                    ", description='" + description + '\'' +
                    ", ibuMin='" + ibuMin + '\'' +
                    ", ibuMax='" + ibuMax + '\'' +
                    ", abvMin='" + abvMin + '\'' +
                    ", abvMax='" + abvMax + '\'' +
                    ", srmMin='" + srmMin + '\'' +
                    ", srmMax='" + srmMax + '\'' +
                    ", ogMin='" + ogMin + '\'' +
                    ", fgMin='" + fgMin + '\'' +
                    ", fgMax='" + fgMax + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    '}';
        }
    }

    public class Available {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Available{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
