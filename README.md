# 🛒 F-Commerce Backend (Spring Boot)

A scalable and secure backend system for an e-commerce platform built using Java 21 and Spring Boot.  
The project demonstrates real-world backend engineering concepts including authentication, database design, transactions, concurrency control, and system design patterns.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- JWT Authentication
- MySQL
- Maven
- Swagger (API Documentation)
- Lombok

---

## 🧩 Features

### 👤 User Management
- User registration & login
- Role-based access control (ADMIN / USER)
- BCrypt password encryption

### 🔐 Security
- JWT-based stateless authentication
- Spring Security filter chain
- Secure endpoints based on roles

### 🛍️ E-Commerce Core Modules
- Product & Category management
- Cart & Cart Items
- Order & Order Items
- Inventory management

### ⚙️ Advanced Backend Concepts
- DTO-based request/response design
- Global exception handling (`@ControllerAdvice`)
- Input validation
- Pagination, sorting, and filtering
- Dynamic search using Specifications

### 🧠 System Design Concepts
- Transaction management using `@Transactional`
- Idempotency to prevent duplicate order placement
- Optimistic locking (`@Version`)
- Pessimistic locking for inventory updates
- Handling concurrency issues

### 📊 Database Optimization
- Indexing on frequently queried fields (email, SKU, order status)
- N+1 problem optimization using JPQL JOIN FETCH

### 📘 API Documentation
- Swagger/OpenAPI integration for API testing and documentation

---

## 🏗️ Architecture

Layered architecture is used
