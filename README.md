# UK Postcode Distance Calculator

A Spring Boot application that calculates the distance between UK postcodes using geographic coordinates. The application provides both a REST API and a command-line interface.

## Features

- Calculate distance between two UK postcodes
- Update postcode coordinates
- View list of postcodes
- Simple command-line interface
- RESTful API endpoints

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Setup and Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/postcode-distance.git
   cd postcode-distance
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   cd backend
   mvn spring-boot:run
   ```

## API Endpoints

- `GET /api/postcodes/health` - Health check endpoint
- `GET /api/postcodes/distance?postcode1=XX1 1XX&postcode2=YY2 2YY` - Calculate distance between two postcodes
- `PUT /api/postcodes/{code}?latitude=51.5074&longitude=-0.1278` - Update postcode coordinates
- `GET /api/postcodes/list?page=0&pageSize=50` - Get paginated list of postcodes

## Command-Line Interface

The application also provides a command-line interface that can be accessed when running the application. The CLI offers the following options:

1. Calculate Distance Between Postcodes
2. Update Postcode Coordinates
3. Exit

## Technologies Used

- Spring Boot
- Spring Security
- Maven
- Java
- Angular (Frontend)

## License

This project is licensed under the MIT License - see the LICENSE file for details. 