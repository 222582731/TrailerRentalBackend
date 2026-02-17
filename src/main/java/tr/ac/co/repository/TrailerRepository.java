package tr.ac.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ac.co.domain.Trailer;
import tr.ac.co.domain.enums.Status;
import tr.ac.co.domain.enums.TrailerType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {

    Optional<Trailer> findByName(String name);

    List<Trailer> findByType(TrailerType type);

    List<Trailer> findByStatus(Status status);
}
