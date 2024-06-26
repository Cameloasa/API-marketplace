package se.lexicon.g49marketplace.service;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

import java.time.LocalDateTime;

public interface AdvertisementService {

    //create
    Advertisement createAdvertisement(AdvertisementDTOForm advertisementDTO);
    //add advertising to user
    AdvertisementDTOView addAdvertisementToPerson(String email , AdvertisementDTOForm dtoForm);
    //remove advertising after expiration date
    AdvertisementDTOView deleteAdvertisement(AdvertisementDTOForm dtoForm, LocalDateTime expirationDate);
    //findByCreationDate
    AdvertisementDTOView findAdvertisementByCreationDate(AdvertisementDTOForm dtoForm, LocalDateTime creationDate);
    //findByExpirationDate
    AdvertisementDTOView findAdvertisementByExpirationDate(AdvertisementDTOForm dtoForm, LocalDateTime expirationDate);
    //findByUserEmail
    AdvertisementDTOView findAdvertisementByUserEmail(String email,AdvertisementDTOForm dtoForm);
}
