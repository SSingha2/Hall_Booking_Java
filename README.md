# Convention Hall Booking System

Welcome to the **Convention Hall Booking System**! This project is a full-stack web application designed to facilitate the booking and management of convention halls. It provides two types of user roles—**Hall Owners** and **Clients**—with tailored functionalities for each role.

---

## **Features**

### **Hall Owners (Company Users):**
- Post and manage convention halls, including details such as availability, pricing, and location.
- View bookings made by clients.

### **Clients:**
- Search for available convention halls.
- Book a hall for a specific date and time.

### **Authentication and Authorization:**
- Secure login and signup system.
- Role-based access control (Admin, Hall Owners, Clients).

### **Technology Stack:**
- **Frontend:** Angular.
- **Backend:** Spring Boot (Java).
- **Database:** MySQL.

---

## **Project Structure**

The repository is organized into two main folders:

### 1. **Frontend**
- Contains the Angular application.
- Responsible for the user interface and client-side logic.

#### Commands to Run:
```bash
cd frontend
npm install
ng serve
```

### 2. **Backend**
- Built with Spring Boot.
- Handles business logic, authentication, and API endpoints.

#### Commands to Run:
```bash
cd backend
./mvnw spring-boot:run
```

---

## **Setup Instructions**

### Prerequisites:
- **Node.js** and **npm** (for Angular frontend).
- **Java 11+** and **Maven** (for Spring Boot backend).
- **Docker** (for containerized deployment).
- **MySQL** (or Dockerized MySQL instance).

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/SSingha2/Hall_Booking_Java.git
   cd Hall_Booking_Java
   ```
2. Set up the MySQL database:
   - Create a new database named `hall_booking`.
   - Update database credentials in `backend/src/main/resources/application.properties`.
3. Start the backend and frontend as explained above.

