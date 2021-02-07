package ru.holuhoev.social_network.web.converters;

import org.springframework.stereotype.Component;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInputDTO;

import javax.annotation.Nonnull;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Nonnull
    public User convertFromInput(@Nonnull final CreateUserInputDTO user) {
        return new User(
                UUID.randomUUID(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getInterests(),
                user.getCity()
        );
    }

    @Nonnull
    public UserDTO convertToDTO(@Nonnull final User user, final boolean isFriend) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getInterests(),
                user.getCity(),
                isFriend
        );
    }

    @Nonnull
    public List<UserDTO> convertToDTOs(
            @Nonnull final List<User> users,
            @Nonnull final Set<UUID> friendsUserIds
    ) {
        return users
                .stream()
                .map(user -> convertToDTO(user, friendsUserIds.contains(user.getUserId())))
                .collect(Collectors.toList());
    }
}
