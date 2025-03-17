package com.postcode.postcode_distance.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postcode.postcode_distance.exception.InvalidPostcodeException;
import com.postcode.postcode_distance.exception.PostcodeNotFoundException;
import com.postcode.postcode_distance.model.DistanceResponse;
import com.postcode.postcode_distance.model.Postcode;
import com.postcode.postcode_distance.repository.PostcodeRepository;
import com.postcode.postcode_distance.util.DistanceCalculator;
import com.postcode.postcode_distance.util.PostcodeValidator;

@Service
public class PostcodeServiceImpl implements PostcodeService {

    private static final Logger logger = LoggerFactory.getLogger(PostcodeServiceImpl.class);
    
    private final PostcodeRepository postcodeRepository;
    private final DistanceCalculator distanceCalculator;

    @Autowired
    public PostcodeServiceImpl(PostcodeRepository postcodeRepository, DistanceCalculator distanceCalculator) {
        this.postcodeRepository = postcodeRepository;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public Postcode getPostcode(String code) {
        // Validate postcode format
        if (!PostcodeValidator.isValid(code)) {
            logger.warn("Invalid postcode format: {}", code);
            throw new InvalidPostcodeException("Invalid UK postcode format: " + code);
        }
        
        // Format the postcode to standard format
        String formattedCode = PostcodeValidator.format(code);
        logger.debug("Looking up formatted postcode: {}", formattedCode);
        
        return postcodeRepository.findByCode(formattedCode)
                .orElseThrow(() -> {
                    logger.warn("Postcode not found: {}", formattedCode);
                    return new PostcodeNotFoundException("Postcode not found: " + formattedCode);
                });
    }

    @Override
    // Calculates distance between two postcodes
    public DistanceResponse calculateDistance(String postcode1, String postcode2) {
        logger.info("Calculating distance between postcodes: {} and {}", postcode1, postcode2);
        
        Postcode pc1 = getPostcode(postcode1);
        Postcode pc2 = getPostcode(postcode2);
        
        double distance = distanceCalculator.calculateDistance(
                pc1.getLatitude(), pc1.getLongitude(),
                pc2.getLatitude(), pc2.getLongitude());
        
        logger.debug("Distance calculated: {} km", distance);
        
        DistanceResponse response = new DistanceResponse();
        response.setPostcode1(pc1.getCode());
        response.setLatitude1(pc1.getLatitude());
        response.setLongitude1(pc1.getLongitude());
        response.setPostcode2(pc2.getCode());
        response.setLatitude2(pc2.getLatitude());
        response.setLongitude2(pc2.getLongitude());
        response.setDistance(distance);
        response.setUnit("km");
        
        return response;
    }

    @Override
    // Updates postcode coordinates
    public Postcode updatePostcode(String code, Double latitude, Double longitude) {
        logger.info("Updating postcode: {} with lat={}, lng={}", code, latitude, longitude);
        
        // Validate postcode format
        if (!PostcodeValidator.isValid(code)) {
            logger.warn("Invalid postcode format for update: {}", code);
            throw new InvalidPostcodeException("Invalid UK postcode format: " + code);
        }
        
        // Validate coordinates if provided
        if (latitude != null && (latitude < -90 || latitude > 90)) {
            logger.warn("Invalid latitude value: {}", latitude);
            throw new InvalidPostcodeException("Latitude must be between -90 and 90 degrees");
        }
        
        if (longitude != null && (longitude < -180 || longitude > 180)) {
            logger.warn("Invalid longitude value: {}", longitude);
            throw new InvalidPostcodeException("Longitude must be between -180 and 180 degrees");
        }
        
        Postcode postcode = getPostcode(code);
        
        if (latitude != null) {
            postcode.setLatitude(latitude);
        }
        
        if (longitude != null) {
            postcode.setLongitude(longitude);
        }
        
        logger.debug("Postcode updated successfully: {}", postcode.getCode());
        
        // In a real application, you would persist this change
        // For this example, the in-memory map is already updated
        
        return postcode;
    }
    
    @Override
    // Retrieves paginated list of postcodes
    public List<Postcode> getAllPostcodes(int page, int pageSize) {
        logger.info("Fetching postcodes page {} with size {}", page, pageSize);
        
        // Get all postcodes from repository
        List<Postcode> allPostcodes = new ArrayList<>(postcodeRepository.getAllPostcodes());
        
        // Calculate start and end indices for pagination
        int start = page * pageSize;
        int end = Math.min(start + pageSize, allPostcodes.size());
        
        // Return sublist if valid indices, otherwise empty list
        if (start < allPostcodes.size()) {
            logger.debug("Returning postcodes {} to {} of {}", start, end, allPostcodes.size());
            return allPostcodes.subList(start, end);
        } else {
            logger.debug("Page {} is out of bounds, returning empty list", page);
            return new ArrayList<>();
        }
    }
    
    @Override
    // Gets total count of postcodes in the repository
    public int getTotalPostcodeCount() {
        int count = postcodeRepository.getPostcodeCount();
        logger.debug("Total postcode count: {}", count);
        return count;
    }
} 