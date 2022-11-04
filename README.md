# lastcancunhotel

Requirements
Docker to run mongodb
Java Home path set
Deploy instructions

Hotel API

# Room Availability
### Should return all available rooms to book, given the input parameters

Request
GET 
path.to/v1/rooms/available?checkIn=**{yyyy-MM-dd}**&checkOut=**{yyyy-MM-dd}**

Response
```
[
    {
        "id": {id},
        "name": {name},
        "description": {description},
        "amenities": [
            {amenities}
        ],
        "location": {location}
    }
]
```
# New Booking
### Book a stay using the roomId(from the Room Availability endpoint), checkIn and checkOut parameters
POST
path.to/v1/booking

Request Body
```
{
    "roomId": {roomId},
    "checkIn": {checkIn},
    "checkOut": {checkOut}
}
```

Response
```
{
    "id": {id},
    "checkin": {checkIn},
    "checkout": {checkOut},
    "room": {
        "id": {roomId},
        "name": {roomName},
        "description": {roomDescription},
        "amenities": [
            {amenities}
        ],
        "location": {roomLocation}
    }
}
```
# Modify Booking
### Modify an existing booking a stay using the bookingId(generated in the New booking endpoint), checkIn and checkOut parameters
POST
path.to/v1/booking/**{bookingId}**

Request Body
```
{
    "checkIn": {checkIn},
    "checkOut": {checkOut}
}
```

Response
```
{
    "id": {id},
    "checkin": {checkIn},
    "checkout": {checkOut},
    "room": {
        "id": {roomId},
        "name": {roomName},
        "description": {roomDescription},
        "amenities": [
            {amenities}
        ],
        "location": {roomLocation}
    }
}
```
