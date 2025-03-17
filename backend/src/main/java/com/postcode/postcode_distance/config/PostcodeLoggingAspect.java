package com.postcode.postcode_distance.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postcode.postcode_distance.model.DistanceResponse;

// Aspect for structured logging of postcode distance calculations
@Aspect
@Component
public class PostcodeLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger("POSTCODE_REQUESTS");
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Logs postcode distance calculations in a structured format
    @Around("execution(* com.postcode.postcode_distance.service.PostcodeService.calculateDistance(..))")
    public Object logPostcodeDistance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String postcode1 = (String) args[0];
        String postcode2 = (String) args[1];
        
        long startTime = System.currentTimeMillis();
        
        try {
            // Execute the actual method
            Object result = joinPoint.proceed();
            
            // Log successful request in structured format
            if (result instanceof DistanceResponse) {
                DistanceResponse response = (DistanceResponse) result;
                Map<String, Object> logData = new HashMap<>();
                logData.put("event", "POSTCODE_DISTANCE_CALCULATION");
                logData.put("status", "SUCCESS");
                logData.put("postcode1", response.getPostcode1());
                logData.put("postcode2", response.getPostcode2());
                logData.put("distance_km", response.getDistance());
                logData.put("duration_ms", System.currentTimeMillis() - startTime);
                
                logger.info(objectMapper.writeValueAsString(logData));
            }
            
            return result;
        } catch (Exception e) {
            // Log failed request
            Map<String, Object> logData = new HashMap<>();
            logData.put("event", "POSTCODE_DISTANCE_CALCULATION");
            logData.put("status", "ERROR");
            logData.put("postcode1", postcode1);
            logData.put("postcode2", postcode2);
            logData.put("error", e.getMessage());
            logData.put("duration_ms", System.currentTimeMillis() - startTime);
            
            logger.error(objectMapper.writeValueAsString(logData));
            throw e;
        }
    }
    
    // Logs postcode updates in a structured format
    @Around("execution(* com.postcode.postcode_distance.service.PostcodeService.updatePostcode(..))")
    public Object logPostcodeUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String postcode = (String) args[0];
        Double latitude = args.length > 1 ? (Double) args[1] : null;
        Double longitude = args.length > 2 ? (Double) args[2] : null;
        
        long startTime = System.currentTimeMillis();
        
        try {
            // Execute the actual method
            Object result = joinPoint.proceed();
            
            // Log successful update in structured format
            Map<String, Object> logData = new HashMap<>();
            logData.put("event", "POSTCODE_UPDATE");
            logData.put("status", "SUCCESS");
            logData.put("postcode", postcode);
            logData.put("latitude", latitude);
            logData.put("longitude", longitude);
            logData.put("duration_ms", System.currentTimeMillis() - startTime);
            
            logger.info(objectMapper.writeValueAsString(logData));
            
            return result;
        } catch (Exception e) {
            // Log failed update
            Map<String, Object> logData = new HashMap<>();
            logData.put("event", "POSTCODE_UPDATE");
            logData.put("status", "ERROR");
            logData.put("postcode", postcode);
            logData.put("error", e.getMessage());
            logData.put("duration_ms", System.currentTimeMillis() - startTime);
            
            logger.error(objectMapper.writeValueAsString(logData));
            throw e;
        }
    }
} 