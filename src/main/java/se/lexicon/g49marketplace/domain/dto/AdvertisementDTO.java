package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AdvertisementDTO {

    @NotBlank(message = "This field should not be null.")
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank(message = " HTML cannot be empty. ")
    private String htmlContent;//description

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
