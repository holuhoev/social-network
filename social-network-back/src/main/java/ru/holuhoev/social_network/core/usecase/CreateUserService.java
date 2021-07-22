package ru.holuhoev.social_network.core.usecase;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.core.domain.repo.UserRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Nonnull
    public User create(@Nonnull final User user) {
        final User userToCreate = encodePassword(user);

        userRepository.create(userToCreate);

        return userToCreate;
    }


    @Nonnull
    private User encodePassword(final User user) {
        final String encodedPassword = passwordEncoder.encode(user.getPassword());

        return new User(
                user.getUserId(),
                user.getUsername(),
                encodedPassword,
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getInterests(),
                user.getCity(),
                user.getGender()
        );
    }

}
