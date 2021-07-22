package ru.holuhoev.social_network.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppErrorCode {
    USER_ALREADY_EXISTS("Пользователь с таким логином уже существует"),
    TOKEN_EXPIRED("Истек срок действия токена"),

    ;


    /**
     * Сообщение для фронта
     */
    private final String description;

}
