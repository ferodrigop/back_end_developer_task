package com.rpindv.backend_task.helpers.validators.file;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ValidationException;

public abstract class FileValidator {
    abstract void validate(MultipartFile file) throws ValidationException;
}
