package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.entity.Friend;
import ru.holuhoev.social_network.core.domain.repo.FriendRepository;

import javax.annotation.Nonnull;

@Service
@AllArgsConstructor
public class AddFriend {

    private final FriendRepository friendRepository;

    public void addFriend(@Nonnull final Friend friend) {
        if (friendRepository.existsByUserIds(friend.getFromUserId(), friend.getToUserId())) {
            return;
        }

        friendRepository.create(friend);
    }
}
