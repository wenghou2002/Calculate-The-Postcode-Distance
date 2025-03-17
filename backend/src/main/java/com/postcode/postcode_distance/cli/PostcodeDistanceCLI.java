package com.postcode.postcode_distance.cli;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.postcode.postcode_distance.model.DistanceResponse;
import com.postcode.postcode_distance.model.Postcode;
import com.postcode.postcode_distance.service.PostcodeService;

// Command-line interface for postcode distance calculations
@Component
@Profile("!test")
public class PostcodeDistanceCLI implements CommandLineRunner {

    private final PostcodeService postcodeService;
    private boolean running = true;

    public PostcodeDistanceCLI(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @Override
    // Runs the CLI application
    public void run(String... args) {
        System.out.println("\n=== Postcode Distance Calculator CLI ===\n");
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (running) {
                displayMainMenu();
                int choice = getMenuChoice(scanner, 3);
                
                switch (choice) {
                    case 1:
                        calculateDistance(scanner);
                        break;
                    case 2:
                        updatePostcode(scanner);
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    // Displays the main menu options
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Calculate Distance Between Postcodes");
        System.out.println("2. Update Postcode Coordinates");
        System.out.println("3. Exit");
        System.out.print("\nEnter your choice (1-3): ");
    }
    
    // Gets a valid menu choice from user input
    private int getMenuChoice(Scanner scanner, int maxChoice) {
        int choice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                
                if (choice >= 1 && choice <= maxChoice) {
                    validInput = true;
                } else {
                    System.out.print("Please enter a number between 1 and " + maxChoice + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        
        return choice;
    }
    
    // Handles the distance calculation workflow
    private void calculateDistance(Scanner scanner) {
        System.out.println("\n=== Calculate Distance Between Postcodes ===");
        
        System.out.print("Enter first postcode: ");
        String postcode1 = scanner.nextLine().trim();
        
        System.out.print("Enter second postcode: ");
        String postcode2 = scanner.nextLine().trim();
        
        try {
            DistanceResponse response = postcodeService.calculateDistance(postcode1, postcode2);
            
            System.out.println("\nResults:");
            System.out.println("----------------------------");
            System.out.printf("Postcode 1: %s (%.6f, %.6f)%n", 
                    response.getPostcode1(), response.getLatitude1(), response.getLongitude1());
            System.out.printf("Postcode 2: %s (%.6f, %.6f)%n", 
                    response.getPostcode2(), response.getLatitude2(), response.getLongitude2());
            System.out.printf("Distance: %.2f %s%n", 
                    response.getDistance(), response.getUnit());
            
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
    
    // Handles the postcode update workflow
    private void updatePostcode(Scanner scanner) {
        System.out.println("\n=== Update Postcode Coordinates ===");
        
        System.out.print("Enter postcode to update: ");
        String code = scanner.nextLine().trim();
        
        System.out.print("Enter new latitude: ");
        double latitude = getDoubleInput(scanner);
        
        System.out.print("Enter new longitude: ");
        double longitude = getDoubleInput(scanner);
        
        try {
            Postcode postcode = postcodeService.updatePostcode(code, latitude, longitude);
            
            System.out.println("\nPostcode Updated:");
            System.out.println("----------------");
            System.out.println("Code: " + postcode.getCode());
            System.out.println("Latitude: " + postcode.getLatitude());
            System.out.println("Longitude: " + postcode.getLongitude());
            
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
    
    // Gets a valid double value from user input
    private double getDoubleInput(Scanner scanner) {
        double value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                value = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        
        return value;
    }
    
    // Exits the application
    private void exit() {
        System.out.println("\nExiting Postcode Distance Calculator. Goodbye!");
        running = false;
    }
} 