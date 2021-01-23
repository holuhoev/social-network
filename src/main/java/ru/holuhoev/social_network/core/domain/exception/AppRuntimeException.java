package ru.holuhoev.social_network.core.domain.exception;

import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AppRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -4878979185454430827L;
    private final AppErrorCode appErrorCode;

    public AppRuntimeException(
            @Nonnull final AppErrorCode appErrorCode,
            @Nullable final String message,
            @Nullable final Throwable cause
    ) {
        super(message, cause);
        this.appErrorCode = appErrorCode;
    }

    public AppRuntimeException(@Nonnull final AppErrorCode appErrorCode) {
        this(appErrorCode, null, null);
    }

    public AppRuntimeException(@Nonnull final AppErrorCode appErrorCode, @Nullable final String logMessage) {
        this(appErrorCode, logMessage, null);
    }

    public AppErrorCode getAppErrorCode() {
        return appErrorCode;
    }
}
