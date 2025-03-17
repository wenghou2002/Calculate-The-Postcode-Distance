package com.postcode.postcode_distance.service;

import java.util.List;

import com.postcode.postcode_distance.model.DistanceResponse;
import com.postcode.postcode_distance.model.Postcode;

public interface PostcodeService {
    // Retrieves a postcode by its code
    Postcode getPostcode(String code);
    // Calculates distance between two postcodes
    DistanceResponse calculateDistance(String postcode1, String postcode2);
    // Updates postcode coordinates
    Postcode updatePostcode(String code, Double latitude, Double longitude);
    // Retrieves paginated list of postcodes
    List<Postcode> getAllPostcodes(int page, int pageSize);
    // Gets total count of postcodes in the repository
    int getTotalPostcodeCount();
} 