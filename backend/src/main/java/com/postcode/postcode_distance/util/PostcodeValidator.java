package com.postcode.postcode_distance.util;

import java.util.regex.Pattern;

// Validates and formats UK postcodes
public class PostcodeValidator {
    
    // UK postcode regex pattern
    // Format: AA9A 9AA, A9A 9AA, A9 9AA, A99 9AA, AA9 9AA, AA99 9AA
    private static final Pattern UK_POSTCODE_PATTERN = Pattern.compile(
            "^[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}$", 
            Pattern.CASE_INSENSITIVE);
    
    /**
     * Validates if a string is a valid UK postcode
     * 
     * @param postcode The postcode to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String postcode) {
        if (postcode == null || postcode.trim().isEmpty()) {
            return false;
        }
        
        return UK_POSTCODE_PATTERN.matcher(postcode.trim()).matches();
    }
    
    /**
     * Formats a postcode to standard format (uppercase with proper spacing)
     * 
     * @param postcode The postcode to format
     * @return Formatted postcode
     */
    public static String format(String postcode) {
        if (postcode == null) {
            return null;
        }
        
        // Remove all whitespace and convert to uppercase
        String formatted = postcode.replaceAll("\\s+", "").toUpperCase();
        
        // Insert space at the correct position (before the last 3 characters)
        if (formatted.length() > 3) {
            formatted = formatted.substring(0, formatted.length() - 3) + " " + 
                       formatted.substring(formatted.length() - 3);
        }
        
        return formatted;
    }
} 