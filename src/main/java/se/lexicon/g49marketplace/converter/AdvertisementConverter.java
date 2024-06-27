package se.lexicon.g49marketplace.converter;

import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

public interface AdvertisementConverter  {

    AdvertisementDTOView toDTO(Advertisement advertisement);
    Advertisement toEntity(AdvertisementDTOForm dto );
}
