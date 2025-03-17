package com.postcode.postcode_distance.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.postcode.postcode_distance.model.Postcode;

@Repository
public interface PostcodeRepository {
    // Finds a postcode by its code
    Optional<Postcode> findByCode(String code);
    // Loads postcodes from data source
    void loadPostcodes();
    // Returns all postcodes
    Collection<Postcode> getAllPostcodes();
    // Returns the total number of postcodes
    int getPostcodeCount();
} 