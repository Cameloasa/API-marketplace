package se.lexicon.g49marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


    // Find advertisements by user that are not expired
    List<Advertisement> findByUserEmailAndExpirationDateAfter(String email, LocalDateTime now);

    // Find all advertisements that are not expired
    List<Advertisement> findByExpirationDateAfter(LocalDateTime now);

    // Find all expired advertisements
    List<Advertisement> findByExpirationDateBefore(LocalDateTime now);

    @Query("select a from Advertisement a where a.expirationDate between :from and :to")
    List<Advertisement> selectAdvertisementBetweenDates(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}