package ru.holuhoev.social_network.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.domain.entity.User;
import ru.holuhoev.social_network.domain.port.UserRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FindFriendUser {

    private final UserRepository userRepository;

    public List<User> loadFriendsByUserId(@Nonnull final UUID userId) {
        return userRepository.loadFriendUsers(userId);
    }
}
