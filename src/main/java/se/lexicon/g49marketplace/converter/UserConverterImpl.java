package se.lexicon.g49marketplace.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g49marketplace.domain.dto.UserDTOView;
import se.lexicon.g49marketplace.domain.entity.User;

@Component
public class UserConverterImpl implements UserConverter {



    @Override
    public UserDTOView toDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTOView.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User toEntity(UserDTOView dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .username(dto.getUsername())
                .build();
    }
}
