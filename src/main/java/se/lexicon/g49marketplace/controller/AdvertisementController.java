package se.lexicon.g49marketplace.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOForm;
import se.lexicon.g49marketplace.domain.dto.AdvertisementDTOView;
import se.lexicon.g49marketplace.domain.entity.Advertisement;
import se.lexicon.g49marketplace.service.AdvertisementService;

import java.time.LocalDate;
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


    @PostMapping("/create")//this is working in Postman
   public ResponseEntity<AdvertisementDTOView> doCreateAdvertisement(@RequestBody @Valid AdvertisementDTOForm adDtoForm) {
        System.out.println(" Register DTO Form: " + adDtoForm);
        AdvertisementDTOView responseBody = advertisementService.createAdvertisement(adDtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

   }

    @GetMapping("/between-dates")
    public ResponseEntity<List<AdvertisementDTOView>> doFindAdvertisementsBetweenDates(
           @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
           @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        System.out.println("List advertisements between dates: " + from + " to " + to);
        List<AdvertisementDTOView> advertisements = advertisementService.findAdvertisementBetweenCreationDateAndExpirationDate(from,to);
        return ResponseEntity.ok(advertisements);
   }
    @GetMapping("/user-advertisements")
    public ResponseEntity<List<AdvertisementDTOView>> findAdvertisementsByUserEmail(@RequestParam String email) {
        List<AdvertisementDTOView> advertisements = advertisementService.findAdvertisementByUserEmail(email);
        return ResponseEntity.ok(advertisements);
    }

    @GetMapping("/active")
    public ResponseEntity<List<AdvertisementDTOView>> getActiveAdvertisements() {
        List<AdvertisementDTOView> activeAdvertisements = advertisementService.getActiveAdvertisements();
        return ResponseEntity.ok(activeAdvertisements);
    }

    @DeleteMapping("/delete-expired")
    public ResponseEntity<Void> deleteAdvertisementsAfterExpirationDate() {
        boolean isDeleted = advertisementService.deleteAdvertisementAfterExpirationDate();
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


        @PostMapping("/add-to-user")
        public ResponseEntity<AdvertisementDTOView> addAdvertisementToUser(@RequestParam String email, @RequestBody @Valid AdvertisementDTOForm adDtoForm) {
            AdvertisementDTOView responseBody = advertisementService.addAdvertisementToUser(email, adDtoForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
        }
    }

