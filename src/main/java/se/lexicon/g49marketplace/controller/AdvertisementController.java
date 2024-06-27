package se.lexicon.g49marketplace.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.service.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advertisements")
@Validated
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(final AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }


    @PostMapping("/create")
   public ResponseEntity<AdvertisementDTOView> doCreateAdvertisement(@RequestBody @Valid AdvertisementDTOForm adDtoForm) {
        System.out.println(" Register DTO Form: " + adDtoForm);
        AdvertisementDTOView responseBody = advertisementService.createAdvertisement(adDtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

   }
}
