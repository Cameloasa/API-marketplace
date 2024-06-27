package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AdvertisementService {

    //create
    AdvertisementDTOView createAdvertisement(AdvertisementDTOForm adDtoForm);
    //find ad between creation date and expiration date
    List<AdvertisementDTOView> findAdvertisementBetweenCreationDateAndExpirationDate(LocalDateTime creationDate, LocalDateTime expirationDate);
    //find advertisement by user email
    Optional<AdvertisementDTOView> findAdvertisementByUserId(String email);
    //add advertising to user
    AdvertisementDTOView addAdvertisementToUser(String email , AdvertisementDTOForm adDtoForm);
    //find active advertisement
    List<AdvertisementDTOView> getActiveAdvertisements();
    //remove advertising after expiration date
    boolean deleteAdvertisementAfterExpirationDate();

}
