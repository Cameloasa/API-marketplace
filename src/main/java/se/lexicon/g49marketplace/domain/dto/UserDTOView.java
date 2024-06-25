package se.lexicon.g49marketplace.domain.dto;

import lombok.*;

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
