package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryDTO {

    @NotBlank(message = "This field cannot be null.")
    private String title;

    @NotBlank(message = "This field cannot be null.")
    private String description;
}
