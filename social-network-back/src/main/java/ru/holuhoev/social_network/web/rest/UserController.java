package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.domain.entity.User;
import ru.holuhoev.social_network.usecase.CreateUser;
import ru.holuhoev.social_network.web.converters.UserConverter;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInput;

import javax.annotation.Nonnull;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    private final CreateUser createUser;
    private final UserConverter userConverter;

    @PostMapping("/register")
    public BaseDTO<User> registerUser(@Nonnull @RequestBody final CreateUserInput createUserInput) {

        final User user = userConverter.convertFromInput(createUserInput);
        final User createdUser = createUser.create(user);

        return BaseDTO.success(createdUser);
    }
}
