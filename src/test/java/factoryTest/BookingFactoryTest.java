package factoryTest;

import org.junit.jupiter.api.Test;
import tr.ac.co.domain.Booking;
import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.BookingStatus;
import tr.ac.co.domain.enums.Role;
import tr.ac.co.domain.enums.TrailerType;
import tr.ac.co.factory.BookingFactory;
import tr.ac.co.factory.TrailerFactory;
import tr.ac.co.factory.UserFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingFactoryTest {

    @Test
    void testCreateValidBooking() {
        User user = UserFactory.createUser("Lihle", "Dambuza", "l.dambu@example.com",
                "Password123!", Role.CUSTOMER, LocalDateTime.now());
        Trailer trailer = TrailerFactory.createAvailableTrailer("Utility Trailer",
                TrailerType.UTILITY, BigDecimal.valueOf(200), 500);
        Booking booking = BookingFactory.createBooking(user, trailer,
                LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 3),
                BigDecimal.valueOf(600), BookingStatus.PENDING);

        assertNotNull(booking);
        assertEquals(user, booking.getUser());
        assertEquals(trailer, booking.getTrailer());
        assertEquals(BookingStatus.PENDING, booking.getStatus());
        System.out.println(user.toString());
        System.out.println(trailer.toString());
        System.out.println(booking.toString());
    }

    @Test
    void testCreateBookingInvalidDates() {
        User user = UserFactory.createUser("Yanga", "Jilaji", "jyanga@gmail.com",
                "Password1234", Role.CUSTOMER, LocalDateTime.now());
        Trailer trailer = TrailerFactory.createAvailableTrailer("Utility Trailer", TrailerType.UTILITY,
                BigDecimal.valueOf(200), 500);

        Booking booking = BookingFactory.createBooking(user, trailer, LocalDate.of(2026, 3,
                5), LocalDate.of(2026, 3, 3), BigDecimal.valueOf(600), BookingStatus.PENDING);
        assertNull(booking);
        System.out.println(user.toString());
        System.out.println(trailer.toString());
        System.out.println(booking.toString());
    }

    @Test
    void testCreateBookingMissingFields() {
        Booking booking = BookingFactory.createBooking(null, null, null, null, null, null);
        assertNull(booking);
        System.out.println(booking.toString());
    }
}
