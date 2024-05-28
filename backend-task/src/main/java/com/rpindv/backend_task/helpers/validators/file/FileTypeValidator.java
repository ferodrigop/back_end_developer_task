package com.rpindv.backend_task.helpers.validators.file;

import com.rpindv.backend_task.entities.WhiteListFileTypes;
import jakarta.validation.ValidationException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileTypeValidator extends FileValidator {
    @Override
    void validate(MultipartFile file) throws ValidationException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!WhiteListFileTypes.isAllowed(extension)) {
            throw new ValidationException("File Type not allowed");
        }
    }
}
