package com.postcode.postcode_distance.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostcodeNotFoundException.class)
    // Handles postcode not found exceptions
    public ResponseEntity<Object> handlePostcodeNotFoundException(
            PostcodeNotFoundException ex, WebRequest request) {
        
        return createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(InvalidPostcodeException.class)
    // Handles invalid postcode format exceptions
    public ResponseEntity<Object> handleInvalidPostcodeException(
            InvalidPostcodeException ex, WebRequest request) {
        
        return createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(Exception.class)
    // Handles all other exceptions
    public ResponseEntity<Object> handleGlobalException(
            Exception ex, WebRequest request) {
        
        return createErrorResponse(
                "An unexpected error occurred", 
                HttpStatus.INTERNAL_SERVER_ERROR, 
                request);
    }
    
    // Creates standardized error response
    private ResponseEntity<Object> createErrorResponse(
            String message, HttpStatus status, WebRequest request) {
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, status);
    }
} 