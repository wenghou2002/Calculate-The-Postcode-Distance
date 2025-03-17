package com.postcode.postcode_distance.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.postcode.postcode_distance.model.Postcode;

@Repository
public class PostcodeRepositoryImpl implements PostcodeRepository {

    private final Map<String, Postcode> postcodeMap = new HashMap<>();

    @PostConstruct
    @Override
    // Loads postcodes from CSV file
    public void loadPostcodes() {
        // For simplicity, we'll load from a CSV file in the resources folder
        // In a real application, you would download and process the files from the provided URLs
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("postcodes.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            
            String line;
            // Skip header if exists
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    // New format: id,postcode,latitude,longitude
                    String code = parts[1].trim().toUpperCase();
                    try {
                        double lat = Double.parseDouble(parts[2]);
                        double lng = Double.parseDouble(parts[3]);
                        postcodeMap.put(code, new Postcode(code, lat, lng));
                    } catch (NumberFormatException e) {
                        // Skip invalid entries
                    }
                } else if (parts.length >= 3) {
                    // Handle old format just in case: postcode,latitude,longitude
                    String code = parts[0].trim().toUpperCase();
                    try {
                        double lat = Double.parseDouble(parts[1]);
                        double lng = Double.parseDouble(parts[2]);
                        postcodeMap.put(code, new Postcode(code, lat, lng));
                    } catch (NumberFormatException e) {
                        // Skip invalid entries
                    }
                }
            }
            
            System.out.println("Loaded " + postcodeMap.size() + " postcodes");
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load postcodes", e);
        }
    }

    @Override
    // Finds a postcode by its code
    public Optional<Postcode> findByCode(String code) {
        if (code == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(postcodeMap.get(code.trim().toUpperCase()));
    }
    
    @Override
    // Returns all postcodes
    public Collection<Postcode> getAllPostcodes() {
        return postcodeMap.values();
    }
    
    @Override
    // Returns the total number of postcodes
    public int getPostcodeCount() {
        return postcodeMap.size();
    }
} 