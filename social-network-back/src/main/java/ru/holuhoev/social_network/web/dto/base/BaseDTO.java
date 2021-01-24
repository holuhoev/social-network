package ru.holuhoev.social_network.web.dto.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseDTO<T> {
    private final boolean success;
    private final T data;
    private final ErrorDTO error;


    @JsonCreator
    public BaseDTO(
            @JsonProperty("success") final boolean success,
            @JsonProperty("data") final T data,
            @JsonProperty("error") final ErrorDTO error
    ) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> BaseDTO<T> success(final T data) {
        return new BaseDTO<>(true, data, null);
    }

    public static <T> BaseDTO<T> success() {
        return new BaseDTO<>(true, null, null);
    }

    public static <T> BaseDTO<T> error(final ErrorDTO error) {
        return new BaseDTO<>(false, null, error);
    }
}
