package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.port.UserRepository;

import javax.annotation.Nonnull;
import java.util.List;
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
    public List<User> loadAllUsers() {
        return userRepository.loadAll();
    }

    @Nonnull
    public User loadUserById(@Nonnull final UUID userId) {
        return userRepository.loadById(userId);
    }
}
