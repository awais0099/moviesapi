# 🎬 Movies API (Spring Boot)

A secure and scalable RESTful API for managing movie data, built using **Java and Spring Boot**. This project demonstrates backend development best practices including authentication, API design, validation, and real-world features like OTP-based verification and file handling.

---

## 📌 Overview

Movies API allows users to manage movie records with secure access control. It includes authentication, pagination, file uploads, and email-based verification flows.

This project showcases:
- Secure API development with JWT
- Clean architecture (Controller → Service → Repository)
- Real-world backend features (OTP, password reset, file handling)

---

## ✨ Key Features

- 🔐 JWT-based authentication & authorization  
- 📧 Email-based OTP verification (registration & password reset)  
- 🎬 CRUD operations for movies  
- 🔄 Pagination and sorting  
- 📁 File upload and download support  
- ❌ Global exception handling (`@ControllerAdvice`)  
- 📄 Swagger/OpenAPI documentation  
- 🧱 Clean layered architecture with DTOs  

---

## ⚙️ Tech Stack

- **Backend:** Java 17, Spring Boot  
- **Security:** Spring Security, JWT  
- **Database:** MySQL  
- **Persistence:** Spring Data JPA  
- **Documentation:** Swagger / OpenAPI  
- **Email Service:** JavaMailSender  

---

## 🔗 API Endpoints (Sample)

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | /auth/login | User login |
| POST | /auth/register | User registration |
| GET | /movies | Get all movies (paginated) |
| POST | /movies | Add new movie |
| PUT | /movies/{id} | Update movie |
| DELETE | /movies/{id} | Delete movie |

---

## 🔄 API Flow

1. User registers and verifies via OTP  
2. User logs in → receives JWT token  
3. Token is used to access protected endpoints  
4. Movies API handles CRUD operations securely  

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven

### Setup

```bash
git clone https://github.com/awais0099/moviesapi
cd moviesapi
