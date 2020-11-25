package ru.holuhoev.social_network.domain.port;

import ru.holuhoev.social_network.domain.entity.User;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    @Nonnull
    Optional<User> loadOptByUsername(@Nonnull final String login);

    @Nonnull
    User create(@Nonnull final User user);

    @Nonnull
    List<User> loadFriendUsers(@Nonnull final UUID userId);

}
