package ru.holuhoev.social_network.web.converters;

import org.springframework.stereotype.Component;
import ru.holuhoev.social_network.domain.entity.User;
import ru.holuhoev.social_network.web.dto.input.CreateUserInput;

@Component
public class UserConverter {
    public User convertFromInput(final CreateUserInput createUserInput) {
        return new User(
                null,
                createUserInput.getUsername(),
                createUserInput.getPassword(),
                createUserInput.getFirstName(),
                createUserInput.getLastName(),
                createUserInput.getBirthDate(),
                createUserInput.getInterests(),
                createUserInput.getCity()
        );
    }
}
