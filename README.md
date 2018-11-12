# REST application Hotel booking

**Version 1.0-SNAPSHOT**

Tech stack: Java 8, Spring MVC, Spring Data, Hibernate, H2, maven.

With capabilities (each represented by separate endpoint):
1. View list of available rooms (room have a number, category, price, additional options like breakfast, cleaning with additional cost) for specified dates.
URL: /rest/profile/room/available
2. View rooms filtered by category. 
URL: /rest/profile/room/category/{category}
3. Create user.
URL: /rest/profile/register
4. User can book the room for specified days.
URL: /rest/profile/booking/{roomId}
5. User can view his booking.
URL: /rest/profile/booking/all-my
6. User can get the total price of the booking (room for dates period + cost of additional options).
URL: /rest/profile/booking/{id}/price
7. View all bookings for the hotel.
URL: /rest/profile/booking/all

Added authorization authentication and authorization.

#### getAllByAvailable
When viewing available numbers for the specified dates, the following logic applies:
- When all parameters are not null we have standard logic
- When startDate == null and endDate != null we have the next logic: startDate = endDate.minusDays(1).
That is, we will get those rooms that are not booked on the previous day from the specified end date.
- When endDate == null and startDate != null we have the next logic: endDate = startDate.plusDays(1).
- When no parameters is specified: startDate = LocalDate.now() and endDate = startDate.plusDays(1).


