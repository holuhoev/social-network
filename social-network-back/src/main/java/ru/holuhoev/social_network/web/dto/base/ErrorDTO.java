package ru.holuhoev.social_network.web.dto.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorDTO {
    private final String id;
    private final String code;
    private final String title;

    @JsonCreator
    public ErrorDTO(
            @JsonProperty("id") final String id,
            @JsonProperty("code") final String code,
            @JsonProperty("title") final String title
    ) {
        this.id = id;
        this.code = code;
        this.title = title;
    }
}
