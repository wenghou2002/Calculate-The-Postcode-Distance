# Postcode Distance Calculator

This application calculates the distance between UK postcodes.

## Running the Application

### Backend Only

If you only want to run the backend, you can use the command-line interface:

```bash
# Build and run the application
mvn clean install
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

Example API endpoints:
- `GET /api/postcodes/health` - Health check (no auth required)
- `GET /api/postcodes/distance?postcode1=SW1A1AA&postcode2=M11AE` - Calculate distance
- `PUT /api/postcodes/{code}?latitude=51.5014&longitude=-0.1419` - Update postcode

## Running with Frontend

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