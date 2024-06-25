package se.lexicon.g49marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findAllByOrderByIdDesc();
    List<Advertisement> findAllByOrderByIdAsc();
    List<Advertisement> findAllByExpirationDateAfter(LocalDate expirationDate);
}
