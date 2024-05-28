package com.rpindv.backend_task.helpers.validators.file;

import jakarta.validation.ValidationException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileNameValidator extends FileValidator{
    private final int fileNameMaxLength = 200;
    @Override
    void validate(MultipartFile file) throws ValidationException {
        String fileName = FilenameUtils.getName(file.getOriginalFilename());

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new ValidationException("File name cannot be null or empty");
        }

        if(fileName.length() > fileNameMaxLength) {
            throw new ValidationException("File name is too long");
        }
    }
}
