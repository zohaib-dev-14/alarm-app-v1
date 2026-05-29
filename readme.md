# ⏰ ReveilleX — Smart Alarm Backend

A production-oriented backend system for smart alarm management built using Spring Boot, Spring Security, JWT Authentication, Redis, PostgreSQL, Docker, and Nginx.

The application provides secure authentication, email OTP verification, refresh token rotation, alarm CRUD operations, challenge-based alarm dismissal, API documentation, and containerized deployment.

---

# 🚀 Features

## Authentication & Security

* JWT Authentication
* Refresh Token Rotation
* Spring Security
* BCrypt Password Encryption
* Role-Based Authorization
* Secure Protected APIs

## Email OTP Verification

* Registration OTP Verification
* Forgot Password OTP Verification
* OTP Resend Functionality
* OTP Expiration Handling
* Redis-Based OTP Storage

## Alarm Management

* Create Alarm
* View Alarms
* Update Alarm
* Delete Alarm
* Enable / Disable Alarm
* Challenge-Based Alarm Dismissal

## Challenge System

* Math Challenge Generation
* Math Challenge Verification
* QR Challenge Generation
* QR Challenge Verification

## API Documentation

* Swagger UI
* OpenAPI 3 Documentation

## Infrastructure

* Dockerized Application
* Docker Compose
* PostgreSQL Database
* Redis Cache
* Nginx Reverse Proxy

---

# 🛠️ Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

## Authentication

* JWT
* Refresh Tokens
* BCrypt

## Database & Cache

* PostgreSQL
* Redis

## Email Service

* SendGrid

## DevOps

* Docker
* Docker Compose
* Nginx

## Documentation

* Swagger UI
* OpenAPI 3

## Build Tool

* Maven

---

# 🏗️ System Architecture

```text
Flutter Mobile Application
            │
            ▼
     Nginx Reverse Proxy
            │
            ▼
      Spring Boot API
            │
      ┌─────┴─────┐
      ▼           ▼
 PostgreSQL     Redis
      │           │
      └─────┬─────┘
            ▼
        SendGrid
```

---

# 🔄 Authentication Workflow

```text
Register User
      │
      ▼
Send Email OTP
      │
      ▼
Verify OTP
      │
      ▼
Login
      │
      ▼
JWT Access Token
      │
      ▼
Protected APIs
      │
      ▼
Refresh Token Rotation
```

---

# ⏰ Alarm Workflow

```text
Create Alarm
      │
      ▼
Alarm Triggers
      │
      ▼
Challenge Generated
      │
 ┌────┴─────┐
 ▼          ▼
Math       QR
Challenge Challenge
 ▼          ▼
Verify Challenge
      │
      ▼
Alarm Dismissed
```

---

# 📁 Project Structure

```text
src/main/java

├── config
├── controller
├── dto
├── entities
├── enums
├── exception
├── filter
├── helper
├── principal
├── repositories
├── response
├── service
├── util
└── ReveilleXAlarmApplication
```

---

# 🔐 Authentication APIs

| Method | Endpoint                         |
| ------ | -------------------------------- |
| POST   | /api/v1/auth/register            |
| POST   | /api/v1/auth/verify-registration |
| POST   | /api/v1/auth/login               |
| POST   | /api/v1/auth/refresh             |
| POST   | /api/v1/auth/logout              |
| POST   | /api/v1/auth/reset-password      |

---

# 📧 OTP APIs

| Method | Endpoint                            |
| ------ | ----------------------------------- |
| POST   | /api/v1/otp/forgot-password         |
| POST   | /api/v1/otp/verify-forgot-password  |
| POST   | /api/v1/otp/resend-registration-otp |
| POST   | /api/v1/otp/resend-forgot-password  |

---

# ⏰ Alarm APIs

| Method | Endpoint                   |
| ------ | -------------------------- |
| POST   | /api/v1/alarms             |
| GET    | /api/v1/alarms             |
| PUT    | /api/v1/alarms/{id}        |
| DELETE | /api/v1/alarms/{id}        |
| PATCH  | /api/v1/alarms/{id}/toggle |

---

# 🧮 Challenge APIs

## Math Challenge

| Method | Endpoint                          |
| ------ | --------------------------------- |
| GET    | /api/v1/challenges/math/{alarmId} |
| POST   | /api/v1/challenges/math/verify    |

## QR Challenge

| Method | Endpoint                        |
| ------ | ------------------------------- |
| GET    | /api/v1/challenges/qr/{alarmId} |
| POST   | /api/v1/challenges/qr/verify    |

---

# 📸 API Documentation Screenshots

## Authentication APIs

![Register](docs/screenshots/auth_register.png)

![Verify Registration](docs/screenshots/auth_verify_registration.png)

![Login](docs/screenshots/auth_login.png)

![Refresh Token](docs/screenshots/auth_refresh.png)

![Logout](docs/screenshots/auth_logout.png)

![Reset Password](docs/screenshots/auth_reset_password.png)

---

## OTP APIs

![Forgot Password OTP](docs/screenshots/forgot_password_otp.png)

![Verify Forgot Password OTP](docs/screenshots/verify_forgot_password_otp.png)

![Resend Registration OTP](docs/screenshots/resend_registration_otp.png)

![Resend Forgot Password OTP](docs/screenshots/resend_forgot_password_otp.png)

---

## Alarm APIs

![Create Alarm](docs/screenshots/create_alarm.png)

![Get Alarms](docs/screenshots/get_alarms.png)

![Update Alarm](docs/screenshots/update_alarm.png)

![Delete Alarm](docs/screenshots/delete_alarm.png)

![Toggle Alarm](docs/screenshots/toggle_alarm.png)

---

## Challenge APIs

![Math Challenge](docs/screenshots/math_challenge.png)

![Verify Math Challenge](docs/screenshots/math_verify.png)

![QR Challenge](docs/screenshots/qr_challenge.png)

![Verify QR Challenge](docs/screenshots/qr_verify.png)

---

# 🐳 Docker Deployment

The application runs through Docker Compose using multiple containers:

* Spring Boot Application
* PostgreSQL Database
* Redis Cache
* Nginx Reverse Proxy

```bash
docker compose up -d
```

---

# ⚙️ Local Setup

## Clone Repository

```bash
git clone https://github.com/yourusername/reveillex-backend.git
```

## Enter Project

```bash
cd reveillex-backend
```

## Run Docker

```bash
docker compose up -d
```

## Run Application

```bash
mvn spring-boot:run
```

---

# 📖 Swagger Documentation

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🚀 Future Improvements

* Mobile Push Notifications
* Kafka Event Streaming
* Kubernetes Deployment
* GitHub Actions CI/CD
* Monitoring & Logging
* Multi-Device Alarm Synchronization

---

# 👨‍💻 Author

**Muhammad Zohaib**

Backend Engineer | Spring Boot | Security | Cloud & DevOps

GitHub:
https://github.com/zohaib-dev-14
