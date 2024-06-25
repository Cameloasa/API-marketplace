package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.repository.AdvertisementRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Advertisement createAdvertisement(Advertisement ad) {
        return advertisementRepository.save(ad);
    }

    public List<Advertisement> getActiveAdvertisements() {
        return advertisementRepository.findAllByExpirationDateAfter(LocalDate.now());
    }
}
