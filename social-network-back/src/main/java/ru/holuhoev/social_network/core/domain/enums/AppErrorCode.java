package ru.holuhoev.social_network.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppErrorCode {
    USER_ALREADY_EXISTS("Пользователь с логином уже существует"),
    FRIEND_ALREADY_EXISTS("Пользователь уже добавлен в друзья"),


    ;


    /**
     * Сообщение для фронта
     */
    private final String description;

}
