package ru.holuhoev.social_network.core.domain.exception;

import ru.holuhoev.social_network.core.domain.enums.OtusErrorCode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AppRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -4878979185454430827L;
    private final OtusErrorCode otusErrorCode;

    public AppRuntimeException(
            @Nonnull final OtusErrorCode otusErrorCode,
            @Nullable final String message,
            @Nullable final Throwable cause
    ) {
        super(message, cause);
        this.otusErrorCode = otusErrorCode;
    }

    public AppRuntimeException(@Nonnull final OtusErrorCode otusErrorCode) {
        this(otusErrorCode, null, null);
    }

    public AppRuntimeException(@Nonnull final OtusErrorCode otusErrorCode, @Nullable final String message) {
        this(otusErrorCode, message, null);
    }

    public OtusErrorCode getOtusErrorCode() {
        return otusErrorCode;
    }
}
