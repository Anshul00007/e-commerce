# 🛒 F-Commerce Backend (Spring Boot)

A scalable and secure backend system for an e-commerce platform built using **Java 21** and **Spring Boot**. This project demonstrates enterprise backend engineering concepts including stateless authentication, transactional integrity, concurrency control, and query optimization.

---

## 🏗️ System Architecture

The application follows a clean, decoupled **Layered (3-Tier) Architecture** to separate concerns and ensure maintainability:

```text
[ Client (Postman / Frontend) ]
               │
               ▼ (HTTP Requests)
    [ Controller Layer ] <───> [ DTOs & Validation ]
               │
               ▼ (Business Logic)
      [ Service Layer ]  <───> [ Security / JWT Filters ]
               │
               ▼ (Data Access)
    [ Repository Layer ] <───> [ JPA / Hibernate ]
               │
               ▼ (Persistence)
       [ MySQL Database ]
```

---

## 🚀 Tech Stack & Design Patterns

* **Core Framework**: Java 21, Spring Boot 3.5.x, Spring Data JPA, Spring Security.
* **Security & Authentication**: Stateless JWT (JSON Web Tokens), BCrypt Password Encryption, Role-Based Access Control (`ROLE_USER`, `ROLE_ADMIN`).
* **Database & Optimization**: MySQL, HikariCP, Indexing, JPQL `JOIN FETCH` (N+1 Query Resolution).
* **Data Design**: Data Transfer Object (DTO) pattern, `@ControllerAdvice` Global Exception Handler, JSR-380 Validation.
* **Concurrency & Reliability**: `@Transactional` boundaries, Idempotent Order Processing, Optimistic Locking (`@Version`), and Pessimistic Locking for inventory adjustments.

---

## 🛠️ Local Installation & Setup

Follow these steps to configure and boot the application locally on your machine:

### 1. Prerequisites
Ensure you have the following installed:
* **Java 21 JDK** or higher.
* **Maven 3.9+**
* **MySQL Server** running locally on port `3306`.

### 2. Configure Environment Variables
Create a file named `.env` in the **root directory** of the project (next to `pom.xml`) and add your local credentials:
```env
DB_URL=jdbc:mysql://localhost:3306/fcommerce_db
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_32_byte_long_secure_development_jwt_secret_key
```
*(Note: The `.env` file is explicitly ignored by git via `.gitignore` to keep credentials secure).*

### 3. Initialize the Database
Open your MySQL terminal and create the application database schema:
```sql
CREATE DATABASE fcommerce_db;
```

### 4. Build and Run the Application
Execute the standard Maven command in your terminal:
```bash
mvn spring-boot:run
```
The application will boot up natively on port **`8081`**.

---

## 📘 Interactive API Documentation

Once the server is running locally, you can view, interact with, and test all backend endpoints via the built-in Swagger UI portal:

🔗 **Local Swagger Link**: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

## ⚡ Quick-Start API Examples

### 1. User Registration (`POST /api/v1/auth/register`)
```bash
curl -X POST http://localhost:8081/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "SecurePassword123"
  }'
```

### 2. User Login (`POST /api/v1/auth/login`)
```bash
curl -X POST http://localhost:8081/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "SecurePassword123"
  }'
```
*Response returns a JWT Bearer Token to authenticate subsequent requests.*
