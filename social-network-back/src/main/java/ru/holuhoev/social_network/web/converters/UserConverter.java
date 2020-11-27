package ru.holuhoev.social_network.web.converters;

import org.springframework.stereotype.Component;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInput;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Nonnull
    public User convertFromInput(@Nonnull final CreateUserInput user) {
        return new User(
                null,
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getInterests(),
                user.getCity()
        );
    }

    @Nonnull
    public UserDTO convertToDTO(@Nonnull final User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getInterests(),
                user.getCity()
        );
    }

    @Nonnull
    public List<UserDTO> convertToDTOs(@Nonnull final List<User> users) {
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
