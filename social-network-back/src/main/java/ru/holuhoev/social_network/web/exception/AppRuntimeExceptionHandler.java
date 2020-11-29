package ru.holuhoev.social_network.web.exception;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.holuhoev.social_network.core.domain.exception.AppRuntimeException;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.base.ErrorDTO;

import javax.annotation.Nonnull;

@ControllerAdvice
public class AppRuntimeExceptionHandler {

    @Nonnull
    @ExceptionHandler(AppRuntimeException.class)
    public ResponseEntity<BaseDTO<?>> handleException(@Nonnull final AppRuntimeException exception) {
        final ErrorDTO error = new ErrorDTO(
                RandomStringUtils.randomAlphanumeric(10),
                exception.getAppErrorCode().name(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(BaseDTO.error(error));
    }
}
