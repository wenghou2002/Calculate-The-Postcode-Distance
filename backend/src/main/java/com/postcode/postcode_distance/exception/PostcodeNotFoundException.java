package com.postcode.postcode_distance.exception;

// Exception thrown when postcode is not found in the repository
public class PostcodeNotFoundException extends RuntimeException {
    public PostcodeNotFoundException(String message) {
        super(message);
    }
} 