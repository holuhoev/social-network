package ru.holuhoev.social_network.core.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.holuhoev.social_network.core.domain.repo.FriendRepository;

import javax.annotation.Nonnull;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RemoveFriend {

    private final FriendRepository friendRepository;


    public boolean removeFromFriend(
            @Nonnull final UUID myUserId,
            @Nonnull final UUID userId
    ) {
        return friendRepository.remove(myUserId, userId);
    }
}
