# Hotel_booking

**Version 0.0.1-SNAPSHOT**

#### getAllByAvailable
When viewing available numbers for the specified dates, the following logic applies:
- When all parameters are not null we have standard logic
- When startDate == null and endDate != null we have the next logic: startDate = endDate.minusDays(1).
That is, we will get those rooms that are not booked on the previous day from the specified end date.
- When endDate == null and startDate != null we have the next logic: endDate = startDate.plusDays(1).
- When no parameters is specified: startDate = LocalDate.now() and endDate = startDate.plusDays(1).


