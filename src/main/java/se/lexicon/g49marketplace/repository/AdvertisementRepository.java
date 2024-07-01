package se.lexicon.g49marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {


    //@Query("SELECT a FROM Advertisement a WHERE a.user.email = :email")
    List<Advertisement> findByUser_Email(String email);

    List<Advertisement> findAllByExpirationDateAfter(LocalDate now);

    List<Advertisement> findByExpirationDateBefore(LocalDate now);

    @Query("SELECT a FROM Advertisement a WHERE a.user.email = :email AND a.expirationDate > :now")
    List<Advertisement> findAdvertisementsByUserEmailAndExpirationDateIsAfter(@Param("email") String email, @Param("now") LocalDate now);

    List<Advertisement> findByCreationDateBetween(LocalDate from, LocalDate to);

    @Query("SELECT a FROM Advertisement a WHERE a.creationDate BETWEEN :from AND :to AND a.expirationDate BETWEEN :from AND :to")
    List<Advertisement> selectAdvertisementBetweenDates(@Param("from") LocalDate from, @Param("to") LocalDate to);
}