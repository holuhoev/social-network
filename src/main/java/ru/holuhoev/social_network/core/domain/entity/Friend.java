package ru.holuhoev.social_network.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Friend {
    private final UUID fromUserId;
    private final UUID toUserId;
}
