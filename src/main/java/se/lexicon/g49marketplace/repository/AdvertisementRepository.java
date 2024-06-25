package se.lexicon.g49marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    // Find all advertisements that are not expired
    List<Advertisement> findByExpirationDateAfter(LocalDateTime now);

    // Find advertisements by category that are not expired
    List<Advertisement> findByCategoryIdAndExpirationDateAfter(Long categoryId, LocalDateTime now);

    // Find advertisements by user that are not expired
    List<Advertisement> findByUserEmailAndExpirationDateAfter(String email, LocalDateTime now);

    // Optional: Find all expired advertisements (useful for cleanup tasks)
    List<Advertisement> findByExpirationDateBefore(LocalDateTime now);
}
