package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;

import java.time.LocalDate;
import java.util.List;

public interface AdvertisementService {

    //create
    AdvertisementDTOView createAdvertisement(AdvertisementDTOForm adDtoForm);
    //find ad between creation date and expiration date
    List<AdvertisementDTOView> findAdvertisementBetweenCreationDateAndExpirationDate(LocalDate from, LocalDate to);
    //find advertisement by user email
    List<AdvertisementDTOView> findAdvertisementByUserEmail(String email);
    //add advertising to user
    AdvertisementDTOView addAdvertisementToUser(String email , AdvertisementDTOForm adDtoForm);
    //find active advertisement
    List<AdvertisementDTOView> getActiveAdvertisements();
    //remove advertising after expiration date
    boolean deleteAdvertisementAfterExpirationDate();

}
