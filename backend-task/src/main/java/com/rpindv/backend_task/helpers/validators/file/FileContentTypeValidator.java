package com.rpindv.backend_task.helpers.validators.file;

import com.rpindv.backend_task.entities.WhiteListFileTypes;
import jakarta.validation.ValidationException;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;


public class FileContentTypeValidator extends FileValidator {
    private final Tika tika = new Tika();
    @Override
    void validate(MultipartFile file) throws ValidationException {
        if (!WhiteListFileTypes.isAllowed(tika.detect(file.getOriginalFilename()))) {
            throw new ValidationException("File Type not allowed");
        }
    }
}
