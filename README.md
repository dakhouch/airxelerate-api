# AirXelerate API

## Overview

**AirXelerate API** is a Spring Boot-based backend service designed to manage authentication and flight-related data. It provides secure user authentication and CRUD operations for flights, ensuring a robust and scalable system.

 **There is an available account:**

 **Account 1**:
 
        email: anass.dakhouch@example.com,
 
        password: Qwerty123,
        
        role:User.
 
 **Account 2**:
 
         email: ramadan.karim@example.com, 
 
         password: Azerty123,
 
         role: Admin.
 
## Features

- **Authentication & Authorization**: Secure user login and role-based access control (RBAC).
- **Flight Management**: CRUD operations for flights.
- **Global Exception Handling**: Proper handling of business and technical exceptions.
- **Pagination Support**: Efficient data retrieval with pagination.
- **Internationalization (i18n)**: Multi-language support for API messages.
- **Security**: JWT-based authentication.

## Tech Stack

- **Spring Boot** (Backend Framework)
- **Spring Security** (Authentication & Authorization)
- **Spring Data JPA** (Database Access)
- **Hibernate** (ORM)
- **JWT (JSON Web Token)** (Authentication)
- **H2/PostgreSQL/MySQL** (Database - Configurable)
- **Maven** (Build Tool)

## Project Structure

```
src/main/java/com/example/airxelerateapi
│── api
│   ├── AuthController.java         # Authentication endpoints
│   ├── FlightController.java       # Flight-related endpoints
│
│── config
│   ├── jwt
│   │   ├── AppConfig.java          # Application configuration
│   │   ├── DataLoader.java         # Initial data seeding
│   │   ├── SecurityConfig.java     # Security configuration
│
│── dto
│   ├── auth                        # Authentication DTOs
│   │   ├── AccessTokenResponseDto.java
│   │   ├── AuthResponseDto.java
│   │   ├── LoginRequestDto.java
│   │   ├── UserResponseDto.java
│   ├── flight                      # Flight DTOs
│   │   ├── FlightRequestDto.java
│   │   ├── FlightResponseDto.java
│   ├── response                     # Generic response DTOs
│       ├── MessageResult.java
│       ├── PageResponseDto.java
│       ├── Pagination.java
│       ├── Result.java
│
│── entity
│   ├── AbstractEntity.java          # Base entity class
│   ├── Flight.java                  # Flight entity
│   ├── User.java                    # User entity
│
│── repository
│   ├── FlightRepository.java        # Flight data repository
│   ├── UserRepository.java          # User data repository
│
│── service
│   ├── facade
│   │   ├── AuthenticationService.java  # Interface for authentication
│   │   ├── FlightService.java          # Interface for flight operations
│   ├── impl
│   │   ├── AuthenticationServiceImpl.java  # Authentication service implementation
│   │   ├── FlightServiceImpl.java          # Flight service implementation
│
│── exception
│   ├── BusinessException.java       # Custom business exception
│   ├── TechnicalException.java      # Custom technical exception
│   ├── GlobalExceptionHandler.java  # Global exception handling
│
│── mapper
│   ├── AuthMapper.java              # DTO-to-Entity mapper for auth
│   ├── FlightMapper.java            # DTO-to-Entity mapper for flight
│
│── util
│   ├── ApiMessage.java              # Standardized API messages
│   ├── Config.java                   # General configuration
│   ├── ExceptionMessage.java         # Exception messages
│
│── validator
│   ├── FlightValidator.java         # Flight validation logic
│   ├── GlobalValidator.java         # Global validation logic
│
│── resources
│   ├── i18n
│   │   ├── messages.properties      # English messages
│   │   ├── messages_fr.properties   # French messages
│   ├── application.yml              # Main configuration file
```

## Setup Instructions

### Prerequisites

- **Java 17+**
- **Maven**
- **PostgreSQL/MySQL (Optional, if not using H2)**

### Steps to Run

1. **Clone the repository**

   ```sh
   git clone https://github.com/your-repo-url.git
   cd airxelerate-api
   ```

2. **Configure Database**\
   Update `application.yml` to set up your database (H2, PostgreSQL, or MySQL).

3. **Build and Run**

   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

| Endpoint                   | Method | Description         | Auth Required |
| -------------------        | ------ | ------------------- | ------------- |
| `/api/auth/login`          | POST   | User login with JWT | No            |
| `/api/auth/refresh-token`  | POST   | User registration   | No            |
| `/api/flights`             | GET    | Get all flights     | Yes           |
| `/api/flights/{id}`        | GET    | Get flight by ID    | Yes           |
| `/api/flights`             | POST   | Create a new flight | Yes           |
| `/api/flights/{id}`        | DELETE | Delete a flight     | Yes           |

## Security & Authentication

- Uses **JWT** for authentication.
- Secure endpoints require `Authorization: Bearer <token>` in the headers.

## Contribution

1. Fork the repository.
2. Create a new branch (`feature/your-feature`).
3. Commit and push your changes.
4. Open a pull request.

## License

This project is licensed under the **MIT License**.

