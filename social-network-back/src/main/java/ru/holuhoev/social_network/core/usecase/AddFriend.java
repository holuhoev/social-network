package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.enums.OtusErrorCode;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.core.domain.port.FriendRepository;

import javax.annotation.Nonnull;

@Service
@AllArgsConstructor
public class AddFriend {

    private final FriendRepository friendRepository;

    public Friend addFriend(@Nonnull final Friend friend) {
        if (friendRepository.existsByUserIds(friend.getFromUserId(), friend.getToUserId())) {
            throw new AppRuntimeException(OtusErrorCode.FRIEND_ALREADY_EXISTS);
        }

        return friendRepository.create(friend);
    }
}
