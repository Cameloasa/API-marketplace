package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "ID is required.")
    private Long id;

    @NotBlank(message = "Title is required..")
    @Size(min = 10, max = 100)
    private String title;

    @NotBlank(message = " Description cannot be empty. ")
    private String description;//Use HTML content here

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    @NotNull(message = "User details are required.")
    @Email(message = "Invalid email format.")
    private UserDTOForm user;

}
