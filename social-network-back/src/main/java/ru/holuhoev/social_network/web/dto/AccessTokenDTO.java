package ru.holuhoev.social_network.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
@AllArgsConstructor
public class AccessTokenDTO {

    @Nonnull
    private final String accessToken;
}
