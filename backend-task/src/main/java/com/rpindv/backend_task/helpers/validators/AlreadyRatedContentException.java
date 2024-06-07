package com.rpindv.backend_task.helpers.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Value Not Found")
public class AlreadyRatedContentException extends RuntimeException {
    public AlreadyRatedContentException() {
    }

    public AlreadyRatedContentException(String message) {
        super(message);
    }

    public AlreadyRatedContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyRatedContentException(Throwable cause) {
        super(cause);
    }

    public AlreadyRatedContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
