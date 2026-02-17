package tr.ac.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ac.co.domain.Booking;
import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.BookingStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByTrailer(Trailer trailer);

    List<Booking> findByStatus(BookingStatus status);

    Optional<Booking> findByUserAndTrailerAndStatus(User user, Trailer trailer, BookingStatus status);
}
