package ru.holuhoev.social_network.core.domain.repo;


import ru.holuhoev.social_network.core.domain.entity.Friend;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface FriendRepository {

    boolean existsByUserIds(@Nonnull final UUID firstUserId, @Nonnull final UUID secondUserId);

    void create(@Nonnull final Friend friend);

    boolean remove(@Nonnull final UUID firstUserId, @Nonnull final UUID secondUserId);
}
