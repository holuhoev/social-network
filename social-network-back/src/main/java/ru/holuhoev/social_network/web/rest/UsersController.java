package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.application.security.ClientInfoService;
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.usecase.AddFriend;
import ru.holuhoev.social_network.core.usecase.CreateUser;
import ru.holuhoev.social_network.core.usecase.FindUser;
import ru.holuhoev.social_network.core.usecase.RemoveFriend;
import ru.holuhoev.social_network.core.usecase.UpdateUser;
import ru.holuhoev.social_network.web.converters.UserConverter;
import ru.holuhoev.social_network.web.dto.UserDTO;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.CreateUserInputDTO;
import ru.holuhoev.social_network.web.dto.input.UpdateUserInputDTO;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final CreateUser createUser;
    private final AddFriend addFriend;
    private final UpdateUser updateUser;
    private final FindUser findUser;
    private final RemoveFriend removeFriend;
    private final UserConverter userConverter;
    private final ClientInfoService clientInfoService;

    @Transactional
    @PostMapping(path = "/register")
    public BaseDTO<UserDTO> registerUser(@RequestBody @Nonnull final CreateUserInputDTO createUserInput) {

        final User user = userConverter.convertFromInput(createUserInput);

        final User createdUser = createUser.create(user);

        final UserDTO dto = userConverter.convertToDTO(createdUser, false);

        return BaseDTO.success(dto);
    }

    @Transactional
    @PutMapping("/updateProfile")
    public BaseDTO<UserDTO> updateProfile(@RequestBody @Nonnull final UpdateUserInputDTO updateUserInputDTO) {
        final UUID userId = clientInfoService.loadClientInfo().getUserId();

        final User currentUser = findUser.loadUserById(userId);
        final User user = new User(
                userId,
                currentUser.getUsername(),
                currentUser.getPassword(),
                updateUserInputDTO.getFirstName(),
                updateUserInputDTO.getLastName(),
                updateUserInputDTO.getAge(),
                updateUserInputDTO.getInterests(),
                updateUserInputDTO.getCity(),
                currentUser.getGender()
        );

        final User updatedUser = updateUser.update(user);

        return BaseDTO.success(userConverter.convertToDTO(updatedUser, false));
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
        final UUID currentUserId = clientInfoService.loadClientInfo().getUserId();

        final List<User> users = findUser
                .loadUsers()
                .stream()
                .filter(userDTO -> !userDTO.getUserId().equals(currentUserId))
                .collect(Collectors.toList());

        final List<User> myFriends = findUser.loadFriendsByUserId(currentUserId);

        final Set<UUID> friendUserIds = myFriends.stream().map(User::getUserId).collect(Collectors.toSet());

        final List<UserDTO> userDTOs = userConverter.convertToDTOs(users, friendUserIds);

        return BaseDTO.success(userDTOs);
    }

    @GetMapping("/myFriends")
    public BaseDTO<List<UserDTO>> getMyFriends() {
        final UUID userId = clientInfoService.loadClientInfo().getUserId();

        final List<User> users = findUser.loadFriendsByUserId(userId);

        final Set<UUID> friendUserIds = users.stream().map(User::getUserId).collect(Collectors.toSet());

        final List<UserDTO> userDTOs = userConverter.convertToDTOs(users, friendUserIds);

        return BaseDTO.success(userDTOs);
    }


    @DeleteMapping("/friends/remove/{userId}")
    public BaseDTO<Boolean> removeFromFriends(@PathVariable @Nonnull final UUID userId) {
        final UUID myUserId = clientInfoService.loadClientInfo().getUserId();
        final boolean result = removeFriend.removeFromFriend(
                myUserId,
                userId
        );

        return BaseDTO.success(result);
    }

    @GetMapping("/currentUser")
    public BaseDTO<UserDTO> getCurrentUser() {
        final UUID userId = clientInfoService.loadClientInfo().getUserId();

        final UserDTO user = userConverter.convertToDTO(findUser.loadUserById(userId), false);

        return BaseDTO.success(user);
    }


    @GetMapping("/search")
    public BaseDTO<List<UserDTO>> searchUsers(
            @RequestParam final String lastName,
            @RequestParam final String firstName
    ) {
        final List<User> users = findUser.findByLastNameAndFirstName(lastName, firstName);

        return BaseDTO.success(userConverter.convertToDTOs(users, Collections.emptySet()));
    }
}
