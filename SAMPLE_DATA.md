# 📊 Sample Data for Testing

Use this data to quickly populate your database for testing.

## 🚌 Sample Buses

### Bus 1: Volvo Multi-Axle (AC Sleeper)

```json
POST http://localhost:8080/api/buses

{
  "busNumber": "MH12AB1234",
  "busName": "Volvo Multi-Axle",
  "busType": "AC Sleeper",
  "operatorName": "RedBus Travels",
  "totalSeats": 40,
  "amenities": ["WiFi", "Charging Point", "Water Bottle", "Blanket", "Reading Light"],
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

### Bus 2: Mercedes Benz (AC Seater)

```json
{
  "busNumber": "DL01CD5678",
  "busName": "Mercedes Benz",
  "busType": "AC Seater",
  "operatorName": "VRL Travels",
  "totalSeats": 45,
  "amenities": ["WiFi", "Charging Point", "Water Bottle", "Snacks"],
  "imageUrl": "https://images.unsplash.com/photo-1570125909232-eb263c188f7e",
  "rows": 15,
  "seatsPerRow": 3,
  "isActive": true,
  "seatLayout": {
    "type": "2x3",
    "unavailableSeats": []
  }
}
```

### Bus 3: Scania (Non-AC Sleeper)

```json
{
  "busNumber": "KA05EF9012",
  "busName": "Scania Metrolink",
  "busType": "Non-AC Sleeper",
  "operatorName": "SRS Travels",
  "totalSeats": 36,
  "amenities": ["Charging Point", "Water Bottle", "Pillow"],
  "imageUrl": "https://images.unsplash.com/photo-1544620347-c4fd4a3d5957",
  "rows": 12,
  "seatsPerRow": 3,
  "isActive": true,
  "seatLayout": {
    "type": "2x1",
    "unavailableSeats": []
  }
}
```

## 🛣️ Sample Routes

**Note:** Replace `BUS_ID_HERE` with actual bus ID from previous step.

### Route 1: Mumbai → Pune

```json
POST http://localhost:8080/api/routes

{
  "busId": "BUS_ID_HERE",
  "fromCity": "Mumbai",
  "toCity": "Pune",
  "departureTime": "22:00",
  "arrivalTime": "04:00",
  "duration": "6h 00m",
  "distance": 150,
  "baseFare": 500,
  "boardingPoints": ["Dadar", "Thane", "Kalyan", "Panvel"],
  "droppingPoints": ["Wakad", "Hinjewadi", "Shivaji Nagar", "Swargate"],
  "daysOfOperation": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  "isActive": true
}
```

### Route 2: Delhi → Jaipur

```json
{
  "busId": "BUS_ID_HERE",
  "fromCity": "Delhi",
  "toCity": "Jaipur",
  "departureTime": "23:00",
  "arrivalTime": "05:30",
  "duration": "6h 30m",
  "distance": 280,
  "baseFare": 600,
  "boardingPoints": ["Kashmere Gate", "Anand Vihar", "Ghaziabad"],
  "droppingPoints": ["Sindhi Camp", "Railway Station", "Ajmeri Gate"],
  "daysOfOperation": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  "isActive": true
}
```

### Route 3: Bangalore → Chennai

```json
{
  "busId": "BUS_ID_HERE",
  "fromCity": "Bangalore",
  "toCity": "Chennai",
  "departureTime": "21:30",
  "arrivalTime": "05:00",
  "duration": "7h 30m",
  "distance": 350,
  "baseFare": 700,
  "boardingPoints": ["Majestic", "Electronic City", "Hosur"],
  "droppingPoints": ["Koyambedu", "T Nagar", "Adyar"],
  "daysOfOperation": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  "isActive": true
}
```

### Route 4: Hyderabad → Vijayawada

```json
{
  "busId": "BUS_ID_HERE",
  "fromCity": "Hyderabad",
  "toCity": "Vijayawada",
  "departureTime": "20:00",
  "arrivalTime": "04:30",
  "duration": "8h 30m",
  "distance": 275,
  "baseFare": 550,
  "boardingPoints": ["KPHB", "Kukatpally", "Mehdipatnam"],
  "droppingPoints": ["Benz Circle", "Governorpet", "Patamata"],
  "daysOfOperation": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
  "isActive": true
}
```

## 👤 Sample User

```json
POST http://localhost:8080/api/users

{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "+919876543210",
  "password": "password123",
  "role": "USER",
  "isActive": true
}
```

## 🎫 Sample Booking

**Note:** Replace IDs with actual values.

```json
POST http://localhost:8080/api/bookings

{
  "userId": "USER_ID_HERE",
  "busId": "BUS_ID_HERE",
  "routeId": "ROUTE_ID_HERE",
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
  "seatNumbers": ["1A", "1B"],
  "numberOfSeats": 2,
  "totalFare": 1000,
  "paymentStatus": "COMPLETED",
  "paymentMethod": "STRIPE"
}
```

## 🔄 Quick Setup Script

Create file `setup-data.sh`:

```bash
#!/bin/bash

# Create Bus
BUS_RESPONSE=$(curl -s -X POST http://localhost:8080/api/buses \
  -H "Content-Type: application/json" \
  -d '{
    "busNumber": "MH12AB1234",
    "busName": "Volvo Multi-Axle",
    "busType": "AC Sleeper",
    "operatorName": "RedBus Travels",
    "totalSeats": 40,
    "amenities": ["WiFi", "Charging Point"],
    "rows": 10,
    "seatsPerRow": 4,
    "isActive": true,
    "seatLayout": {"type": "2x2", "unavailableSeats": []}
  }')

echo "Bus created!"
echo $BUS_RESPONSE

# Extract bus ID and create route
# (Add route creation here with bus ID)
```

Run: `chmod +x setup-data.sh && ./setup-data.sh`

## ✅ Verification Steps

1. **Check Buses:**
   ```
   GET http://localhost:8080/api/buses
   Should return array with buses
   ```

2. **Check Routes:**
   ```
   GET http://localhost:8080/api/routes
   Should return array with routes
   ```

3. **Search Routes:**
   ```
   GET http://localhost:8080/api/routes/search?fromCity=Mumbai&toCity=Pune
   Should return matching routes
   ```

4. **Frontend Test:**
   - Open http://localhost:3000
   - Search for Mumbai to Pune
   - Should show available buses

## 🎨 Popular Indian Routes to Add

- Mumbai → Pune
- Delhi → Jaipur
- Bangalore → Chennai
- Hyderabad → Vijayawada
- Ahmedabad → Surat
- Kolkata → Siliguri
- Chennai → Coimbatore
- Pune → Goa

## 📝 Notes

- Use realistic departure/arrival times
- Set journey dates in the future
- Keep base fares reasonable (₹400-₹1000)
- Add 3-5 boarding/dropping points per route
- Include popular amenities

---

**Data setup complete! Start booking! 🚌**