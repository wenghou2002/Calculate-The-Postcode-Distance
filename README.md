# Postcode Distance Calculator

A Spring Boot application that calculates the distance between UK postcodes using geographic coordinates. The application provides both a REST API, command-line interface, and a modern Angular frontend.

## Features

- Calculate distance between two UK postcodes
- Update postcode coordinates
- View list of postcodes
- Simple command-line interface
- RESTful API endpoints
- Modern, responsive UI with animations and visual feedback

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Node.js and npm (for frontend)

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

## Running the Application

### Backend Only (CLI Mode)

If you only want to run the backend, you can use the command-line interface:

```bash
cd backend
mvn spring-boot:run
```

Once the application starts, you'll be presented with a menu:

```
=== Postcode Distance Calculator CLI ===

=== Main Menu ===
1. Calculate Distance Between Postcodes
2. Update Postcode Coordinates
3. Exit

Enter your choice (1-3):
```

Simply enter the number corresponding to the option you want to use:

1. **Calculate Distance Between Postcodes**: Enter two postcodes to calculate the distance between them
2. **Update Postcode Coordinates**: Update the coordinates for a specific postcode
3. **Exit**: Exit the application

### REST API

The application also provides a REST API that can be accessed at:

```
http://localhost:8080/api/postcodes
```

Authentication is required for API access:
- Username: user
- Password: password

### Running with Frontend

For a complete experience with the Angular frontend:

1. Start the backend:
   ```
   cd backend
   mvn spring-boot:run
   ```

2. Start the frontend:
   ```
   cd frontend
   npm install
   npm start
   ```

3. Access the application at:
   ```
   http://localhost:4200
   ```

## API Endpoints

- `GET /api/postcodes/health` - Health check endpoint (no auth required)
- `GET /api/postcodes/distance?postcode1={postcode1}&postcode2={postcode2}` - Calculate distance between two postcodes
- `PUT /api/postcodes/{code}?latitude={latitude}&longitude={longitude}` - Update postcode coordinates
- `GET /api/postcodes/list?page={page}&pageSize={pageSize}` - Get paginated list of postcodes

Example:
```
GET /api/postcodes/distance?postcode1=SW1A1AA&postcode2=M11AE
```

## UI Enhancements

The application features a modern, user-friendly interface with the following enhancements:

### Visual Design
- Clean, professional design system with consistent spacing and colors
- Modern color scheme with gradient accents and carefully selected typography
- Improved visual hierarchy to help users focus on important elements
- Card-based design with subtle animations and elevation effects on hover

### User Experience
- Enhanced form elements with icon-based fields and helpful hints
- Improved visual feedback for all user interactions
- Intuitive navigation with icon indicators
- Responsive design that works well on all device sizes
- Better data visualization for coordinates and distances

### Technical Implementation
- Custom CSS variables for consistent theming
- Hover animations and transitions for interactive elements
- Enhanced loading states and error handling
- Improved accessibility with better color contrast and focus states
- Semantic HTML structure for better SEO and screen reader support

## Technologies Used

- Spring Boot (Backend)
- Spring Security
- Maven
- Java
- Angular (Frontend)
- HTML5/CSS3 with modern features
- Font Awesome icons
- Google Fonts (Poppins)
