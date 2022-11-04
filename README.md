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
        "id": "1da48796-d3a6-432a-a3df-ee43c31cb37d",
        "name": "Quarto duplo",
        "description": "Quarto duplo master com chuveiro",
        "amenities": [
            "Ar condicionado",
            "Fogão",
            "Gás"
        ],
        "location": "Rua chimarrão 43"
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
