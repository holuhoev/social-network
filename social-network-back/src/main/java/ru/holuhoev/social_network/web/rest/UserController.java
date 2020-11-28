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
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.usecase.AddFriend;
import ru.holuhoev.social_network.core.usecase.CreateUser;
import ru.holuhoev.social_network.core.usecase.FindUser;
import ru.holuhoev.social_network.web.converters.UserConverter;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInput;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateUser createUser;
    private final AddFriend addFriend;
    private final FindUser findUser;
    private final UserConverter userConverter;

    @Transactional
    @PostMapping(path = "/register")
    public BaseDTO<UserDTO> registerUser(@RequestBody @Nonnull final CreateUserInput createUserInput) {

        final User user = userConverter.convertFromInput(createUserInput);

        final User createdUser = createUser.create(user);

        final UserDTO dto = userConverter.convertToDTO(createdUser);

        return BaseDTO.success(dto);
    }

    @Transactional
    @PostMapping("/addFriend/{userId}")
    public ResponseEntity<Boolean> addFriend(@PathVariable @Nonnull final UUID userId) {
        final UUID fromUserId = UUID.randomUUID();

        addFriend.addFriend(new Friend(fromUserId, userId));

        return ResponseEntity.ok(true);
    }

    @GetMapping
    public BaseDTO<List<UserDTO>> getAllUsers() {
        final List<UserDTO> users = userConverter.convertToDTOs(findUser.loadAllUsers());

        return BaseDTO.success(users);
    }

    @GetMapping("/friends")
    public BaseDTO<List<UserDTO>> getMyFriends() {
        final UUID userId = UUID.randomUUID();

        final List<UserDTO> users = userConverter.convertToDTOs(findUser.loadFriendsByUserId(userId));

        return BaseDTO.success(users);
    }


    @GetMapping("/currentUser")
    public BaseDTO<UserDTO> getCurrentUser() {
        final UUID userId = UUID.randomUUID();

        final UserDTO user = userConverter.convertToDTO(findUser.loadUserById(userId));

        return BaseDTO.success(user);
    }
}
