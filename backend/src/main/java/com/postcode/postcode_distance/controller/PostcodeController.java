package com.postcode.postcode_distance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postcode.postcode_distance.model.DistanceResponse;
import com.postcode.postcode_distance.model.Postcode;
import com.postcode.postcode_distance.service.PostcodeService;

@RestController
@RequestMapping("/api/postcodes")
public class PostcodeController {

    private final PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping("/health")
    // Checks API health status
    public String healthCheck() {
        return "Postcode Distance API is up and running!";
    }
    
    @GetMapping("/distance")
    // Calculates distance between two postcodes
    public DistanceResponse getDistance(
            @RequestParam("postcode1") String postcode1,
            @RequestParam("postcode2") String postcode2) {
        return postcodeService.calculateDistance(postcode1, postcode2);
    }

    @PutMapping("/{code}")
    // Updates postcode coordinates
    public Postcode updatePostcode(
            @PathVariable String code,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {
        return postcodeService.updatePostcode(code, latitude, longitude);
    }
    
    @GetMapping("/list")
    // Retrieves paginated list of postcodes
    public Map<String, Object> getAllPostcodes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int pageSize) {
        List<Postcode> postcodes = postcodeService.getAllPostcodes(page, pageSize);
        int totalCount = postcodeService.getTotalPostcodeCount();
        
        Map<String, Object> response = new HashMap<>();
        response.put("postcodes", postcodes);
        response.put("totalCount", totalCount);
        
        return response;
    }
} 