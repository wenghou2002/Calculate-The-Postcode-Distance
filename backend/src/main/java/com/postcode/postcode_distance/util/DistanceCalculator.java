package com.postcode.postcode_distance.util;

// Calculates distance between geographic coordinates
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371.0; // kilometers

    // Calculates distance between two geographic points using Haversine formula
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double lat1Radians = Math.toRadians(latitude1);
        double lon1Radians = Math.toRadians(longitude1);
        double lat2Radians = Math.toRadians(latitude2);
        double lon2Radians = Math.toRadians(longitude2);
        
        double a = haversine(lat1Radians, lat2Radians) 
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS * c;
    }
    
    // Calculates haversine of angle difference
    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }
    
    // Squares a number
    private double square(double x) {
        return x * x;
    }
} 