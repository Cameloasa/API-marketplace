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
    private LocalDateTime expirationDate;
    private boolean published;
    private boolean deleted;
    private boolean active;
    private User user;

}
