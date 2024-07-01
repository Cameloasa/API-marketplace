package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;



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

    private LocalDate creationDate;
    private LocalDate expirationDate;

    @NotNull(message = "User details are required.")
    private UserDTOForm user;

}
