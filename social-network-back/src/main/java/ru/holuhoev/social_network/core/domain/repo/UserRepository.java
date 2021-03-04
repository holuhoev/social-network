package ru.holuhoev.social_network.core.domain.repo;

import ru.holuhoev.social_network.core.domain.entity.User;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    @Nonnull
    Optional<User> loadOptByUsername(@Nonnull final String username);

    void create(@Nonnull final User user);

    @Nonnull
    List<User> loadFriendUsers(@Nonnull final UUID userId);

    @Nonnull
    List<User> loadUsers();

    @Nonnull
    User loadById(@Nonnull final UUID userId);

    void insertBatch(@Nonnull final List<User> users);

    List<User> findByLastNameAndFirstName(@Nonnull final String lastName, @Nonnull final String firstName);

    void update(@Nonnull final User user);
}
