package se.lexicon.g49marketplace.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString


public class UserDTO {

    @NotBlank(message = "Please, enter your email.")
    @Email
    private String email;

    @NotBlank(message = "Please, enter your username.")
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank(message = "Please, enter your password.")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must be at least 8 characters long and include one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;

    @NotBlank(message = "Please, enter your phone number.")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    @Size(min = 10, max = 15)
    private String phoneNumber;
}
