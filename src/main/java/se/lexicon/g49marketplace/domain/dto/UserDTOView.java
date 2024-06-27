package se.lexicon.g49marketplace.domain.dto;

import lombok.*;
import se.lexicon.g49marketplace.domain.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder


public class UserDTOView {

    private String email;
    private String username;
    private String phoneNumber;

}
