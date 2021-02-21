package ru.holuhoev.social_network.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.base.ErrorDTO;

import javax.annotation.Nonnull;

@Slf4j
@ControllerAdvice
public class AppRuntimeExceptionHandler {

    @Nonnull
    @ExceptionHandler(AppRuntimeException.class)
    public ResponseEntity<BaseDTO<?>> handleException(@Nonnull final AppRuntimeException exception) {
        final AppErrorCode appErrorCode = exception.getAppErrorCode();
        final ErrorDTO error = new ErrorDTO(
                RandomStringUtils.randomAlphanumeric(10),
                appErrorCode.name(),
                appErrorCode.getDescription()
        );
        log.error(error.toString(), exception);
        return ResponseEntity.status(selectStatus(appErrorCode))
                             .body(BaseDTO.error(error));
    }

    private HttpStatus selectStatus(final AppErrorCode appErrorCode) {
        if (appErrorCode == AppErrorCode.TOKEN_EXPIRED) {
            return HttpStatus.UNAUTHORIZED;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
