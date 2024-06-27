package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementService {

    //create
    AdvertisementDTOView createAdvertisement(AdvertisementDTOForm dtoForm);
    //add advertising to user
    AdvertisementDTOView addAdvertisementToUser(String email , AdvertisementDTOForm dtoForm);
    //find active advertisement
    List<AdvertisementDTOView> getActiveAdvertisements();
    //remove advertising after expiration date
    boolean deleteAdvertisementAfterExpirationDate();

}
