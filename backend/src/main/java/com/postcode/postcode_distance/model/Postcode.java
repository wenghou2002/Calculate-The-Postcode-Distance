package com.postcode.postcode_distance.model;

// Represents a UK postcode with geographic coordinates
public class Postcode {
    private String code;
    private double latitude;
    private double longitude;

    public Postcode() {
    }

    public Postcode(String code, double latitude, double longitude) {
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
} 