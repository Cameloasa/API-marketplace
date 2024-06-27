package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g49marketplace.converter.AdvertisementConverter;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {


    private AdvertisementRepository advertisementRepository;
    private AdvertisementConverter advertisementConverter;
    private UserRepository userRepository;
    private UserConverter userConverter;


    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, AdvertisementConverter advertisementConverter, UserRepository userRepository, UserConverter userConverter) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementConverter = advertisementConverter;
        this.userRepository = userRepository;
        this.userConverter = userConverter;

    }

    @Override
    @Transactional
    public AdvertisementDTOView createAdvertisement(AdvertisementDTOForm dtoForm) {
        //check if advertisement is in the database
        if(dtoForm == null) throw new IllegalArgumentException("AdvertisementDTOForm is null");
        //Check if the advertisement exist in the database, if exist already throw a data duplicate exception
        boolean advertisementExists = advertisementRepository.existsById(dtoForm.getId());
        if (advertisementExists)
            throw new DataDuplicateException("Advertisement already exists");

        //Convert AdvertisementDTOForm to AdvertisementEntity using converter
        Advertisement advertisement = advertisementConverter.toEntity(dtoForm);

        String userEmail = dtoForm.getUserEmail().getEmail();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + userEmail));
        advertisement.addUser(user);

        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

        UserDTOView userDTOView = userConverter.toDTO(user);

        return AdvertisementDTOView.builder()
                .id(savedAdvertisement.getId())
                .title(savedAdvertisement.getTitle())
                .description(savedAdvertisement.getDescription())
                .creationDate(savedAdvertisement.getCreationDate())
                .expirationDate(savedAdvertisement.getExpirationDate())
                .active(!savedAdvertisement.isExpired())
                .user(user)
                .build();
        
    }

    @Override
    @Transactional
    public AdvertisementDTOView addAdvertisementToUser(String email, AdvertisementDTOForm dtoForm) {
       User user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found"));
       Advertisement advertisement = advertisementConverter.toEntity(dtoForm);
       advertisement.setUser(user);
       advertisementRepository.save(advertisement);

        return advertisementConverter.toDTO(advertisement);
    }

    @Override
    public List<AdvertisementDTOView> getActiveAdvertisements() {
        return advertisementRepository.findAll().stream()
                .filter(advertisement -> !advertisement.isExpired())
                .map(advertisementConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAdvertisementAfterExpirationDate() {
            List<Advertisement> expiredAds = advertisementRepository.findAll().stream()
                    .filter(Advertisement::isExpired)
                    .collect(Collectors.toList());

            if (expiredAds.isEmpty()) return false;

            advertisementRepository.deleteAll(expiredAds);
            return true;
    }
}
