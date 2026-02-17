package tr.ac.co.factory;

import tr.ac.co.domain.Booking;
import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.BookingStatus;


import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingFactory {

    public static Booking createBooking(User user,
                                        Trailer trailer,
                                        LocalDate startDate,
                                        LocalDate endDate,
                                        BigDecimal totalCost,
                                        BookingStatus status) {

        if (user == null
                || trailer == null
                || startDate == null
                || endDate == null
                || startDate.isAfter(endDate)
                || totalCost == null
                || totalCost.compareTo(BigDecimal.ZERO) <= 0
                || status == null) {
            return null;
        }

        return new Booking.Builder()
                .setUser(user)
                .setTrailer(trailer)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalCost(totalCost)
                .setStatus(status)
                .build();
    }


    public static Booking createPendingBooking(User user,
                                               Trailer trailer,
                                               LocalDate startDate,
                                               LocalDate endDate,
                                               BigDecimal totalCost) {
        //setting default status
        return createBooking(user, trailer, startDate, endDate, totalCost, BookingStatus.PENDING);
    }
}
