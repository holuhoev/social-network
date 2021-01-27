package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.application.security.ClientInfoService;
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.usecase.AddFriend;
import ru.holuhoev.social_network.core.usecase.CreateUser;
import ru.holuhoev.social_network.core.usecase.FindUser;
import ru.holuhoev.social_network.web.converters.UserConverter;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInputDTO;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final CreateUser createUser;
    private final AddFriend addFriend;
    private final FindUser findUser;
    private final UserConverter userConverter;
    private final ClientInfoService clientInfoService;

    @Transactional
    @PostMapping(path = "/register")
    public BaseDTO<UserDTO> registerUser(@RequestBody @Nonnull final CreateUserInputDTO createUserInput) {

        final User user = userConverter.convertFromInput(createUserInput);

        final User createdUser = createUser.create(user);

        final UserDTO dto = userConverter.convertToDTO(createdUser);

        return BaseDTO.success(dto);
    }

    @Transactional
    @PostMapping("/addFriend/{userId}")
    public BaseDTO<Boolean> addFriend(@PathVariable @Nonnull final UUID userId) {
        final UUID fromUserId = clientInfoService.loadClientInfo().getUserId();

        addFriend.addFriend(new Friend(fromUserId, userId));

        return BaseDTO.success(true);
    }

    @GetMapping
    public BaseDTO<List<UserDTO>> getAllUsers() {
        final List<UserDTO> users = userConverter.convertToDTOs(findUser.loadUsers());
        final UUID currentUserId = clientInfoService.loadClientInfo().getUserId();

        return BaseDTO.success(
                users.stream()
                     .filter(userDTO -> !userDTO.getUserId().equals(currentUserId))
                     .collect(Collectors.toList())
        );
    }

    @GetMapping("/myFriends")
    public BaseDTO<List<UserDTO>> getMyFriends() {
        final UUID userId = clientInfoService.loadClientInfo().getUserId();

        final List<UserDTO> users = userConverter.convertToDTOs(findUser.loadFriendsByUserId(userId));

        return BaseDTO.success(users);
    }


    @GetMapping("/currentUser")
    public BaseDTO<UserDTO> getCurrentUser() {
        final UUID userId = clientInfoService.loadClientInfo().getUserId();

        final UserDTO user = userConverter.convertToDTO(findUser.loadUserById(userId));

        return BaseDTO.success(user);
    }
}
