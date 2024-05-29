package com.rpindv.backend_task.helpers;

public class FileNameSanitization {
    private static final String ALLOWED_CHARACTERS = "^[a-zA-Z0-9._-]+$";

    public static String sanitizeFileName(String fileName) {
        // Remove any non-ASCII characters
        String validASCIIFileName = fileName.replaceAll("[^\\p{ASCII}]", "");

        // Replace any path separators or unsafe characters with _ and return it
        return validASCIIFileName.replaceAll(ALLOWED_CHARACTERS, "_");
    }
}
