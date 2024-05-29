package com.rpindv.backend_task.helpers;

public class FileNameSanitization {
    private static final String ALLOWED_CHARACTERS = "[^0-9_a-zA-Z\\(\\)\\%\\-\\.]";

    public static String sanitizeFileName(String fileName) {

        // Replace any path separators or unsafe characters with _ and return it
        return fileName.replaceAll(ALLOWED_CHARACTERS, "_");
    }
}
