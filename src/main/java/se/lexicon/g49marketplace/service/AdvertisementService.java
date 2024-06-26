package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementService {

    //create
    Advertisement createAdvertisement(AdvertisementDTOForm advertisementDTO);
    //add advertising to user
    AdvertisementDTOView addAdvertisementToPerson(String email , AdvertisementDTOForm dtoForm);
    //findByUserEmail
    AdvertisementDTOView findAdvertisementByUserEmail(String email,AdvertisementDTOForm dtoForm);
    //find active advertisement
    List<Advertisement> getActiveAdvertisements();
    //remove advertising after expiration date
    boolean deleteAdvertisementAfterExpirationDate();

}
