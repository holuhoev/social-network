package ru.holuhoev.social_network.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {
    private final UUID userId;
    @Nonnull
    private final String username;
    @Nonnull
    private final String password;
    @Nonnull
    private final String firstName;
    @Nonnull
    private final String lastName;
    @Nonnull
    private final LocalDate birthDate;
    @Nonnull
    private final String interests;
    @Nonnull
    private final String city;
}
