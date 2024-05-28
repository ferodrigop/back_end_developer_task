package com.rpindv.backend_task.helpers.validators;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
