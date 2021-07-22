package ru.holuhoev.social_network.core.domain.repo;

import ru.holuhoev.social_network.core.domain.entity.User;

import javax.annotation.Nonnull;
import java.util.Optional;

public interface UserRepository {

    @Nonnull
    Optional<User> loadOptByUsername(@Nonnull final String username);

    void create(@Nonnull final User user);

}
