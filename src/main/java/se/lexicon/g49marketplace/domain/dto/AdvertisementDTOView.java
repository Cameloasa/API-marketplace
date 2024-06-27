package se.lexicon.g49marketplace.domain.dto;

import lombok.*;
import se.lexicon.g49marketplace.domain.entity.User;

import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private boolean isExpired;
    private UserDTOView user;




}
