# Payment Service - Hotel Booking App Microservice

This is a REST-based payment processing microservice designed specifically to handle transactions for the main **[Hotel Booking App](https://github.com/MitkoVasilev01/hotel-booking-app)**. 

It receives request calls, processes simulated payments, and records transactions in a shared or dedicated MySQL database.

## System Architecture Role
In this microservice ecosystem, `payment-service` acts as an independent billing engine. The main application (`hotel-booking-app`) communicates with this service via **Spring Cloud Feign Client** requests sent to `/api/payments`.

```text
[hotel-booking-app] --(Feign Client POST)--> [payment-service] --(Saves)--> [MySQL Database]
```

Tech Stack
Backend: Java 17, Spring Boot 3.3.5, Spring Web, Spring Data JPA.
Database: MySQL, Hibernate ORM.
Tools: Maven, Git, Lombok, Docker (configured with a multi-stage Dockerfile).

API Endpoints
1. Process Payment
Method: POST
Path: /api/payments
Request Parameters:
reservationId (UUID) - The ID of the reservation being paid for.
amount (Double) - The transaction amount.
Response: 200 OK with the created Payment transaction details as JSON.

2. Retrieve Payment Details
Method: GET
Path: /api/payments/{id}
Response: 200 OK with the specific Payment details, or 404 Not Found.

How to Run
1. Orchestrated via Docker Compose (Recommended)
This service is designed to be launched alongside the main application and database inside a unified Docker network. For the complete, single-command startup guide, please refer to the main repository:
Hotel Booking App - Orchestration Guide

2. Run Standalone (Locally)
If you wish to run this microservice individually:
1. Clone the repository:
git clone https://github.com/MitkoVasilev01/payment-service.git

2. Configure database credentials in src/main/resources/application.properties (replace placeholders with your local MySQL credentials):
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
spring.datasource.username=your_username
spring.datasource.password=your_password

3. Run the application (it defaults to port 8081 to prevent conflicts with the main app):
mvn spring-boot:run
