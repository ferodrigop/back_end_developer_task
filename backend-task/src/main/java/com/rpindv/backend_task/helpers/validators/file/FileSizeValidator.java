package com.rpindv.backend_task.helpers.validators.file;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
public class FileSizeValidator extends FileValidator {
    private long maxSize = 1024 * 1024 * 50; // 50 MB

    @Override
    public void validate(MultipartFile file) throws ValidationException {
        if (file.getSize() > maxSize) {
            throw new ValidationException("File is too large, limit is 50 MB(Mega Bytes)");
        }
    }
}
