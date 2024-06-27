package se.lexicon.g49marketplace.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;

@Component
public class AdvertisementConverterImpl implements AdvertisementConverter {



    @Override
    public AdvertisementDTOView toDTO(Advertisement advertisement) {
        if (advertisement == null) {
            return null;
        }
        return AdvertisementDTOView.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .creationDate(advertisement.getCreationDate())
                .expirationDate(advertisement.getExpirationDate())
                .active(!advertisement.isExpired())
                .build();

    }

    @Override
    public Advertisement toEntity(AdvertisementDTOForm dto) {
        if (dto == null) {
            return null;
        }
        return Advertisement.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .expirationDate(dto.getExpirationDate())
                .build();

    }
}
