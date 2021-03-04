package ru.holuhoev.social_network.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("M"),
    FEMALE("F");

    @Nonnull
    private final String id;


    private static final Map<String, Gender> LOOKUP = Arrays
            .stream(values())
            .collect(Collectors.toMap(Gender::getId, v -> v));

    @Nonnull
    public static Gender byId(final String id) {
        return LOOKUP.get(id);
    }

}
