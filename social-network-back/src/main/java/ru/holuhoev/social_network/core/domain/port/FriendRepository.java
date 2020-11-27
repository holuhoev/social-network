package ru.holuhoev.social_network.core.domain.port;


import ru.holuhoev.social_network.core.domain.entity.Friend;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public interface FriendRepository {

    boolean existsByUserIds(@Nonnull final UUID firstUserId,@Nonnull final UUID secondUserId);

    @Nonnull
    Friend create(@Nonnull final Friend friend);

    @Nonnull
    List<Friend> loadByUserId(@Nonnull final UUID userId);
}
