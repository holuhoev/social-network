package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.repo.UserRepository;

import javax.annotation.Nonnull;

@Service
@AllArgsConstructor
public class UpdateUser {

    private final UserRepository userRepository;

    @Nonnull
    public User update(@Nonnull final User user) {
        userRepository.update(user);
        return userRepository.loadById(user.getUserId());
    }
}
