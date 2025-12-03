# 📮 Postman Collection Guide

## 🚀 Quick Start

### 1. Import Collection

1. Download `Bus_Booking_API.postman_collection.json`
2. Open Postman
3. Click **Import** button
4. Select the downloaded file
5. Collection will be imported with all endpoints

### 2. Set Environment Variables

Create a new environment in Postman with these variables:

| Variable | Value | Description |
|----------|-------|-------------|
| `base_url` | `http://localhost:8080` | Backend server URL |
| `bus_id` | (empty initially) | Will be set after creating bus |
| `route_id` | (empty initially) | Will be set after creating route |
| `booking_id` | (empty initially) | Will be set after creating booking |
| `booking_number` | (empty initially) | Booking reference number |
| `user_id` | `user123` | Test user ID |

## 📋 Collection Structure

### 1. Buses (7 endpoints)
- Get All Buses
- Get Bus by ID
- Get Active Buses
- Get Buses by Type
- Create Bus
- Update Bus
- Delete Bus

### 2. Routes (7 endpoints)
- Get All Routes
- Get Route by ID
- Search Routes
- Get Routes by Bus
- Create Route
- Update Route
- Delete Route

### 3. Bookings (8 endpoints)
- Get All Bookings
- Get Booking by ID
- Get Booking by Number
- Get User Bookings
- Get Booked Seats
- Create Booking
- Update Booking
- Cancel Booking

### 4. Payments (2 endpoints)
- Process Payment - Stripe
- Process Payment - Razorpay

### 5. Sample Workflows (6 steps)
- Complete booking flow from search to confirmation

## 🎯 Testing Workflow

### Step 1: Create a Bus

**Endpoint:** `POST /api/buses`

**Request Body:**
```json
{
  "busNumber": "MH12AB1234",
  "busName": "Volvo Multi-Axle",
  "busType": "AC Sleeper",
  "operatorName": "RedBus Travels",
  "totalSeats": 40,
  "amenities": ["WiFi", "Charging Point", "Water Bottle", "Blanket"],
  "imageUrl": "https://images.unsplash.com/photo-1544620347-c4fd4a3d5957",
  "rows": 10,
  "seatsPerRow": 4,
  "isActive": true,
  "seatLayout": {
    "type": "2x2",
    "unavailableSeats": []
  }
}
```

**Response:**
```json
{
  "id": "674e1234567890abcdef1234",
  "busNumber": "MH12AB1234",
  "busName": "Volvo Multi-Axle",
  ...
}
```

**Action:** Copy the `id` from response and set it as `bus_id` variable in Postman.

### Step 2: Create a Route

**Endpoint:** `POST /api/routes`

**Request Body:**
```json
{
  "busId": "{{bus_id}}",
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

**Action:** Copy the `id` from response and set it as `route_id` variable.

### Step 3: Search Routes

**Endpoint:** `GET /api/routes/search?fromCity=Mumbai&toCity=Pune`

**Response:**
```json
[
  {
    "id": "674e1234567890abcdef5678",
    "busId": "674e1234567890abcdef1234",
    "fromCity": "Mumbai",
    "toCity": "Pune",
    ...
  }
]
```

### Step 4: Check Seat Availability

**Endpoint:** `GET /api/bookings/booked-seats?busId={{bus_id}}&routeId={{route_id}}&journeyDate=2024-12-15`

**Response:**
```json
["1A", "2B", "3C"]
```

This shows which seats are already booked.

### Step 5: Create Booking

**Endpoint:** `POST /api/bookings`

**Request Body:**
```json
{
  "userId": "user123",
  "busId": "{{bus_id}}",
  "routeId": "{{route_id}}",
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
  "seatNumbers": ["4A", "4B"],
  "numberOfSeats": 2,
  "totalFare": 1000,
  "paymentStatus": "PENDING",
  "paymentMethod": "STRIPE"
}
```

**Response:**
```json
{
  "id": "674e1234567890abcdef9012",
  "bookingNumber": "BUS12AB34CD",
  "status": "CONFIRMED",
  ...
}
```

**Action:** Copy `id` as `booking_id` and `bookingNumber` as `booking_number`.

### Step 6: Process Payment

**Endpoint:** `POST /api/payments/process`

**Request Body:**
```json
{
  "bookingId": "{{booking_id}}",
  "amount": 1000,
  "currency": "INR",
  "paymentMethod": "stripe",
  "cardNumber": "4242424242424242",
  "cardExpiry": "12/25",
  "cardCvv": "123",
  "email": "john@example.com"
}
```

**Response:**
```json
{
  "success": true,
  "paymentId": "stripe_abc123xyz",
  "transactionId": "stripe_abc123xyz",
  "amount": 1000,
  "currency": "INR",
  "status": "COMPLETED",
  "message": "Payment successful via Stripe"
}
```

### Step 7: Get Booking Details

**Endpoint:** `GET /api/bookings/{{booking_id}}`

**Response:**
```json
{
  "id": "674e1234567890abcdef9012",
  "bookingNumber": "BUS12AB34CD",
  "userId": "user123",
  "busId": "674e1234567890abcdef1234",
  "routeId": "674e1234567890abcdef5678",
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
  "seatNumbers": ["4A", "4B"],
  "numberOfSeats": 2,
  "totalFare": 1000,
  "paymentStatus": "COMPLETED",
  "paymentMethod": "STRIPE",
  "paymentId": "stripe_abc123xyz",
  "transactionId": "stripe_abc123xyz",
  "status": "CONFIRMED",
  "bookingDate": "2024-12-03T10:30:00Z"
}
```

## 🧪 Test Scenarios

### Scenario 1: Happy Path (Successful Booking)

1. Create Bus → Get bus_id
2. Create Route → Get route_id
3. Search Routes → Verify route exists
4. Check Seat Availability → See available seats
5. Create Booking → Get booking_id
6. Process Payment → Payment successful
7. Get Booking → Verify status is CONFIRMED

### Scenario 2: Seat Already Booked

1. Create Booking with seats ["1A", "1B"]
2. Try to create another booking with same seats
3. Should get error: "Seat 1A is already booked"

### Scenario 3: Cancel Booking

1. Create Booking
2. Process Payment
3. Cancel Booking → `PUT /api/bookings/{{booking_id}}/cancel`
4. Verify status changed to CANCELLED
5. Verify paymentStatus changed to REFUNDED

### Scenario 4: Get User Bookings

1. Create multiple bookings for same user
2. Get User Bookings → `GET /api/bookings/user/{{user_id}}`
3. Verify all bookings are returned

## 📊 Sample Test Data

### Multiple Buses

```json
// Bus 1: AC Sleeper
{
  "busNumber": "MH12AB1234",
  "busName": "Volvo Multi-Axle",
  "busType": "AC Sleeper",
  "operatorName": "RedBus Travels",
  "totalSeats": 40,
  "rows": 10,
  "seatsPerRow": 4
}

// Bus 2: AC Seater
{
  "busNumber": "DL01CD5678",
  "busName": "Mercedes Benz",
  "busType": "AC Seater",
  "operatorName": "VRL Travels",
  "totalSeats": 45,
  "rows": 15,
  "seatsPerRow": 3
}

// Bus 3: Non-AC Sleeper
{
  "busNumber": "KA05EF9012",
  "busName": "Scania Metrolink",
  "busType": "Non-AC Sleeper",
  "operatorName": "SRS Travels",
  "totalSeats": 36,
  "rows": 12,
  "seatsPerRow": 3
}
```

### Multiple Routes

```json
// Route 1: Mumbai → Pune
{
  "fromCity": "Mumbai",
  "toCity": "Pune",
  "departureTime": "22:00",
  "arrivalTime": "04:00",
  "baseFare": 500
}

// Route 2: Delhi → Jaipur
{
  "fromCity": "Delhi",
  "toCity": "Jaipur",
  "departureTime": "23:00",
  "arrivalTime": "05:30",
  "baseFare": 600
}

// Route 3: Bangalore → Chennai
{
  "fromCity": "Bangalore",
  "toCity": "Chennai",
  "departureTime": "21:30",
  "arrivalTime": "05:00",
  "baseFare": 700
}
```

## 🔍 Response Status Codes

| Code | Meaning | Example |
|------|---------|---------|
| 200 | Success | GET requests successful |
| 201 | Created | POST requests successful |
| 204 | No Content | DELETE requests successful |
| 400 | Bad Request | Invalid data sent |
| 404 | Not Found | Resource doesn't exist |
| 500 | Server Error | Backend error |

## 💡 Tips

### 1. Use Variables
- Set `bus_id`, `route_id`, `booking_id` as variables
- Use `{{variable_name}}` in requests
- Update variables after each creation

### 2. Save Responses
- Use Postman's "Save Response" feature
- Compare responses across tests
- Track changes over time

### 3. Create Test Scripts
Add to Tests tab in Postman:

```javascript
// Save bus_id from response
pm.test("Save bus_id", function() {
    var jsonData = pm.response.json();
    pm.environment.set("bus_id", jsonData.id);
});

// Verify status code
pm.test("Status code is 201", function() {
    pm.response.to.have.status(201);
});

// Verify response structure
pm.test("Response has id", function() {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('id');
});
```

### 4. Use Pre-request Scripts
Generate dynamic data:

```javascript
// Generate random bus number
pm.environment.set("random_bus_number", 
    "MH" + Math.floor(Math.random() * 100) + "AB" + Math.floor(Math.random() * 10000)
);

// Set future date
var tomorrow = new Date();
tomorrow.setDate(tomorrow.getDate() + 1);
pm.environment.set("journey_date", tomorrow.toISOString().split('T')[0]);
```

## 🐛 Troubleshooting

### Issue: Connection Refused
**Solution:** Ensure backend is running on port 8080

### Issue: 404 Not Found
**Solution:** Check if resource exists, verify IDs are correct

### Issue: 400 Bad Request
**Solution:** Validate request body format, check required fields

### Issue: Seat Already Booked
**Solution:** Check booked seats first, select different seats

## 📚 Additional Resources

- [Postman Documentation](https://learning.postman.com/)
- [REST API Best Practices](https://restfulapi.net/)
- Backend README.md for API details

---

**Happy Testing! 🚀**