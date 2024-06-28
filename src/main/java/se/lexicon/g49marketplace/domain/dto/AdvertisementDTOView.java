package se.lexicon.g49marketplace.domain.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AdvertisementDTOView {

    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private UserDTOView user;




}
