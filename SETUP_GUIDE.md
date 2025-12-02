# 🚀 Complete Setup Guide - Bus Booking System

## 📋 Prerequisites

### Required Software
- **Java JDK 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **MongoDB 4.4+** - [Download](https://www.mongodb.com/try/download/community)
- **Node.js 14+** - [Download](https://nodejs.org/)
- **Git** - [Download](https://git-scm.com/)

## 🗄️ MongoDB Setup

### Option 1: Local MongoDB

1. **Install MongoDB**
   ```bash
   # Windows: Download installer from mongodb.com
   # Mac: brew install mongodb-community
   # Linux: sudo apt-get install mongodb
   ```

2. **Start MongoDB**
   ```bash
   # Windows: Start MongoDB service from Services
   # Mac/Linux:
   mongod --dbpath /path/to/data/directory
   ```

3. **Verify MongoDB is running**
   ```bash
   mongo
   # Should connect to MongoDB shell
   ```

### Option 2: MongoDB Atlas (Cloud - Recommended)

1. Go to [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
2. Create free account
3. Create new cluster (Free tier available)
4. Click "Connect" → "Connect your application"
5. Copy connection string
6. Update `application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/bus_booking_db
   ```

## 🔧 Backend Setup

### 1. Clone Repository

```bash
git clone https://github.com/sampurnv/bus-booking-backend-springboot.git
cd bus-booking-backend-springboot
```

### 2. Configure Application

Edit `src/main/resources/application.properties`:

```properties
# MongoDB (choose one)
# Local:
spring.data.mongodb.uri=mongodb://localhost:27017/bus_booking_db

# Cloud (Atlas):
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/bus_booking_db

# Email (Optional - for notifications)
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

# Payment (Optional - for testing)
stripe.api.key=sk_test_your_key
razorpay.key.id=your_key_id
```

### 3. Build Project

```bash
mvn clean install
```

### 4. Run Application

```bash
mvn spring-boot:run
```

**Backend will start on:** `http://localhost:8080`

### 5. Verify Backend

Open browser: `http://localhost:8080/api/buses`

Should return: `[]` (empty array)

## 💻 Frontend Setup

### 1. Clone Repository

```bash
git clone https://github.com/sampurnv/bus-booking-frontend-react.git
cd bus-booking-frontend-react
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Start Development Server

```bash
npm start
```

**Frontend will open on:** `http://localhost:3000`

## 📊 Add Sample Data

### Using MongoDB Compass (GUI)

1. Download [MongoDB Compass](https://www.mongodb.com/products/compass)
2. Connect to your MongoDB instance
3. Create database: `bus_booking_db`
4. Create collections: `buses`, `routes`, `bookings`, `users`

### Using MongoDB Shell

```bash
mongo
use bus_booking_db

# Insert sample bus
db.buses.insertOne({
  busNumber: "MH12AB1234",
  busName: "Volvo Multi-Axle",
  busType: "AC Sleeper",
  operatorName: "RedBus Travels",
  totalSeats: 40,
  amenities: ["WiFi", "Charging Point", "Water Bottle", "Blanket"],
  imageUrl: "https://images.unsplash.com/photo-1544620347-c4fd4a3d5957",
  rows: 10,
  seatsPerRow: 4,
  isActive: true,
  seatLayout: {
    type: "2x2",
    unavailableSeats: []
  }
})

# Get the bus ID from the response, then insert route
db.routes.insertOne({
  busId: "PASTE_BUS_ID_HERE",
  fromCity: "Mumbai",
  toCity: "Pune",
  departureTime: "22:00",
  arrivalTime: "04:00",
  duration: "6h 00m",
  distance: 150,
  baseFare: 500,
  boardingPoints: ["Dadar", "Thane", "Kalyan"],
  droppingPoints: ["Wakad", "Hinjewadi", "Shivaji Nagar"],
  daysOfOperation: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  isActive: true
})
```

### Using API (Recommended)

Use Postman or curl:

```bash
# Create Bus
curl -X POST http://localhost:8080/api/buses \
  -H "Content-Type: application/json" \
  -d '{
    "busNumber": "MH12AB1234",
    "busName": "Volvo Multi-Axle",
    "busType": "AC Sleeper",
    "operatorName": "RedBus Travels",
    "totalSeats": 40,
    "amenities": ["WiFi", "Charging Point", "Water Bottle"],
    "imageUrl": "https://images.unsplash.com/photo-1544620347-c4fd4a3d5957",
    "rows": 10,
    "seatsPerRow": 4,
    "isActive": true,
    "seatLayout": {
      "type": "2x2",
      "unavailableSeats": []
    }
  }'
```

## 🧪 Testing the Application

### 1. Test Backend APIs

```bash
# Get all buses
curl http://localhost:8080/api/buses

# Search routes
curl "http://localhost:8080/api/routes/search?fromCity=Mumbai&toCity=Pune"
```

### 2. Test Frontend

1. Open `http://localhost:3000`
2. Enter search details:
   - From: Mumbai
   - To: Pune
   - Date: Tomorrow's date
3. Click "Search Buses"
4. Select a bus
5. Choose seats
6. Fill passenger details
7. Complete booking

## 🔐 Email Configuration (Gmail)

### 1. Enable 2-Factor Authentication

1. Go to Google Account settings
2. Enable 2-Step Verification

### 2. Generate App Password

1. Go to [App Passwords](https://myaccount.google.com/apppasswords)
2. Select "Mail" and "Other"
3. Copy the 16-character password
4. Update `application.properties`:
   ```properties
   spring.mail.username=your-email@gmail.com
   spring.mail.password=xxxx xxxx xxxx xxxx
   ```

## 💳 Payment Gateway Setup

### Stripe (Test Mode)

1. Sign up at [Stripe](https://stripe.com)
2. Get test API keys from Dashboard
3. Update `application.properties`:
   ```properties
   stripe.api.key=sk_test_...
   ```

**Test Card:** 4242 4242 4242 4242

### Razorpay (Test Mode)

1. Sign up at [Razorpay](https://razorpay.com)
2. Get test API keys
3. Update `application.properties`:
   ```properties
   razorpay.key.id=rzp_test_...
   razorpay.key.secret=...
   ```

## 🐛 Troubleshooting

### Backend Issues

**MongoDB Connection Failed**
```
Solution: 
- Check MongoDB is running: mongod --version
- Verify connection string in application.properties
- Check firewall settings
```

**Port 8080 already in use**
```
Solution:
- Change port in application.properties: server.port=8081
- Kill process using port: lsof -ti:8080 | xargs kill
```

**Build Failed**
```
Solution:
- Check Java version: java -version (should be 17+)
- Clean and rebuild: mvn clean install -U
```

### Frontend Issues

**npm install fails**
```
Solution:
- Clear npm cache: npm cache clean --force
- Delete node_modules and package-lock.json
- Run npm install again
```

**API calls failing**
```
Solution:
- Verify backend is running on port 8080
- Check browser console for CORS errors
- Verify API_BASE_URL in src/services/api.js
```

**Blank page**
```
Solution:
- Check browser console for errors
- Verify all dependencies installed
- Clear browser cache
```

## 📱 Running Both Together

### Terminal 1 (Backend)
```bash
cd bus-booking-backend-springboot
mvn spring-boot:run
```

### Terminal 2 (Frontend)
```bash
cd bus-booking-frontend-react
npm start
```

## 🎯 Quick Test Workflow

1. **Start Backend** → Wait for "Started BusBookingApplication"
2. **Start Frontend** → Opens browser automatically
3. **Add Sample Data** → Use Admin Dashboard or API
4. **Search Buses** → Enter Mumbai to Pune
5. **Select Seats** → Choose seats from layout
6. **Complete Booking** → Fill details and pay
7. **View Bookings** → Check "My Bookings"

## 📚 Additional Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [MongoDB Docs](https://docs.mongodb.com/)
- [React Docs](https://react.dev/)

## 🆘 Need Help?

1. Check this guide thoroughly
2. Review README.md files
3. Check browser/terminal console for errors
4. Verify all prerequisites are installed

## ✅ Success Checklist

- [ ] Java 17+ installed
- [ ] Maven installed
- [ ] MongoDB running
- [ ] Node.js installed
- [ ] Backend cloned and configured
- [ ] Frontend cloned
- [ ] Backend running on port 8080
- [ ] Frontend running on port 3000
- [ ] Sample data added
- [ ] Can search and book buses

---

**You're all set! Happy Booking! 🚌**