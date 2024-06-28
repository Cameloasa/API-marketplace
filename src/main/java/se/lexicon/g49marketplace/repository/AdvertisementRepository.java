package se.lexicon.g49marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    // Using method query creation
    List<Advertisement> findByUserEmailAndExpirationDateAfter(String email, LocalDate now);

    @Query("SELECT a FROM Advertisement a WHERE a.user.email = :email")
    List<Advertisement> findByUserEmail(@Param("email") String email);
    // Find all advertisements that are not expired
    List<Advertisement> findByExpirationDateAfter(LocalDate now);

    // Find all expired advertisements
    List<Advertisement> findByExpirationDateBefore(LocalDate now);

    // Find all expired advertisements
    List<Advertisement> findAllByExpirationDateBefore(LocalDate expirationDate);

    List<Advertisement> findByCreationDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT a FROM Advertisement a WHERE a.user.email = :email AND a.expirationDate > :now")
    List<Advertisement> findAdvertisementsByUserEmailAndExpirationDateAfter(@Param("email") String email, @Param("now") LocalDate now);


    @Query("select a from Advertisement a where a.creationDate between :from and :to and a.expirationDate between :from and :to")
    List<Advertisement> selectAdvertisementBetweenDates(@Param("from") LocalDate from, @Param("to") LocalDate to);
}