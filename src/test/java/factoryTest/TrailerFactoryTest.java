package factoryTest;

import org.junit.jupiter.api.Test;
import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.enums.Status;
import tr.ac.co.domain.enums.TrailerType;
import tr.ac.co.factory.TrailerFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerFactoryTest {

    @Test
    void testCreateValidTrailer() {
        Trailer trailer = TrailerFactory.createTrailer(
                "Utility Trailer 6x4",
                TrailerType.UTILITY,
                "A sturdy trailer",
                BigDecimal.valueOf(250),
                750,
                "6x4",
                null,
                Status.AVAILABLE
        );
        assertNotNull(trailer);
        assertEquals("Utility Trailer 6x4", trailer.getName());
        assertEquals(Status.AVAILABLE, trailer.getStatus());
        assertEquals(BigDecimal.valueOf(250), trailer.getPricePerDay());
        System.out.println(trailer.toString());
    }

    @Test
    void testCreateTrailerInvalidPrice() {
        Trailer trailer = TrailerFactory.createTrailer(
                "Invalid Trailer",
                TrailerType.UTILITY,
                "No price",
                BigDecimal.valueOf(-10),
                500,
                null,
                null,
                Status.AVAILABLE
        );
        assertNull(trailer);
        System.out.println(trailer);
    }

    @Test
    void testCreateAvailableTrailerShortcut() {
        Trailer trailer = TrailerFactory.createAvailableTrailer(
                "Shortcut Trailer",
                TrailerType.CAR_HAULER,
                BigDecimal.valueOf(300),
                1000
        );
        assertNotNull(trailer);
        assertEquals(Status.AVAILABLE, trailer.getStatus());
        System.out.println(trailer.toString());
    }
}
