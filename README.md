# SMS Sender - Full Stack Developer Test

## Overview

This project exposes a REST API that simulates sending SMS messages. Messages exceeding 160 characters are automatically split and appended with a suffix like ` - Part X of Y`. A minimal responsive frontend built with React + TypeScript allows users to test the functionality easily.

---

## Setup Instructions

### Docker (Recommended - Development Mode)

Make sure you have Docker and Docker Compose installed.

```bash
git clone https://github.com/Aconde13/sms-api
cd sms-api
docker-compose up --build
```

- Backend: http://localhost:8080  
- Frontend: http://localhost:5173

Any code change in the frontend reflects instantly. Backend changes require recompile or relaunch with `--build`.

### Manual (Without Docker)

#### Backend (Java 17+)

```bash
cd backend
./gradlew bootRun
```

#### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## Architecture & Design Decisions

### 1. Architecture Decisions

- Layered architecture with clear separation (`controller`, `service`, `validation`, `exception`)
- Manual validation via `SmsValidator` class for custom control and better testability
- Global error handling using `@RestControllerAdvice`
- Used a constant `MAX_SMS_LENGTH` instead of hardcoding values
- Frontend structured with components, services, and pages to mimic scalable applications

### 2. API Design

- RESTful endpoint: `POST /api/sms/send`
- Consistent error response format:

```json
{
  "error": "Phone number must not be empty",
  "timestamp": "...",
  "status": 400
}
```

- No versioning was implemented due to scope, but the structure supports future versioning like `/api/v1/sms/send`

### 3. State Management (Frontend)

- Managed using React's `useState`
- Small application, so no external state management library was needed
- HTTP logic abstracted into a `services` layer for separation of concerns

### 4. Security

- Authentication and authorization not implemented due to project scope

### 5. Scalability & Maintainability

If this were to scale to thousands of users:
- Store SMS records in a database
- Move constants and logic to shared utility modules
- Add rate limiting and request sanitization
- Add integration and controller-level testing
- Add CI/CD pipeline and production-grade Docker configuration

### 6. Time Constraints

- Did not implement production Docker setup
- Skipped integration/e2e testing
- Message splitting does not preserve full words yet; currently, splits may occur in the middle of words

---

## Project Structure

```
root/
├── backend/
│   └── src/main/java/.../controller, service, validation, exception
├── frontend/
│   └── src/components, services, pages
├── docker-compose.yml
└── README.md
```

---

## API Example

**POST /api/sms/send**

```json
{
  "phone": "1234567890",
  "message": "A very long message that needs splitting..."
}
```

**Console Output:**

```
Sending to 1234567890: Part 1 of 3
Sending to 1234567890: Part 2 of 3
Sending to 1234567890: Part 3 of 3
```

**Success Response:**

```json
{
  "message": "Message sent"
}
```

---

## Final Notes

- All business logic is unit tested
- Frontend allows full interaction and reflects backend responses
- Project can be launched entirely with Docker for easy testing
