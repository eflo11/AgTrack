package com.agtrack.model;


import java.sql.Date;

public class Crop {
    private int cropId;
    private int growerId;
    private String name = "";
    private String type = "";
    private String variety = "";
    private int growthTime = 0;
    private Date plantDate;

    private String latitude = "";
    private String longitude = "";

    public Crop(){}

    public Crop(String name, String type, String variety){
        this.name = name;
        this.type = type;
        this.variety = variety;
    }

    public int getCropId() {
        return cropId;
    }

    public void setCropId(int id) {
        this.cropId = id;
    }

    public int getGrowerId() {
        return growerId;
    }

    public void setGrowerId(int growerId) {
        this.growerId = growerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public int getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(int growthTime) {
        this.growthTime = growthTime;
    }

    public Date getPlantDate() {
        return plantDate;
    }

    public void setPlantDate(Date plantDate) {
        this.plantDate = plantDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
