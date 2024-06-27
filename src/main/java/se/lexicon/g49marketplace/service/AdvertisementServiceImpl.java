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
        try {
            // Check if advertisementDTOForm is null
            if (dtoForm == null) {
                throw new IllegalArgumentException("AdvertisementDTOForm is null");
            }

            // Check if the advertisement exists in the database
            if (advertisementRepository.existsById(dtoForm.getId())) {
                throw new DataDuplicateException("Advertisement already exists with ID: " + dtoForm.getId());
            }

            // Convert AdvertisementDTOForm to AdvertisementEntity using converter
            Advertisement advertisement = advertisementConverter.toEntity(dtoForm);

            // Find user by email
            String userEmail = dtoForm.getUserEmail();
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new DataNotFoundException("User not found with email: " + userEmail));

            // Set user to advertisement
            advertisement.addUser(user);

            // Save advertisement to the database
            Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

            // Convert savedAdvertisement to AdvertisementDTOView and return
            return advertisementConverter.toDTO(savedAdvertisement);
        } catch (IllegalArgumentException | DataDuplicateException | DataNotFoundException ex) {
            // Log the exception and throw custom error response
            ex.printStackTrace(); // Logging the exception stack trace
            throw new RuntimeException("Failed to create advertisement: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred while creating advertisement");
        }
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
