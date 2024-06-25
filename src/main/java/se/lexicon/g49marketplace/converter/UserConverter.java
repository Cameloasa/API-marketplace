package se.lexicon.g49marketplace.converter;

import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.domain.entity.User;

public interface UserConverter {

    UserDTOView toDTO(User user);
    User toEntity(UserDTOView dto);
}
