# 🚌 Bus Booking System - Backend

Complete bus booking system backend built with **Spring Boot** and **MongoDB**.

## ✨ Features

### Core Features
- 🚌 **Bus Management** - CRUD operations for buses
- 🛣️ **Route Management** - Manage routes with boarding/dropping points
- 🎫 **Booking System** - Complete booking lifecycle
- 💺 **Seat Selection** - Real-time seat availability
- 💳 **Payment Integration** - Stripe & Razorpay support
- 📧 **Email Notifications** - Booking confirmations & cancellations
- 👤 **User Management** - User registration and profiles

### Advanced Features
- Real-time seat availability checking
- Automatic booking number generation
- Email notifications for bookings
- Payment gateway integration
- Booking cancellation with refunds
- Admin dashboard support

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **MongoDB** - NoSQL Database
- **Spring Data MongoDB**
- **Spring Mail** - Email notifications
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

## 📋 Prerequisites

- JDK 17 or higher
- Maven 3.6+
- MongoDB 4.4+ (running locally or cloud)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/sampurnv/bus-booking-backend-springboot.git
cd bus-booking-backend-springboot
```

### 2. Configure MongoDB

Update `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/bus_booking_db
```

For MongoDB Atlas (Cloud):
```properties
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/bus_booking_db
```

### 3. Configure Email (Optional)

For Gmail:
```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 4. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

Server starts on: **http://localhost:8080**

## 📚 API Endpoints

### Bus Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/buses` | Get all buses |
| GET | `/api/buses/{id}` | Get bus by ID |
| GET | `/api/buses/active` | Get active buses |
| GET | `/api/buses/type/{type}` | Get buses by type |
| POST | `/api/buses` | Create new bus |
| PUT | `/api/buses/{id}` | Update bus |
| DELETE | `/api/buses/{id}` | Delete bus |

### Route Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/routes` | Get all routes |
| GET | `/api/routes/{id}` | Get route by ID |
| GET | `/api/routes/search?fromCity=X&toCity=Y` | Search routes |
| GET | `/api/routes/bus/{busId}` | Get routes by bus |
| POST | `/api/routes` | Create new route |
| PUT | `/api/routes/{id}` | Update route |
| DELETE | `/api/routes/{id}` | Delete route |

### Booking Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/bookings` | Get all bookings |
| GET | `/api/bookings/{id}` | Get booking by ID |
| GET | `/api/bookings/number/{bookingNumber}` | Get booking by number |
| GET | `/api/bookings/user/{userId}` | Get user bookings |
| GET | `/api/bookings/booked-seats?busId=X&routeId=Y&journeyDate=Z` | Get booked seats |
| POST | `/api/bookings` | Create new booking |
| PUT | `/api/bookings/{id}` | Update booking |
| PUT | `/api/bookings/{id}/cancel` | Cancel booking |

### Payment

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/payments/process` | Process payment |

## 📦 Sample Data

### Create a Bus

```json
POST /api/buses
{
  "busNumber": "MH12AB1234",
  "busName": "Volvo Multi-Axle",
  "busType": "AC Sleeper",
  "operatorName": "RedBus Travels",
  "totalSeats": 40,
  "amenities": ["WiFi", "Charging Point", "Water Bottle", "Blanket"],
  "imageUrl": "https://example.com/bus.jpg",
  "rows": 10,
  "seatsPerRow": 4,
  "seatLayout": {
    "type": "2x2",
    "unavailableSeats": ["1A", "1B"]
  },
  "isActive": true
}
```

### Create a Route

```json
POST /api/routes
{
  "busId": "bus-id-here",
  "fromCity": "Mumbai",
  "toCity": "Pune",
  "departureTime": "22:00",
  "arrivalTime": "04:00",
  "duration": "6h 00m",
  "distance": 150,
  "baseFare": 500,
  "boardingPoints": ["Dadar", "Thane", "Kalyan"],
  "droppingPoints": ["Wakad", "Hinjewadi", "Shivaji Nagar"],
  "daysOfOperation": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  "isActive": true
}
```

### Create a Booking

```json
POST /api/bookings
{
  "userId": "user-id-here",
  "busId": "bus-id-here",
  "routeId": "route-id-here",
  "passengerDetails": {
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "+919876543210",
    "age": 30,
    "gender": "Male"
  },
  "journeyDate": "2024-12-15",
  "fromCity": "Mumbai",
  "toCity": "Pune",
  "boardingPoint": "Dadar",
  "droppingPoint": "Wakad",
  "seatNumbers": ["A1", "A2"],
  "numberOfSeats": 2,
  "totalFare": 1000,
  "paymentStatus": "PENDING",
  "paymentMethod": "STRIPE"
}
```

## 🗄️ Database Collections

### buses
- Bus information and seat layout

### routes
- Route details with boarding/dropping points

### bookings
- Booking records with passenger details

### users
- User accounts and profiles

## 🔧 Configuration

### Payment Gateways

**Stripe:**
```properties
stripe.api.key=sk_test_your_stripe_secret_key
```

**Razorpay:**
```properties
razorpay.key.id=your_razorpay_key_id
razorpay.key.secret=your_razorpay_key_secret
```

## 📧 Email Templates

The system sends HTML-formatted emails for:
- Booking confirmations
- Booking cancellations
- Refund notifications

## 🧪 Testing

```bash
mvn test
```

## 📝 Project Structure

```
src/main/java/com/busbooking/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── model/          # MongoDB entities
├── repository/     # MongoDB repositories
└── service/        # Business logic
```

## 🚀 Deployment

### Docker

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Build Docker Image

```bash
mvn clean package
docker build -t bus-booking-backend .
docker run -p 8080:8080 bus-booking-backend
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

MIT License

## 👨‍💻 Author

Created with ❤️ by Bhindi Team

## 🔗 Related

- Frontend Repository: [bus-booking-frontend-react](https://github.com/sampurnv/bus-booking-frontend-react)

---

**Happy Coding! 🚌**