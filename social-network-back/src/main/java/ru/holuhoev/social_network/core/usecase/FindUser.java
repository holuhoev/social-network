package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.repo.UserRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FindUser {

    private final UserRepository userRepository;

    @Nonnull
    public List<User> loadFriendsByUserId(@Nonnull final UUID userId) {
        return userRepository.loadFriendUsers(userId);
    }

    @Nonnull
    public List<User> loadUsers() {
        return userRepository.loadUsers();
    }

    @Nonnull
    public User loadUserById(@Nonnull final UUID userId) {
        return userRepository.loadById(userId);
    }

    @Nonnull
    public Optional<User> loadOptUserByUsername(@Nonnull final String username) {
        return userRepository.loadOptByUsername(username);
    }

    @Nonnull
    public List<User> findByLastNameAndFirstName(@Nonnull final String lastName, @Nonnull final String firstName) {
        return userRepository.findByLastNameAndFirstName(lastName, firstName);
    }
}
