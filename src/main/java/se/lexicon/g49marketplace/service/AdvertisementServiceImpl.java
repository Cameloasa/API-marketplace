package se.lexicon.g49marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.dto.UserDTOForm;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.domain.entity.User;
import se.lexicon.g49marketplace.exception.DataNotFoundException;
import se.lexicon.g49marketplace.repository.AdvertisementRepository;
import se.lexicon.g49marketplace.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    private AdvertisementDTOView convertToAdvertisementDTOView(Advertisement advertisement) {
        return AdvertisementDTOView.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .creationDate(advertisement.getCreationDate())
                .expirationDate(advertisement.getExpirationDate())
                .build();
    }


    @Override
    @Transactional
    public AdvertisementDTOView createAdvertisement(AdvertisementDTOForm adDtoForm) {
        // Check if user exists, otherwise create a new one
        Optional<User> optionalUser = userRepository.findByEmail(adDtoForm.getUser().getEmail());

        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = User.builder()
                    .email(adDtoForm.getUser().getEmail())
                    .username(adDtoForm.getUser().getUsername())
                    .password(adDtoForm.getUser().getPassword())
                    .phoneNumber(adDtoForm.getUser().getPhoneNumber())
                    .build();
            user = userRepository.save(user);
        }

        //Create a new ad entity using the DTO and builder
        Advertisement advertisement = Advertisement.builder()
                .title(adDtoForm.getTitle())
                .description(adDtoForm.getDescription())
                .creationDate(adDtoForm.getCreationDate())
                .expirationDate(adDtoForm.getExpirationDate())
                .user(user) // Associate the advertisement with the user
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
    public List<AdvertisementDTOView> findAdvertisementBetweenCreationDateAndExpirationDate(LocalDate from, LocalDate to) {
        // Retrieve ad with dates between the given dates
        List<Advertisement> advertisementList = advertisementRepository.selectAdvertisementBetweenDates(from,to);
        return advertisementList.stream().map(this::convertToAdvertisementDTOView).collect(Collectors.toList());

    }

    @Override
    public List<AdvertisementDTOView> findAdvertisementByUserEmail(String email) {
        List<Advertisement> advertisements = advertisementRepository.findByUserEmail(email);
        return advertisements.stream().map(this::convertToAdvertisementDTOView).collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementDTOView> getActiveAdvertisements() {
        List<Advertisement> activeAdvertisements = advertisementRepository.findAllByExpirationDateAfter(LocalDate.now());
        return activeAdvertisements.stream().map(this::convertToAdvertisementDTOView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteAdvertisementAfterExpirationDate() {
        List<Advertisement> expiredAdvertisements = advertisementRepository.findByExpirationDateBefore(LocalDate.now());
        advertisementRepository.deleteAll(expiredAdvertisements);
        return true;
    }

    @Override
    @Transactional
    public AdvertisementDTOView addAdvertisementToUser(String email, AdvertisementDTOForm adDTOForm) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found with email: " + email));

        // Update the advertisementDTOForm with the retrieved user
        adDTOForm.setUser(
                UserDTOForm.builder()
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .phoneNumber(user.getPhoneNumber())
                        .build());
        return createAdvertisement(adDTOForm);
    }


}
