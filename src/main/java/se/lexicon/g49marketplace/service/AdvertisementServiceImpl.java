package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g49marketplace.converter.UserConverter;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.exception.DataDuplicateException;
import se.lexicon.g49marketplace.exception.DataNotFoundException;
import se.lexicon.g49marketplace.repository.AdvertisementRepository;
import se.lexicon.g49marketplace.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;

    }

    @Override
    @Transactional
    public AdvertisementDTOView createAdvertisement(AdvertisementDTOForm adDtoForm) {
        //Create a new ad entity using the DTO and builder
        Advertisement advertisement = Advertisement.builder()
                .id(adDtoForm.getId())
                .title(adDtoForm.getTitle())
                .description(adDtoForm.getDescription())
                .creationDate(adDtoForm.getCreationDate())
                .expirationDate(adDtoForm.getExpirationDate())
                .build();
        // Save the created entity to the database
        Advertisement savedAd = advertisementRepository.save(advertisement);
        // Convert the saved entity to a DTOView user using builder
        UserDTOView buildUserDtoView = UserDTOView.builder()
                .email(savedAd.getUser().getEmail())
                .username(savedAd.getUser().getUsername())
                .phoneNumber(savedAd.getUser().getPhoneNumber())
                .build();
        //return adDTO view using builder
        return AdvertisementDTOView.builder()
                .id(savedAd.getId())
                .title(savedAd.getTitle())
                .description(savedAd.getDescription())
                .creationDate(savedAd.getCreationDate())
                .expirationDate(savedAd.getExpirationDate())
                .user(buildUserDtoView)
                .build();
    }

    @Override
    public List<AdvertisementDTOView> findAdvertisementBetweenCreationDateAndExpirationDate(LocalDateTime creationDate, LocalDateTime expirationDate) {
        return List.of();
    }

    @Override
    public Optional<AdvertisementDTOView> findAdvertisementByUserId(String email) {
        return Optional.empty();
    }

    @Override
    public AdvertisementDTOView addAdvertisementToUser(String email, AdvertisementDTOForm adDtoForm) {
        return null;
    }

    @Override
    public List<AdvertisementDTOView> getActiveAdvertisements() {
        return List.of();
    }

    @Override
    public boolean deleteAdvertisementAfterExpirationDate() {
        return false;
    }
}
