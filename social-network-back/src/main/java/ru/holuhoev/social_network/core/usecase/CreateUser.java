package ru.holuhoev.social_network.core.usecase;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.core.domain.port.UserRepository;

import javax.annotation.Nonnull;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(@Nonnull final User user) {

        final String username = user.getUsername();

        if (userRepository.loadOptByUsername(username).isPresent()) {
            throw new AppRuntimeException(
                    AppErrorCode.USER_ALREADY_EXISTS,
                    String.format("User with username %s is already exists", username)
            );
        }

        final String encodedPassword = passwordEncoder.encode(user.getPassword());

        final User userToCreate = new User(
                UUID.randomUUID(),
                username,
                encodedPassword,
                user.getFirstName(),
                user.getLastName(),
                user.getAge(),
                user.getInterests(),
                user.getCity(),
                user.getCreateTs()
        );

        userRepository.create(userToCreate);

        return userToCreate;
    }
}
