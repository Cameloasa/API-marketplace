package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import se.lexicon.g49marketplace.domain.entity.User;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AdvertisementDTOForm {

    @NotBlank(message = "This field should not be null.")
    private Long id;

    @NotBlank(message = "This field should not be null.")
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank(message = " HTML cannot be empty. ")
    private String html;//description

    @NotBlank
    private LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime expirationDate = LocalDateTime.now().plusDays(30);
    private boolean active;
    private boolean deleted;
    private User user;
}
