package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.usecase.CreateUserService;
import ru.holuhoev.social_network.web.converters.UserConverter;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInputDTO;

import javax.annotation.Nonnull;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {

    private final CreateUserService createUserService;
    private final UserConverter userConverter;

    @Transactional
    @PostMapping(path = "/register")
    public BaseDTO<UserDTO> registerUser(@RequestBody @Nonnull final CreateUserInputDTO createUserInput) {

        final User user = userConverter.convertFromInput(createUserInput);

        final User createdUser = createUserService.create(user);

        final UserDTO dto = userConverter.convertToDTO(createdUser, false);

        return BaseDTO.success(dto);
    }


}
